package com.company.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class PublicUrlConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/registration", "/login", "/home", "/index", "/", "/user/save")
//                        "/index","/styles/**","/images/**","/scripts/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll();

	}
}
