package net.kdigital.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration   // 설정 클래스임을 나타내는 annotation 
@EnableWebSecurity   // 웹 보안은 모두 이 클래스의 설정을 따름을 나차내는 annotation 
public class SecurityConfig {
	// 웹 보안에 관련해서 설정할 수 있는 객체: HttpSecurity 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 웹 요청에 대한 접근권한 설정 
		// 3.2 version : lambda 식으로 설정해줌 
		http.authorizeHttpRequests((auth) -> auth.requestMatchers("/"
																, "/board/boardList"
																, "/board/boardDetail"
																, "/user/join" // 회원가입 화면요청 
																, "/user/joinProc"  // 회원가입 처리요청 
																, "/user/login"  // 로그인 화면 요청 
																, "/comment/allComments"
																, "/images/**"
																, "/css/**"
																, "/script/**").permitAll() 
																// 인증절차 없이도 접근가능한 요청
												.requestMatchers("/admin/**").hasRole("ADMIN")
												.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
												.anyRequest().authenticated() // 나머지 요청은 인증해야함 
					); 
		
		// custom 로그인 화면 설정 
		http.formLogin((auth) -> auth.loginPage("/user/login") // 로그인 화면 요청 
									.usernameParameter("userId") // username = id , form에서 userId로 설정해야함
									.passwordParameter("userPwd") 
									.loginProcessingUrl("/user/loginProc") // 로그인 처리 요청 
									.defaultSuccessUrl("/").permitAll()  // 로그인 성공 시 요청 
					);
		
		// 로그아웃 설정 
		http.logout((auth) -> auth.logoutUrl("/user/logout")  // 로그아웃 처리 url 
								.logoutSuccessUrl("/")  // 로그아웃 성공시 이동 url 
								// http 세선에 로그인에 대한 정보를 저장함 so, http session 무효화  
								.invalidateHttpSession(true)
								// 제거할 쿠키 정보 전달 
								.deleteCookies("JSESSIONID")
				);
		
		// CSRF(Cross-Site Request Forgery: 사이트 위변조기능) 비활성화-개발하는 동안 무효화 
		// post 요청 시 토큰 발급을 안해도 되도록 개발하는 동안 설정 
		// POST, PUT, DELETE이 진행되지 않도록 개발하는 동안 disabled 시켜줌 -> 배포 시 활성화  
		http.csrf((auth) -> auth.disable());
		return http.build();
	}
	
	@Bean // 반환하는 타입을 bean으로 등록 
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
