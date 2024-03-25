package net.kdigital.board.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.BoardDTO;
import net.kdigital.board.entity.BoardEntity;
import net.kdigital.board.repository.BoardRepository;
import net.kdigital.board.util.FileService;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	// 업로드된 파일이 저장될 directory 경로
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	@Value("${user.board.pageLimit}")
	int pageLimit;
	
	
	// 1. DB 전체조회 
	public Page<BoardDTO> selectAll(Pageable pageable, String searchFilter, String searchKeyword) {
		// pageable 추가 - 2024.03.18
		int page = pageable.getPageNumber()-1;  // page 위치값 0부터 시작
		 

		// Java Reflection 기능을 이용할 수도 있음 
		// List<BoardEntity> entityList = null;
		Page<BoardEntity> entityList = null;
		// page
		switch (searchFilter) {
		case "all": 
			entityList = boardRepository.findByAllContaining(searchKeyword, 
					PageRequest.of(page, pageLimit)); 
			// sort 쿼리 제외 
			// Sort.by(Sort.Direction.DESC, "boardNum")
			break;
		case "title": 
			entityList = boardRepository.findByBoardTitleContaining(searchKeyword, 
					PageRequest.of(page, pageLimit)); 
			break;
		case "content": 
			entityList = boardRepository.findByBoardContentContaining(searchKeyword, 
					PageRequest.of(page, pageLimit)); 
			break;
		case "titleContent": 
			entityList = boardRepository.findByTitleContentContaining(searchKeyword, 
					PageRequest.of(page, pageLimit)); 
			break;
		case "writer": 
			entityList = boardRepository.findByBoardWriterContaining(searchKeyword, 
					PageRequest.of(page, pageLimit)); 
			break;
		default:
			entityList = boardRepository.findAll(PageRequest.of(page, pageLimit)); break;
		}
		
//		List<BoardEntity> entityList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
//		List<BoardDTO> dtoList = new ArrayList<>();
//		entityList.forEach((entity) -> dtoList.add(BoardEntity.toDTO(entity)));
//		return null;
		
		
		// 2024.03.18 - pageable  추가 
//		 System.out.println("글 내용(getContent) :" + entityList.getContent());
//	     System.out.println("글 개수(getTotalElements) :" + entityList.getTotalElements());
//	     System.out.println("요청한 페이지 번호(getNumber) :" + entityList.getNumber());
//	     System.out.println("총 페이지 수(getTotalPages) :" + entityList.getTotalPages());
//	     System.out.println("한 페이지 글자 개수(getSize==pageLimit) :" + entityList.getSize());
//	     System.out.println("이전 페이지(hasPrevious) :" + entityList.hasPrevious());
//	     System.out.println("다음 페이지(hasNext) :" + entityList.hasNext());
//	     System.out.println("첫번째 페이지(isFirst) :" + entityList.isFirst());
//	     System.out.println("마지막 페이지(isLast) :" + entityList.isLast());

	      
	     // entityfmf dto로 변환하여 List에 담는 작업
	     // entityList.forEach((entity) -> dtoList.add(BoardDTO.toDTO(entity)));
		Page<BoardDTO> dtoList = null;
	    dtoList = entityList.map(board ->
	    	new BoardDTO(board.getBoardNum(),
	    			board.getBoardWriter(),
	    			board.getBoardTitle(),
	    			board.getHitCount(),
	    			board.getCreateDate(),
	    			board.getOriginalFileName()));

	      return dtoList;
	   
	}

	
	// 2. DB에 게시글 저장 
	public void insertBoard(BoardDTO boardDTO) {
		// to be deleted after login func dev 
		boardDTO.setBoardWriter("김뽀삐");		
		log.info("저장경로: {}", uploadPath);
		
		// 첨부파일이 있으면 
		if(!boardDTO.getUploadFile().isEmpty()) {
			String savedFileName = FileService.saveFile(boardDTO.getUploadFile(), uploadPath);
			String originalFileName = boardDTO.getUploadFile().getOriginalFilename();
			
			// 파일명 setting
			boardDTO.setOriginalFileName(originalFileName);
			boardDTO.setSavedFileName(savedFileName);
		}
		
		BoardEntity boardEntity = BoardDTO.toEntity(boardDTO);
		boardRepository.save(boardEntity);
	}
	
	// 3. BoardNum으로 DTO 반환
	public BoardDTO selectOne(Long boardNum) {
		Optional<BoardEntity> entity = boardRepository.findById(boardNum);
		if(entity.isPresent()) {
			return BoardEntity.toDTO(entity.get());
		} else return null;
	}


	// 4. DB에서 boardNum에 해당하는 레코드 삭제
	@Transactional
	public void deleteOne(Long boardNum) {
		// 글번호에 해당하는 글 읽어옴 
		Optional<BoardEntity> entity = boardRepository.findById(boardNum);
		if(entity.isPresent()) {
			BoardEntity board = entity.get();
			String savedFileName = board.getSavedFileName();
			log.info("첨부파일명: {}", savedFileName);
			
			if(savedFileName == null) {
				boardRepository.deleteById(boardNum);
				return;
			} 
			String fullPath = uploadPath + "/" + savedFileName;
			FileService.deleteFile(fullPath);
			boardRepository.deleteById(boardNum);
			// fileService에서 파일도 삭제 

		}
		
	}


	// 5. DB에 데이터 수정요청: 파일 찾고 -> 수정하고 -> 삭제하고 -> 저장 
	@Transactional
	public void updateOne(BoardDTO boardDTO) {		
		MultipartFile uploadFile = boardDTO.getUploadFile();
		String originalFileName = null;
		String savedFileName  = null;
		String oldSavedFileName = null;
	
		// 수정된 글에 파일을 첨부한 경우: 파일 저장, 이름 추출 
		if(!uploadFile.isEmpty()) {
			originalFileName = uploadFile.getOriginalFilename();
			savedFileName = FileService.saveFile(uploadFile, uploadPath);
		}
		
		// 원본 글에 대한 정보 
		Optional<BoardEntity> entity = boardRepository.findById(boardDTO.getBoardNum());
		if(entity.isPresent()) {
			BoardEntity boardEntity = entity.get();
			oldSavedFileName = boardEntity.getSavedFileName();
			
			// 원본에 첨부파일 존재 && 업로드한 파일 존재 
			if(oldSavedFileName != null && !uploadFile.isEmpty()) {
				// 원본 파일 삭제 
				String fullPath = uploadPath + "/" + oldSavedFileName; // 원본 첨부파일 저장 경로 
				FileService.deleteFile(fullPath); // path에 있는 파일 삭제
			}
			
			// 새로 업로드 된 파일이 있으면 새로운 값을 넣어줌 
			if(!uploadFile.isEmpty()) {
				boardEntity.setOriginalFileName(originalFileName);
				boardEntity.setSavedFileName(savedFileName);
			}
			
			boardEntity.setBoardTitle(boardDTO.getBoardTitle());
			boardEntity.setBoardContent(boardDTO.getBoardContent());
			boardEntity.setUpdateDate(LocalDateTime.now());	
		}
		
	}
	
	// 조회수 증가 
	@Transactional
	public void incrementHitcount(Long boardNum) {
		Optional<BoardEntity> entity = boardRepository.findById(boardNum);
		
		if(entity.isPresent()) {
			BoardEntity boardEntity = entity.get();
			boardEntity.setHitCount(boardEntity.getHitCount()+1);
		}
	}


	@Transactional
	public int increaseLike(Long boardNum) {
		BoardEntity targetEntity = boardRepository.findById(boardNum).get();
		int originLike = targetEntity.getLikeCount();
		targetEntity.setLikeCount(originLike+1);
		return targetEntity.getLikeCount();
		
	}
	
	

}
