package com.pederapido.pederapido.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class CorsConfig //implements WebMvcConfigurer 
{
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		 registry.addMapping("/**")
//         .allowedOrigins("http://localhost:4200", "http://ec2-100-26-208-1.compute-1.amazonaws.com:8081/",
// 				"http://ec2-100-26-208-1.compute-1.amazonaws.com:8081/:80", "http://ec2-100-26-208-1.compute-1.amazonaws.com",
// 				"http://100.26.208.1", "http://100.26.208.1:8081")
//         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
//         .allowCredentials(true);
//	}

}
