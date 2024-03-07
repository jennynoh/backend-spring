package net.kdigital.spring6.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.spring6.dto.FriendDTO;
import net.kdigital.spring6.service.FriendService;

@Controller
@Slf4j
public class FriendController {
	
	private FriendService service;
	
	//생성자 주입방식 - service 객체 생성
	// before: @Autowired
	// new FriendService()하지 않고 spring이 생성해서 전달해줌 
	public FriendController(FriendService service) {
		this.service = service;
	}
	
	@GetMapping("/insert")
	public String insert() {
		return "insertForm"; // forwarding 
	}
	
	@PostMapping("/insert")
	public String insert(
			@ModelAttribute FriendDTO friendDTO
			) {
		service.insertFriend(friendDTO);
		
		log.info("{}", friendDTO.toString());
		
		// 입력하는 화면으로 이동 => GetMapping("/insert") 재요청 
		// redirect: 브라우저에게 get 방식으로 다시 요청하라고 알려줌 
		return "redirect:/"; // redirect 방식 
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		
		List<FriendDTO> friendList = service.selectAll();
		
		model.addAttribute("list", friendList);
		
		return "list";
	}

}
