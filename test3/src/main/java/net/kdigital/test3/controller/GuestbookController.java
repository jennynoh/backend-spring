package net.kdigital.test3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.test3.dto.GuestbookDTO;
import net.kdigital.test3.service.GuestbookService;

@Controller
@Slf4j
public class GuestbookController {
	
	// db에 작업요청을 보낼 service 객체 추가
	private GuestbookService service;
	
	public GuestbookController(GuestbookService service) {
		this.service = service;
	}

	@GetMapping("/insert")
	public String insert() {
		return "insertForm";
	}
	
	@PostMapping("/insert")
	public String insert(
			@ModelAttribute GuestbookDTO guestbookDTO) {
		// guestbookDTO객체 db에 전달, 저장 
		service.insertGuestbook(guestbookDTO);
		return "redirect:/insert";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		// service에서 select * 결과 받아서 model로 전달 
		List<GuestbookDTO> guestbookList = service.selectAll();
		model.addAttribute("list", guestbookList);
		return "list";
	}
	
	@GetMapping("/valPwd")
	public String valPwd(Model model) {
		return "val_pwd";
	}
	
	@GetMapping("/deleteOne")
	public String deleteOne(
			@RequestParam(name="guestSeq", defaultValue="-1") Long guestSeq) {
		service.deleteOne(guestSeq);
		return "redirect:/list";
	}
	
	@GetMapping("/getMine")
	public String getMine(
			@RequestParam(name="userInput", defaultValue="0") String userInput,
			Model model) {
		List<GuestbookDTO> mybookList = service.selectMine(userInput); 
		model.addAttribute("list", mybookList);
		
		return "mybook";
	}
	
	@GetMapping("/updateOne")
	public String updateOne(
			@RequestParam(name="guestSeq", defaultValue="-1") Long guestSeq,
			Model model) {
		log.info("수정할 guestSeq: {}", guestSeq);
		
		GuestbookDTO guestbookDTO = service.selectOne(guestSeq);
		log.info("수정할 data: {}", guestbookDTO.toString());
		
		model.addAttribute("updateTarget", guestbookDTO);
		return "updateForm";
	}
	
	@PostMapping("/updateOne")
	public String updateOne(
			@RequestParam(name="guestSeq", defaultValue="-1") Long guestSeq,
			@RequestParam(name="updatedText", defaultValue="") String updatedText,
			RedirectAttributes rttr) {
		
		GuestbookDTO targetDTO = service.updateOne(guestSeq, updatedText);
		rttr.addAttribute("userInput", targetDTO.getPwd());
		return "redirect:/getMine";
	}
	
	
	
}
