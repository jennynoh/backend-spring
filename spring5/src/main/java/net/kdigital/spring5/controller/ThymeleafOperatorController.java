package net.kdigital.spring5.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.kdigital.spring5.dto.FriendDTO;

@Controller
@RequestMapping("/display")
public class ThymeleafOperatorController {

	@GetMapping("/operator")
	public String operator(Model model) {
		String name = "James Dean";
		String one = "한놈";
		String two = "두시기";
		String three = "석삼";
		
		int intNum = 22;
		double dbNum = 42.195;
		
		FriendDTO friend = new FriendDTO("마루치", 25, "010-111-222", LocalDate.of(2005, 03, 25), true);
		
		model.addAttribute("name", name);
		model.addAttribute("one", one);
		model.addAttribute("two", two);
		model.addAttribute("three", three);
		model.addAttribute("intNum", intNum);
		model.addAttribute("dbNum", dbNum);
		model.addAttribute("friend", friend);
		return "thyme_operator";
	}
}
