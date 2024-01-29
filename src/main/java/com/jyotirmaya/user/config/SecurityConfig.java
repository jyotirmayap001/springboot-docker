package com.jyotirmaya.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	private static final String[] AUTH_WHITELIST= {
			"swagger-resources/**",
			"swagger-ui.html",
			"/v2/api-docs",
			"/webjars/**"
	};
	
//	@Bean
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//          .inMemoryAuthentication()
//          .withUser("jyoti")
//          .password(passwordEncoder().encode("{noop}jyoti@123"))
//          .authorities("ROLE_USER");
//    }
	
//	@Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("jyoti")
//                .password("{noop}jyoti@123")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
	

	
	//for latest spring-security
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		
//		http.csrf().disable().authorizeHttpRequests().antMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated().and().httpBasic();
//		
//		return http.build();
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    String password = passwordEncoder().encode("jyoti@123");
	    auth.inMemoryAuthentication().withUser("jyoti").password(password).roles("ADMIN");
	}
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated().and().httpBasic();
	}
	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
    }
	
}
