package net.kdigital.board.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.BoardDTO;
import net.kdigital.board.entity.BoardEntity;
import net.kdigital.board.repository.BoardRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	// 1. DB 전체조회 
	public List<BoardDTO> selectAll() {
		log.info("Board Service 실행: selectAll()");
		
		List<BoardEntity> entityList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
		List<BoardDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> dtoList.add(BoardEntity.toDTO(entity)));
		return dtoList;
	}

	
	// 2. DB에 게시글 저장 
	public void insertBoard(BoardDTO boardDTO) {
		boardDTO.setBoardWriter("김뽀삐");
		boardDTO.setCreateDate(LocalDateTime.now());
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
	public void deleteOne(Long boardNum) {
		boardRepository.deleteById(boardNum);
	}


	// 5. DB에 데이터 수정요청: 찾고 -> 수정하고 -> 삭제하고 -> 저장 
	@Transactional
	public void updateOne(BoardDTO boardDTO) {
		boardDTO.setUpdateDate(LocalDateTime.now());
		Optional<BoardEntity> entity = boardRepository.findById(boardDTO.getBoardNum());
		if(entity.isPresent()) {
			BoardEntity targetEntity = entity.get();
			targetEntity.setBoardTitle(boardDTO.getBoardTitle());
			targetEntity.setBoardContent(boardDTO.getBoardContent());
			targetEntity.setUpdateDate(boardDTO.getUpdateDate());
		} 
	}
	
	

}
