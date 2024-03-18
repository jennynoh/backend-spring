package net.kdigital.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.kdigital.board.entity.BoardEntity;
import net.kdigital.board.repository.BoardRepository;

@SpringBootTest
class Spring7ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired // bean을 주입받기 위한 annotation
	private BoardRepository repository;
	
	@Test
	void testInsertBoard() {
		for(int i=0; i<50; ++i) {
			BoardEntity b = new BoardEntity();
			b.setBoardTitle("언제까지 해야하지"+(i+1));
			b.setBoardWriter("호롤로");
			b.setBoardContent("test가 될까?~욥");
			
			repository.save(b);
		}
	}

}
