package net.kdigital.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.board.entity.BoardEntity;
import net.kdigital.board.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	List<CommentEntity> findAllByBoardEntityOrderByCommentNumAsc(BoardEntity entity);

	
}
