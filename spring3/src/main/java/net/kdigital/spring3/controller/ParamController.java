package net.kdigital.spring3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ParamController {

	@GetMapping({"/param/send", "/param/sendForm"})
	public String param(
			// param을 전달하지 않으면 오류남 -> defaultValue 설정 시 param 없어도 오류 x
			@RequestParam(name="name", defaultValue="none") String name,
			@RequestParam(name="age", defaultValue="0") int age,
			Model model // SpringFramework에게 받는 매개변수 
			) {
		log.info("전송받은 데이터: name: {}, age: {}", name, age);
		
		// forwarding 방식으로 데이터 반환 
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "result"; // 보낸 데이터에 대한 응답: result.html 
	}
}
