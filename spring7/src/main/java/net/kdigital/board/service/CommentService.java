package net.kdigital.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.board.dto.CommentDTO;
import net.kdigital.board.entity.BoardEntity;
import net.kdigital.board.entity.CommentEntity;
import net.kdigital.board.repository.BoardRepository;
import net.kdigital.board.repository.CommentRepository;

@Service  // spring container에 관리 객체로 등록됨 (bean)
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;

	public CommentDTO commentInsert(CommentDTO commentDTO) {
		// 댓글이 달린 게시글 확인 
		Optional<BoardEntity> boardEntity = boardRepository.findById(commentDTO.getBoardNum());
		if(boardEntity.isPresent()) {
			// CommentEntity에 멤버변수로 BoardEntity가 있어서 CommentEntity를 build하기 위해 전달 
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
		commentList.forEach((item)->dtoList.add(CommentDTO.toDTO(item, boardNum)));
		return dtoList;
	}

	public boolean deleteComment(Long commentNum) {
		commentRepository.deleteById(commentNum);
		if(commentRepository.findById(commentNum)==null) return true;
		else return false;
	}

}


