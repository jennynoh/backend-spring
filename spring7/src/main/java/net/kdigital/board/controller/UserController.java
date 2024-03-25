package net.kdigital.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.UserDTO;
import net.kdigital.board.service.UserService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	/**
	 * 회원가입을 위한 화면 요청 
	 * @return 
	 */
	@GetMapping("/user/join")
	public String join() {
		return "user/join";
	}
	
	/**
	 * 회원가입 처리 
	 * @param userDTO
	 * @return
	 */
	@PostMapping("/user/joinProc")
	public String joinProc(@ModelAttribute UserDTO userDTO) {
		log.info("{}", userDTO.toString());
		
		// role, enabled 여부 세팅 
		userDTO.setRoles("ROLE_USER");
		userDTO.setEnabled(true);
		
		userService.joinProc(userDTO);
		return "redirect:/";
	}
	
	/**
	 * 로그인 화면 요청 
	 * 주의) 로그인 처리는 Controller에 넣지 않음 (Security가 함) 
	 * @return
	 */
	@GetMapping("/user/login")
	public String login() {
		return "user/login";
	}

}
