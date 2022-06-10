package com.pederapido.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AwsCognitoJwtAuthFilter awsCognitoJwtAuthenticationFilter;
	
	@Autowired
	private PedidoProperties properties;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of(properties.getOrigensPermitidas()));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

		http.headers().cacheControl();
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/h2-console/**", "/cardapio/**", "/pedido", "/pedido/solicitarConta", 
						"/socket/**", "/pedido/pedidoAbertoMesa/**", "/restaurante/restauranteMesa/**",
						"/swagger-ui.html", "/webjars/**", "/v2/api-docs", "/swagger-resources/**").permitAll()
	            .anyRequest().authenticated()
	            .and().csrf().disable()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and().headers().frameOptions().sameOrigin()
				.and()
				.addFilterBefore(awsCognitoJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.cors().configurationSource(request -> corsConfiguration);
	}
}
