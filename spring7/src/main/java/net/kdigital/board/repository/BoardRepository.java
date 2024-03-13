package net.kdigital.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.kdigital.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
