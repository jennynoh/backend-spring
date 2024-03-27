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
	private String boardWriter;  // 사용자 id (userId)
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
	
	 // 페이징을 위한 생성자 생성
	 // boardList에서 사용할 내용으로 추림
	 public BoardDTO(Long boardNum, String boardWriter, String boardTitle, int hitCount, LocalDateTime createDate,
	       String originalFileName) {
	      super();
	      this.boardNum = boardNum;
	      this.boardWriter = boardWriter;
	      this.boardTitle = boardTitle;
	      this.hitCount = hitCount;
	      this.createDate = createDate;
	      this.originalFileName = originalFileName;
	   }
	
	
}
