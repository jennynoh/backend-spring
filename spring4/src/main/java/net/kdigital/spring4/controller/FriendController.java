package net.kdigital.spring4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.spring4.dto.FriendDTO;

@Controller
@Slf4j
public class FriendController {

	@PostMapping("/register")
	public String register(
			// parameter 한꺼번에 받기: ModelAttribute
			@ModelAttribute FriendDTO friendDTO,
			Model model
			) {
		log.info("{}", friendDTO.toString());
		
		model.addAttribute("friend", friendDTO);
		return "registerResult"; // forwarding 응답방식: model에 데이터를 실어 나를 수 있음 
	}
}
