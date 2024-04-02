package net.kdigital.secondhandmarket.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.secondhandmarket.dto.CommentDTO;
import net.kdigital.secondhandmarket.service.CommentService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
	public final CommentService commentService;
	
	@PostMapping("/insertComment")
	@ResponseBody
	public CommentDTO insertComment(@ModelAttribute CommentDTO commentDTO) {
		CommentDTO saveResult = commentService.commentInsert(commentDTO);
		
		return saveResult;
	}
	
	@GetMapping("/allComments")
	@ResponseBody
	public List<CommentDTO> allComments(
			@RequestParam(name="boardNum") Long boardNum) {
		List<CommentDTO> commentList = commentService.commentAll(boardNum);
		return commentList;
	}

}
