package net.kdigital.secondhandmarket.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.secondhandmarket.dto.BoardDTO;
import net.kdigital.secondhandmarket.entity.BoardEntity;
import net.kdigital.secondhandmarket.repository.BoardRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;

	public BoardDTO selectOne(Long boardNum) {
		Optional<BoardEntity> entity = boardRepository.findById(boardNum);
		if(entity.isPresent()) {
			return BoardDTO.toDTO(entity.get());
		}
		return null;
	}

	@Transactional
	public void deleteOne(Long boardNum) {
		boardRepository.deleteById(boardNum);
		
	}

	@Transactional
	public void boardWrite(BoardDTO boardDTO) {
		BoardEntity entity = BoardEntity.toEntity(boardDTO);
		entity.setInputDate(LocalDateTime.now());
		boardRepository.save(entity);
	}

	public List<BoardDTO> selectAll() {
		List<BoardEntity> entityList = boardRepository.findSellList();
		List<BoardDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> dtoList.add(BoardDTO.toDTO(entity)));
		
		return dtoList;
	}

	@Transactional
	public void buy(Long boardNum, String buyerId) {
		Optional<BoardEntity> targetEntity = boardRepository.findById(boardNum);
		if(targetEntity.isPresent()) {
			targetEntity.get().setSoldout("Y");
			targetEntity.get().setBuyerId(buyerId);
		}
	}

	public List<BoardDTO> searchCategory(String category) {
		List<BoardEntity> entityList = boardRepository.findByCategory(category);
		List<BoardDTO> dtoList = new ArrayList<>();
		entityList.forEach((item) -> dtoList.add(BoardDTO.toDTO(item)));
		
		return dtoList;
	}

	public List<BoardDTO> searchKeyword(String keyword) {
		List<BoardEntity> entityList = boardRepository.findByTitleContaining(keyword);
		List<BoardDTO> dtoList = new ArrayList<>();
		entityList.forEach((item) -> dtoList.add(BoardDTO.toDTO(item)));
		
		return dtoList;
	}
	
	
	
	
}
