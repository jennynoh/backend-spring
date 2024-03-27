package net.kdigital.board.handler;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	// 만드는법: Source > Override/Implement Methods 
	// 로그인 오류에 대한 에러를 던져주는 메소드 
	// 클라이언트에서 응답하려면 응답객체가 있어야하기 때문에 response 객체를 던져준다. 
	// 요청객체와 응답객체를 함께 보내줌 
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// exception이 발생했을 때 (로그인 실패 시 )
		log.info("로그인 실패: {}", exception.getClass());
		// exception의 종류
		String errMessage = "";
		
		if(exception instanceof BadCredentialsException) {
			errMessage = exception.getMessage();
			errMessage += "\n아이디와 비밀번호를 확인하세요.";
		} else {
			errMessage = exception.getMessage();
			errMessage += "\n알수없는 이유. 관리자에게 문의하세요.";
		}
		errMessage = URLEncoder.encode(errMessage, "UTF-8");
		
		// failure 발생 시 이동할 url + error 여부 + error 메세지를 함께 보냄 
		// error, errMessage request 파라미터와 함께 전달됨!
		setDefaultFailureUrl("/user/login?error=true&errMessage="+errMessage);
		
		super.onAuthenticationFailure(request, response, exception);
	}
	

}
