package net.kdigital.board.controller;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.BoardDTO;
import net.kdigital.board.service.BoardService;
import net.kdigital.board.util.PageNavigator;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {
	private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 파일의 저장경로 읽어오기 
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	// 한 페이지당 글의 갯수 - applicationproperties에서 읽어오기 
	@Value("${user.board.pageLimit}")
	int pageLimit;
	
	
	/* 글 목록 요청 
	 * 1. index에서 넘어오는 경우 searchFilter, searchKeyword 기본값 세팅 
	 *  - 1페이지 요청 
	 * 2. 검색해서 넘어오는 경우 searchFilter, searchKeyword 값 사용
	 * 	- 1페이지 요청 
	 * 3. 하단에 페이지 그룹을 선택할 경우 그 값을 사용 
	 */
	@GetMapping("/boardList")
	public String boardList(
			// spring에서 제공하는 기능, 없으면 3중 subquery쳐서 직접 작업해야함
			// 변수 page: 사용자 지정 변수명, pageable 인터페이스 
			@PageableDefault(page=1) Pageable pageable,
			@RequestParam(name="searchFilter", defaultValue="") String searchFilter,
			@RequestParam(name="searchKeyword", defaultValue="") String searchKeyword,
			Model model) {
		// pageable parameter 추가해서 반환 
		Page<BoardDTO> list = boardService.selectAll(pageable, searchFilter, searchKeyword);
//		List<BoardDTO> list = boardService.selectAll( searchFilter, searchKeyword);
		int totalPages = (int)list.getTotalPages();
		int page = pageable.getPageNumber();
		
		
		PageNavigator navi = new PageNavigator(pageLimit, page, totalPages);
		
		model.addAttribute("list", list);
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("navi", navi);
		
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
		log.info("첨부파일명: {}", boardDTO.getUploadFile().getOriginalFilename());
		log.info("첨부파일 크기: {}", boardDTO.getUploadFile().getSize());
		log.info("첨부파일 타입: {}", boardDTO.getUploadFile().getContentType());
		boardService.insertBoard(boardDTO);
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/boardDetail")
	public String boardDetail(
			@RequestParam(name="boardNum") Long boardNum,
			@RequestParam(name="searchFilter", defaultValue="") String searchFilter,
			@RequestParam(name="searchKeyword", defaultValue="") String searchKeyword,
			Model model) {
		BoardDTO boardDTO = boardService.selectOne(boardNum);
		boardService.incrementHitcount(boardNum);
		model.addAttribute("board", boardDTO);
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("searchKeyword", searchKeyword);
		return "board/board_detail";
	}
	
	// 글 삭제 
	@GetMapping("/boardDelete")
	public String boardDelete(
			@RequestParam(name="boardNum") Long boardNum,
			@RequestParam(name="searchFilter") String searchFilter,
			@RequestParam(name="searchKeyword") String searchKeyword,
			RedirectAttributes rttr) {
		boardService.deleteOne(boardNum);
		rttr.addAttribute("searchFilter", searchFilter);
		rttr.addAttribute("searchKeyword", searchKeyword);
		return "redirect:/board/boardList";
	}
	
	// 수정화면 요청 
	@GetMapping("/boardUpdate")
	public String boardUpdate(
			@RequestParam(name="boardNum") Long boardNum,
			@RequestParam(name="searchFilter") String searchFilter,
			@RequestParam(name="searchKeyword") String searchKeyword,
			Model model) {
		BoardDTO boardDTO = boardService.selectOne(boardNum);
		model.addAttribute("board", boardDTO);
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("searchKeyword", searchKeyword);
		return "board/board_update";
	}
	
	// db에 데이터 수정 요청 
	@PostMapping("/boardUpdate")
	public String boardUpdate(
			@ModelAttribute BoardDTO boardDTO,
			@RequestParam(name="searchFilter") String searchFilter,
			@RequestParam(name="searchKeyword") String searchKeyword,
			RedirectAttributes rttr) {
		boardService.updateOne(boardDTO);
		rttr.addAttribute("boardNum", boardDTO.getBoardNum());
//		log.info("============= {}", boardDTO.toString());
		rttr.addAttribute("searchFilter", searchFilter);
		rttr.addAttribute("searchKeyword", searchKeyword);
		return "redirect:/board/boardDetail";
	}
	
	// 전달받은 게시판 번호의 파일 다운로드 
	@GetMapping("/download")
	public String download(
			@RequestParam(name="boardNum") Long boardNum
			, HttpServletResponse response) {
		BoardDTO boardDTO = boardService.selectOne(boardNum);
		String originalFileName = boardDTO.getOriginalFileName();
		String savedFileName = boardDTO.getSavedFileName();
		
		log.info("original: {}, saved: {}", originalFileName, savedFileName);
		log.info("업로드패스: {}", uploadPath);
		
		// header 세팅 
		try {
			String tempName = URLEncoder.encode(
					originalFileName, StandardCharsets.UTF_8.toString());
			response.setHeader("Content-Disposition", "attachment;filename="+tempName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String fullPath = uploadPath+"/"+savedFileName;
		
		// 다운로드받기 위한 stream 설정 
		FileInputStream filein = null;
		ServletOutputStream fileout = null;
		
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();
			
			// 내부에 socket 통신 존재 
			// 반복적으로 read -> write.... spring 제공 기능 
			// filein 읽어서 fileout으로 내보내기 반복 
			FileCopyUtils.copy(filein, fileout);
			fileout.close();
			filein.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
