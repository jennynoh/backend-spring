package net.kdigital.board.controller;

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
import net.kdigital.board.dto.CommentDTO;
import net.kdigital.board.service.CommentService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
	public final CommentService commentService;
	
	@PostMapping("/insertComment")
	@ResponseBody
	public CommentDTO insertComment(@ModelAttribute CommentDTO commentDTO) {
		log.info("{}", commentDTO);
		CommentDTO saveResult = commentService.commentInsert(commentDTO);
		
		return saveResult;
	}
	
	@GetMapping("/allComments")
	@ResponseBody
	public List<CommentDTO> allComments(
			@RequestParam(name="boardNum") Long boardNum){
		List<CommentDTO> commentList = commentService.commentAll(boardNum);
		log.info("결과 ===> {}", commentList);
		return commentList;
	}
	
	@GetMapping("/deleteComment")
	@ResponseBody
	public boolean deleteComment(
			@RequestParam(name="commentNum") Long commentNum) {
		log.info("댓글삭제 요청: id on {}", commentNum);
		return commentService.deleteComment(commentNum);	
	}
	

}
