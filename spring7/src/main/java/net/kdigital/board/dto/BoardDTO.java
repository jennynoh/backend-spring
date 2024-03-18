package net.kdigital.board.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

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
	
	// 파일이 첨부되었을 때 추가작업
	private MultipartFile uploadFile; // 첨부파일 
	private String originalFileName; // 원본파일명
	private String savedFileName; // 하드디스크에 저장되는 파일명 
	
	
	public static BoardEntity toEntity(BoardDTO boardDTO) {
		return BoardEntity.builder()
				.boardNum(boardDTO.getBoardNum())
				.boardWriter(boardDTO.getBoardWriter())
				.boardTitle(boardDTO.getBoardTitle())
				.boardContent(boardDTO.getBoardContent())
				.hitCount(boardDTO.getHitCount())
				.likeCount(boardDTO.getLikeCount())
				.createDate(boardDTO.getCreateDate())
				.updateDate(boardDTO.getUpdateDate()) // 없어도 되나..?
				.originalFileName(boardDTO.getOriginalFileName())
				.savedFileName(boardDTO.getSavedFileName())
				.build();
	}
	
	
}
