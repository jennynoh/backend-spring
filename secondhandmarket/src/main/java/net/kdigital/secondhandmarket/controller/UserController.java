package net.kdigital.secondhandmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.secondhandmarket.dto.UserDTO;
import net.kdigital.secondhandmarket.service.UserService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@GetMapping("/user/join")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/user/joinProc")
	public String joinProc(@ModelAttribute UserDTO userDTO) {
		userDTO.setRolename("ROLE_USER");
		userDTO.setEnabled(true);
		
		userService.joinProc(userDTO);
		log.info("회원가입 요청: {}", userDTO.toString());
		return "redirect:/";
	}
	
	@GetMapping("/user/login")
	public String login(
			@RequestParam(value="error", required=false) String error
			, @RequestParam(value="errMessage", required=false) String errMessage
			, Model model) {
		model.addAttribute("error", error);
		model.addAttribute("errMessage", errMessage);
		
		return "user/login";
	}

}
