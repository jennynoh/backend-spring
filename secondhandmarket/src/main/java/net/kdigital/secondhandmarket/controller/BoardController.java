package net.kdigital.secondhandmarket.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.secondhandmarket.dto.BoardDTO;
import net.kdigital.secondhandmarket.service.BoardService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	public final BoardService boardService;
	
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
		boardService.boardWrite(boardDTO);
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/boardDetail")
	public String boardDetail(
			@RequestParam(name="boardNum") Long boardNum
			, Model model) {
		BoardDTO boardDTO = boardService.selectOne(boardNum);
		model.addAttribute("board", boardDTO);
		return "board/board_detail";
	}
	
	@GetMapping("/boardDelete")
	public String boardDelete(
			@RequestParam(name="boardNum") Long boardNum) {
		boardService.deleteOne(boardNum);
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/boardBuy")
	public String boardBuy(
			@RequestParam(name="boardNum") Long boardNum
			, @RequestParam(name="buyerId") String buyerId) {
		boardService.buy(boardNum, buyerId);
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/boardSearch")
	public String boardSearch(Model model) {
		List<BoardDTO> list = boardService.selectAll();
		model.addAttribute("list", list);
		return "board/board_search";
	}
	
	@GetMapping("/searchCategory")
	@ResponseBody
	public List<BoardDTO> searchCategory(
			@RequestParam(name="category") String category) {
		List<BoardDTO> boardList = boardService.searchCategory(category);
		return boardList;
	}
	
	@GetMapping("/searchKeyword")
	@ResponseBody
	public List<BoardDTO> searchKeyword(
			@RequestParam(name="keyword") String keyword){
		List<BoardDTO> boardList = boardService.searchKeyword(keyword);
		return boardList;
		
	}
	

}
