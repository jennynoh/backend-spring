package net.kdigital.test2.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // new를 통하지 않고 자동으로 객체 생성 => bean! (: 스프링 프레임워크=Spring Container가 관리하는 객체)
public class MainController {
	
	@GetMapping({"", "/"}) 
	// 루트 요청: localhost (엔터) or localhost/ (엔터)
	public String index(Model model) {
		
		model.addAttribute("name", "홍길동");
		model.addAttribute("age", 25);
		model.addAttribute("join_date", LocalDate.now());
		return "index"; // forwarding으로 응답, 메세지도 실어 보낼 수 있음 
		//redirect 방식도 있음 
	}
	
	@GetMapping("/input")
	public String input(
			@RequestParam(name="name", defaultValue="none") String name,
			@RequestParam(name="age", defaultValue="0") int age,
			Model model // 전달받은 값을 다시 되돌려줌 = echo program
			) {
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		model.addAttribute("join_date", LocalDate.now());
		return "index";
	}
	

}
