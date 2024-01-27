package com.jyotirmaya.user.config;

//import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@ComponentScan(basePackages= {"com.jyotirmaya.user"})
public class SwaggerConfig {

	/*
	 * @Bean public GroupedOpenApi api() { return
	 * GroupedOpenApi.builder().group("user-api").pathsToMatch("/**").build(); }
	 */
	
	@Bean
	public OpenAPI apiSpec() {
		return new OpenAPI().info(new Info().title("springboot-docker API").description("springboot-docker API provides user "));
	}
}
