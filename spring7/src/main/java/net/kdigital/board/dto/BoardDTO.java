package net.kdigital.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.board.entity.BoardEntity;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContent;
	private int hitCount;
	private int likeCount;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	public static BoardEntity toEntity(BoardDTO boardDTO) {
		return BoardEntity.builder()
				.boardNum(boardDTO.getBoardNum())
				.boardWriter(boardDTO.getBoardWriter())
				.boardTitle(boardDTO.getBoardTitle())
				.boardContent(boardDTO.getBoardContent())
				.hitCount(boardDTO.getHitCount())
				.likeCount(boardDTO.getLikeCount())
				.createDate(boardDTO.getCreateDate())
				.updateDate(boardDTO.getUpdateDate())
				.build();
	}
	
}
