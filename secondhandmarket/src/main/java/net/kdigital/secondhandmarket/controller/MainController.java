package net.kdigital.secondhandmarket.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.secondhandmarket.dto.LoginUserDetails;

@Controller
@Slf4j
public class MainController {
	@GetMapping({"", "/"})
	public String index(
			@AuthenticationPrincipal LoginUserDetails loginUser
			, Model model) {
		if(loginUser != null)
			model.addAttribute("memberId", loginUser.getUsername());
		
		return "index";
	}

}
