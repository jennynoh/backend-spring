package net.kdigital.secondhandmarket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.secondhandmarket.dto.CommentDTO;
import net.kdigital.secondhandmarket.entity.BoardEntity;
import net.kdigital.secondhandmarket.entity.CommentEntity;
import net.kdigital.secondhandmarket.repository.BoardRepository;
import net.kdigital.secondhandmarket.repository.CommentRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
	public final CommentRepository commentRepository;
	private final BoardRepository boardRepository;

	public CommentDTO commentInsert(CommentDTO commentDTO) {
		Optional<BoardEntity> boardEntity = boardRepository.findById(commentDTO.getBoardNum());
		if(boardEntity.isPresent()) {
			BoardEntity entity = boardEntity.get();
			CommentEntity commentEntity = CommentEntity.toEntity(commentDTO, entity);
			
			CommentEntity temp = commentRepository.save(commentEntity);
			return CommentDTO.toDTO(temp, temp.getBoardEntity().getBoardNum());
		} else return null;
	}

	public List<CommentDTO> commentAll(Long boardNum) {
		BoardEntity entity = boardRepository.findById(boardNum).get();
		List<CommentEntity> commentList = commentRepository.findAllByBoardEntityOrderByCommentNumAsc(entity);
		List<CommentDTO> dtoList = new ArrayList<>();
		commentList.forEach((item) -> dtoList.add(CommentDTO.toDTO(item, boardNum)));
 		return dtoList;
	}
	
	

}
