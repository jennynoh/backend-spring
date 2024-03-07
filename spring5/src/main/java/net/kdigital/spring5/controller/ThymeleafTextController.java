package net.kdigital.spring5.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.spring5.dto.FriendDTO;

@Controller
@Slf4j
public class ThymeleafTextController {
	
	@GetMapping("/display/text")
	public String text(Model model) {
		// 1) 기본자료형 출력을 위한 데이터 (기본자료형)
		String korean = "대한민국~!";
		String english = "Hello World!";
		int age = 25;
		double pi = Math.PI;
		String tag = "<marquee>돌이 굴러가유~</marquee>";
		String url = "http://www.naver.com";
		
		model.addAttribute("korean", korean);
		model.addAttribute("english", english);
		model.addAttribute("age", age);
		model.addAttribute("pi", pi);
		model.addAttribute("tag", tag);
		model.addAttribute("url", url);
		
		// 2) 객체자료형 출력을 위한 데이터 (객체자료형)
		FriendDTO f1 = new FriendDTO();
		FriendDTO f2 = new FriendDTO("삼장법사", 45, "010-2222-3333", LocalDate.now(), true);
		FriendDTO f3 = new FriendDTO("사오정", 20, "010-1111-3333", LocalDate.now(), false);
		
		model.addAttribute("f1", f1);
		model.addAttribute("f2", f2);
		model.addAttribute("f3", f3);
		
		// 3) 숫자데이터
		int n1 = 1_234_000;
		double n2 = 5_123_456.3456789;
		double n3 = 56.3456789;
		double n4 = 0.33;
		double n5 = 0.05;
		
		model.addAttribute("n1", n1);
		model.addAttribute("n2", n2);
		model.addAttribute("n3", n3);
		model.addAttribute("n4", n4);
		model.addAttribute("n5", n5);
		
		// 4) 날짜 관련 데이터
		Date date = new Date();  // java.util.date
		Calendar calendar = new GregorianCalendar();
		LocalDate localDate = LocalDate.now();
		LocalDateTime localTime = LocalDateTime.of(2024, 03, 04, 13, 45);
		
		model.addAttribute("date", date);
		model.addAttribute("calendar", calendar);
		model.addAttribute("localDate", localDate);
		model.addAttribute("localTime", localTime);
		
		return "thyme_text";
	}

}
