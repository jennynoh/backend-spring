package net.kdigital.spring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // Spring Container의 관리: 객체 생성, 소멸 등의 라이프사이클 관리 
public class MainController {
	
	@GetMapping({"", "/"})  
	// get 방식으로 호출했을 때 실행됨 
	// "": port 번호 뒤에 빈칸, "/": port 번호 뒤에 /일때도 모두 return 
	// 요청경로는 유일해야함 ("", "/")
	public String index() {
		
		return "index"; // template/index.html을 의미함 
	}
}
