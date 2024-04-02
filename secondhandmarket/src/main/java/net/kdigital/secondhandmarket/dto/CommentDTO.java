package net.kdigital.secondhandmarket.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.secondhandmarket.entity.CommentEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CommentDTO {
	private Long commentNum;
	private Long boardNum;
	private String memberId;
	private String commentText;
	private LocalDateTime inputDate;
	
	public static CommentDTO toDTO(CommentEntity commentEntity, Long boardNum) {
		return CommentDTO.builder()
				.commentNum(commentEntity.getCommentNum())
				.boardNum(commentEntity.getBoardEntity().getBoardNum())
				.memberId(commentEntity.getMemberId())
				.commentText(commentEntity.getCommentText())
				.inputDate(commentEntity.getInputDate())
				.build();
	}
	
}

