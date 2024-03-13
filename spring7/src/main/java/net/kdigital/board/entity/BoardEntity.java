package net.kdigital.board.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.board.dto.BoardDTO;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="board")
public class BoardEntity {

	@Id
	@SequenceGenerator(
			name="board_seq"
			, sequenceName="board_seq"
			, initialValue=1
			, allocationSize=1)
	@GeneratedValue(generator="board_seq")
	@Column(name="board_num")
	private Long boardNum;
	
	@Column(name="board_writer")
	private String boardWriter;
	
	@Column(name="board_title")
	private String boardTitle;
	
	@Column(name="board_content")
	private String boardContent;
	
	@Column(name="hit_count")
	private int hitCount;
	
	@Column(name="like_count")
	private int likeCount;
	
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	@Column(name="update_date")
	private LocalDateTime updateDate;
	
	public static BoardDTO toDTO(BoardEntity boardEntity) {
		return BoardDTO.builder()
				.boardNum(boardEntity.getBoardNum())
				.boardWriter(boardEntity.getBoardWriter())
				.boardTitle(boardEntity.getBoardTitle())
				.boardContent(boardEntity.getBoardContent())
				.hitCount(boardEntity.getHitCount())
				.likeCount(boardEntity.getLikeCount())
				.createDate(boardEntity.getCreateDate())
				.updateDate(boardEntity.getUpdateDate())
				.build();
	}
	
	
}



