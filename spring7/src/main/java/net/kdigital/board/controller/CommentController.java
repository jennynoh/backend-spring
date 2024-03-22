package net.kdigital.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentController {
	
	@PostMapping("/comment/commentInsert")
	@ResponseBody
	public void commentInsert() {
		
	}

}
