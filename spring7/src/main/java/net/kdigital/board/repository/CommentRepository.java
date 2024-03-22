package net.kdigital.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.board.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}
