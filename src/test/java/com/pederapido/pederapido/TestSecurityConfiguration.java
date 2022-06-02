package com.pederapido.pederapido;

import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@TestConfiguration
@Order(1)
public class TestSecurityConfiguration implements WebSecurityConfigurer<WebSecurity> {

	@Override
	public void init(WebSecurity builder) throws Exception {
		builder.ignoring().requestMatchers(
			      new AntPathRequestMatcher("/**"));		
	}

	@Override
	public void configure(WebSecurity builder) throws Exception {
		
	}

}
