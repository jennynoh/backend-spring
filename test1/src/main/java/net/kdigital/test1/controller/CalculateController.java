package net.kdigital.test1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CalculateController {
	
	@GetMapping("/calculate")
	public String calculator(
			@RequestParam(name="num1", defaultValue="0") int num1,
			@RequestParam(name="num2", defaultValue="0") int num2,
			Model model
			) {
		int sum = num1 + num2;
		log.info("결과: {} + {} = {}", num1, num2, sum);
		
		model.addAttribute("num1", num1);
		model.addAttribute("num2", num2);
		model.addAttribute("sum", sum);
		
		return "result";
	}
}
