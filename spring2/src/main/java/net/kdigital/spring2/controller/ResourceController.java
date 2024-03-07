package net.kdigital.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResourceController {
	
	@GetMapping("/image")
	public String image() {
		return "image"; // image.html 파일을 반환 
	}
	
	@GetMapping("/css")
	public String css() {
		return "css";
	}
	
	@GetMapping("/javascript")
	public String javascript() {
		return "javascript";
	}
}
