package net.kdigital.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.board.entity.CommentEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CommentDTO {
	private Long commentNum;
	private Long boardNum;
	private String commentWriter;
	private String commentText;
	private LocalDateTime createDate;
	
	public static CommentDTO toDTO(CommentEntity commentEntity, Long boardNum) {
		return CommentDTO.builder()
				.commentNum(commentEntity.getCommentNum())
				.boardNum(commentEntity.getBoardEntity().getBoardNum())
				.commentWriter(commentEntity.getCommentWriter())
				.commentText(commentEntity.getCommentText())
				.createDate(commentEntity.getCreateDate())
				.build();
	}

}
