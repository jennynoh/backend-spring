package net.kdigital.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.BoardDTO;
import net.kdigital.board.service.BoardService;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {
	private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		List<BoardDTO> list = boardService.selectAll();
		model.addAttribute("list", list);
		return "board/board_list";
	}
	
	@GetMapping("/boardWrite")
	public String boardWrite() {
		return "board/board_write";
	}
	
	@PostMapping("/boardWrite")
	public String boardWrite(
			@ModelAttribute BoardDTO boardDTO) {
		log.info("글 저장 요청: {}", boardDTO.toString());
		boardService.insertBoard(boardDTO);
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/boardDetail")
	public String boardDetail(
			@RequestParam(name="boardNum") Long boardNum,
			Model model) {
		BoardDTO boardDTO = boardService.selectOne(boardNum);
		model.addAttribute("board", boardDTO);
		return "board/board_detail";
	}
	
	// 글 삭제 
	@GetMapping("/boardDelete")
	public String boardDelete(
			@RequestParam(name="boardNum") Long boardNum) {
		boardService.deleteOne(boardNum);
		return "redirect:/board/boardList";
	}
	
	// 수정화면 요청 
	@GetMapping("/boardUpdate")
	public String boardUpdate(
			@RequestParam(name="boardNum") Long boardNum,
			Model model) {
		BoardDTO boardDTO = boardService.selectOne(boardNum);
		model.addAttribute("board", boardDTO);
		
		return "board/board_update";
	}
	
	// db에 데이터 수정 요청 
	@PostMapping("/boardUpdate")
	public String boardUpdate(
			@ModelAttribute BoardDTO boardDTO,
			RedirectAttributes rttr) {
		boardService.updateOne(boardDTO);
		rttr.addAttribute("boardNum", boardDTO.getBoardNum());
//		log.info("============= {}", boardDTO.toString());
		return "redirect:/board/boardDetail";
	}
	
	
	

}
