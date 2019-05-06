//package br.com.devchallenge.gateway.security;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * Config de segurança
// * @author erick.oliveira
// *
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	
//	protected void configure(HttpSecurity http) throws Exception {
//	    http.authorizeRequests()
//	      .anyRequest().permitAll()
//	      .and().formLogin().disable().httpBasic().disable();
//	}
//	
//	
//}
//	
