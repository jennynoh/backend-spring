package net.kdigital.spring6.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.spring6.dto.FriendDTO;
import net.kdigital.spring6.service.FriendService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class FriendController {
	
	// service내의 값을 변경하지 않기 때문에 finla로 지정할 수 있음
	// final은 생성과 동시에 초기화 해야하지만 service는 new로 생성할 수 없음 
	// => @RequiredArgsContructor: final이 붙은 멤버를 생성할 때 사용 
	
	private final FriendService service;
	
	//생성자 주입방식 - service 객체 생성
	// before: @Autowired
	// new FriendService()하지 않고 spring이 생성해서 전달해줌 
//	public FriendController(FriendService service) {
//		this.service = service;
//	}
	
	
	
	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("friendDTO", new FriendDTO());
		return "insertForm"; // forwarding  
	}
	
	@PostMapping("/insert")
	public String insert(
			@Valid @ModelAttribute FriendDTO friendDTO, BindingResult bindingResult
			) {
		log.info("binding result: {}", bindingResult);
		if(bindingResult.hasErrors()) {
			log.info("친구정보 등록 실패: 오류포함");
			return "insertForm"; // 에러가 포함된 상태로 입력화면으로 이동 
		}
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
	
	@GetMapping("/deleteOne")
	public String deleteOne(
			@RequestParam(name="friendSeq", defaultValue="0") Long friendSeq
			) {
		log.info("삭제할 객체 seq: {}", friendSeq);
		service.deleteOne(friendSeq);
		return "redirect:/list"; // 친구정보 조회 페이지로 redirect
	}
	
	@GetMapping("/updateOne")
	public String updateOne(
			@RequestParam(name="friendSeq", defaultValue="0") Long friendSeq,
			Model model) {
		
		FriendDTO friendDTO = service.selectOne(friendSeq);
		log.info("수정할 data: {}", friendDTO.toString());
		
		model.addAttribute("friend", friendDTO);
		return "updateForm";
	}
	
	@PostMapping("/updateProc")
	public String updateProc(
			@ModelAttribute FriendDTO friendDTO) {
		log.info("수정된 정보: {}", friendDTO.toString());
		
		service.updateProc(friendDTO);
		
		return "redirect:/list";
	}
	

}
