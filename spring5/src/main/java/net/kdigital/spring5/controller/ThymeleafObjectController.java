package net.kdigital.spring5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ThymeleafObjectController {

	@GetMapping("/display/object")
	public String object(Model model) {
		
		String korean = "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세";
		String english = "I have a dream, a song to sing. To help me cope with anything";
		
		model.addAttribute("korean", korean);
		model.addAttribute("english", english);
		return "thyme_object";
	}
	

 }

/*================================	
 * 요청의 중복된 부분 상위로 빼내기
 * =============================== 
@RequestMapping("/display")
public class ThymeleafObjectController {
	@GetMapping("/display/join")
	blah blah 
	
	@PostMapping("/display/signup")
	blah blah
} 
*/
