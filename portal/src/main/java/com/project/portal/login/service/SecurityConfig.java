package com.project.portal.login.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests() 
			.antMatchers("/login").permitAll() // 로그인은 모든 사용자에게 허용
			// 각각의 사용자의 권한으로 시장하는 매핑 각각의 사용자가 가진 권한에 맞춰서 적용
			.antMatchers("/student/**").hasAuthority("STUDENT")
			.antMatchers("/professor/**").hasAuthority("PROFESSOR")
			.antMatchers("/admin/**").hasAuthority("ADMIN")
			.and()
				.headers()
				.addHeaderWriter(new XFrameOptionsHeaderWriter(
						XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
			.and()
				.formLogin()
				.loginPage("/login") // 로그인 페이지 지정
				.successForwardUrl("/login_success_handler") // 로그인 성공 시 포워드되는 URL (세션값 등 지정)
				//.failureUrl("/access_denied") // 로그인 실패 시 별도로 처리 필요할 경우
			// CrossSite Request Forgery (disable 해주지 않을 경우 POST 매핑에서 문제 발생)
			.and()
				.csrf().disable()
			.logout()
				.logoutUrl("/logout") // 로그아웃 요청 URL
				.logoutSuccessUrl("/login") // 로그아웃이 성공하면 다시 로그인 페이지로 돌아감
				.invalidateHttpSession(true).deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제 허용
		;
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
