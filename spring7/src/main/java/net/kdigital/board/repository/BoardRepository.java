package net.kdigital.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.kdigital.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	
	Page<BoardEntity> findByBoardTitleContaining(String searchKeyword, PageRequest pageRequest);
	Page<BoardEntity> findByBoardContentContaining(String searchKeyword, PageRequest pageRequest);
	Page<BoardEntity> findByBoardWriterContaining(String searchKeyword, PageRequest pageRequest);
	
	@Query("SELECT b FROM BoardEntity b WHERE b.boardTitle LIKE %:keyword% OR b.boardContent LIKE %:keyword% OR b.boardWriter LIKE %:keyword%")
	Page<BoardEntity> findByAllContaining(@Param("keyword") String searchKeyword, PageRequest pageRequest);

	
	@Query("SELECT b FROM BoardEntity b WHERE b.boardTitle LIKE %:keyword% OR b.boardContent LIKE %:keyword%")
	Page<BoardEntity> findByTitleContentContaining(@Param("keyword") String searchKeyword, PageRequest pageRequest);
	
	@Query("SELECT b FROM BoardEntity b")
	Page<BoardEntity> findAll(PageRequest of);
	/*
	 * Offending method: 
	 * public abstract org.springframework.data.domain.Page 
	 * net.kdigital.board.repository.BoardRepository.findAll(org.springframework.data.domain.PageRequest,org.springframework.data.domain.Sort)
	 */
}
