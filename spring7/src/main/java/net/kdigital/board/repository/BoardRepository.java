package net.kdigital.board.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.kdigital.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	
	List<BoardEntity> findByBoardTitleContaining(String searchKeyword, Sort sort);
	List<BoardEntity> findByBoardContentContaining(String searchKeyword, Sort sort);
	List<BoardEntity> findByBoardWriterContaining(String searchKeyword, Sort sort);
	
	@Query("SELECT b FROM BoardEntity b WHERE b.boardTitle LIKE %:keyword% OR b.boardContent LIKE %:keyword% OR b.boardWriter LIKE %:keyword%")
	List<BoardEntity> findByAllContaining(@Param("keyword") String searchKeyword, Sort sort);

	
	@Query("SELECT b FROM BoardEntity b WHERE b.boardTitle LIKE %:keyword% OR b.boardContent LIKE %:keyword%")
	List<BoardEntity> findByTitleContentContaining(@Param("keyword") String searchKeyword, Sort sort);
	
}
