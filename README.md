# springboot-docker jenkins pipeline script for build, docker image creation, docker push & K8s deployment
------------------------------------------------------------------------------------------------------------
pipeline {
    agent any

    environment{
        dockerImageTagName=""
        dockerImageName = ""
    }    
    
   tools{
       maven '3.6.3'
   }
    parameters{
        string(name: 'git_ssh_link')
    }
    
    stages{
         stage("Maven Build"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: '${git_ssh_link}']])
                
                script{
                    def files = readFile('Jenkinsfile')
                    Properties prop=new Properties()
                    InputStream is=new ByteArrayInputStream(files.getBytes())
                    prop.load(is)
                    def app_name='app_name'
                    def imageTag='docker_image_tag'
                    dockerImageName= prop."$app_name"
                    dockerImageTagName= prop."$imageTag"
                }
                sh 'mvn clean install'
            }
        }
         stage("Docker Build"){
           steps{
               
               echo "$dockerImageName"
               echo "$dockerImageTagName"
              sh "docker build -t "+ "$dockerImageName"+":"+"$dockerImageTagName"+" ."
           }
         }
         
        stage("Publish to Dockerhub"){
             
             steps{
                 script{
                     withCredentials([string(credentialsId: 'dockerpwd', variable: 'dockerhubpwd')]) {
                            sh "docker login -u jyotirmayapati001 -p ${dockerhubpwd}"
                            sh "docker tag "+"$dockerImageName"+":"+"$dockerImageTagName"+" jyotirmayapati001/springboot-docker:"+"$dockerImageTagName"+"-release"
                            sh "docker push jyotirmayapati001/springboot-docker:"+"$dockerImageTagName"+"-release"
                            sh 'docker logout'
                    }
                }
             }
         }
         
        stage("Deploy to K8s"){
            steps{
                withCredentials([
                                    file(credentialsId: 'kubeconfig', variable: 'SECRET_FILE'),
                                    string(credentialsId: 'dockerpwd', variable: 'dockerhubpwd')
                                ]) {
                    script {
                        sh "docker login -u jyotirmayapati001 -p ${dockerhubpwd}"
                        sh "sed s/cicd/"+"$dockerImageTagName"+"-release"+"/g "+"kubemanifest.yaml > kubemanifest-new.yaml"
                        sh "kubectl apply --kubeconfig=${SECRET_FILE} -f kubemanifest-new.yaml"
                        sh 'docker logout'
                    }
                }
            }
        }
    }
}

