package net.kdigital.secondhandmarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.secondhandmarket.entity.BoardEntity;
import net.kdigital.secondhandmarket.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	List<CommentEntity> findAllByBoardEntityOrderByCommentNumAsc(BoardEntity entity);

}
