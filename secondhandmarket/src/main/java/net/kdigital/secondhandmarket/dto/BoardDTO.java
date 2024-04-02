package net.kdigital.secondhandmarket.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.secondhandmarket.entity.BoardEntity;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
	private Long boardNum;
	private String memberId;
	private String title;
	private String contents;
	private LocalDateTime inputDate;
	private String category;
	private String soldout;
	private String buyerId;
	
	public static BoardDTO toDTO(BoardEntity boardEntity) {
		return BoardDTO.builder()
				.boardNum(boardEntity.getBoardNum())
				.memberId(boardEntity.getMemberId())
				.title(boardEntity.getTitle())
				.contents(boardEntity.getContents())
				.inputDate(boardEntity.getInputDate())
				.category(boardEntity.getCategory())
				.soldout(boardEntity.getSoldout())
				.buyerId(boardEntity.getBuyerId())
				.build();
	}
	
}

