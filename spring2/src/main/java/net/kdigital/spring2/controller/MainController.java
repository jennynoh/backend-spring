package net.kdigital.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j  // 로그찍는 기능 (from lombok)
public class MainController {

	@GetMapping({"", "/"})
	public String index() {
		log.info("도착!");
		return "index";
	}
}
