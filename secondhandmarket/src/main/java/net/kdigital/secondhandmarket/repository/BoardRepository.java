package net.kdigital.secondhandmarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.kdigital.secondhandmarket.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	@Query("SELECT b FROM BoardEntity b WHERE b.soldout = 'N' ORDER BY b.boardNum DESC")
	List<BoardEntity> findSellList();

	@Query("SELECT b FROM BoardEntity b WHERE b.category = :keyword")
	List<BoardEntity> findByCategory(@Param("keyword") String category);

	@Query("SELECT b FROM BoardEntity b WHERE b.title LIKE %:keyword%")
	List<BoardEntity> findByTitleContaining(@Param("keyword") String keyword);

}
