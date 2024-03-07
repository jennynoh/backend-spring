package net.kdigital.spring5.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.spring5.dto.FriendDTO;

@Controller
@Slf4j
@RequestMapping("/display")
public class ThymeleafCollectionController {

	@GetMapping("/collection")
	public String collection(Model model) {
//		List<String> list = new ArrayList<>();
//		list.add("사과");
//		list.add("복숭아");
//		list.add("오렌지");
//		list.add("포도");
//		list.add("버나나");
		
		List<String> list = Arrays.asList("사과", "바나나", "복숭아", "오렌지", "포도");
		
		// forEach() receives lambda object as a parameter 
		list.forEach((item) -> System.out.println(item));
		
		String lunch = "순두부찌개, 라면, 콩국, 도시락, 카레";
		
		List<FriendDTO> friendList 
			= Arrays.asList(
					new FriendDTO("손오공", 25, "010-1111-2222", LocalDate.now(), true),
					new FriendDTO("삼장법사", 97, "010-2222-2222", LocalDate.now(), false),
					new FriendDTO("저팔계", 47, "010-3333-2222", LocalDate.now(), true)
					);
		
		model.addAttribute("friendList", friendList);
		
		Map<String, FriendDTO> map = new HashMap<>();
		map.put("son", new FriendDTO("손오공", 25, "010-1111-2222", LocalDate.now(), true));
		map.put("sam", new FriendDTO("삼장법사", 97, "010-2222-2222", LocalDate.now(), false));
		map.put("pig", new FriendDTO("저팔계", 47, "010-3333-2222", LocalDate.now(), true));
		
		model.addAttribute("list", list);
		model.addAttribute("map", map);
		model.addAttribute("lunch", lunch);
		
		List<String> numList = new ArrayList<>();
		for(int i=1; i<=30; ++i) numList.add("Current Number="+i);
		
		model.addAttribute("numList", numList);
		
		return "thyme_collection";
	}
}
