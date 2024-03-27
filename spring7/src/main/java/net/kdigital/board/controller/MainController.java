package net.kdigital.board.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.LoginUserDetails;

@Controller
@Slf4j
public class MainController {
	
	@GetMapping({"", "/"})
	public String index(
			@AuthenticationPrincipal LoginUserDetails loginUser
			, Model model) {
		// loginUser이 null이면 getUserName()을 할 수 없어서 null pointer exception 발생 
		if(loginUser != null)
			model.addAttribute("userName", loginUser.getUserName());
		return "index";
	}

}
