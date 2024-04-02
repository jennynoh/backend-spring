package net.kdigital.secondhandmarket.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.secondhandmarket.dto.CommentDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="market_comment")
public class CommentEntity {
	@Id
	@SequenceGenerator(
			name="market_reply_seq"
			, sequenceName="market_reply_seq"
			, initialValue=1
			, allocationSize=1)
	@GeneratedValue(generator="market_reply_seq")
	@Column(name="comment_num")
	private Long commentNum;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="board_num")
	private BoardEntity boardEntity;
	
	@Column(name="member_id")
	private String memberId;
	
	@Column(name="comment_text")
	private String commentText;
	
	@Column(name="input_date")
	@CreationTimestamp
	private LocalDateTime inputDate;
	
	public static CommentEntity toEntity(CommentDTO commentDTO, BoardEntity boardEntity) {
		return CommentEntity.builder()
				.commentNum(commentDTO.getCommentNum())
				.memberId(commentDTO.getMemberId())
				.commentText(commentDTO.getCommentText())
				.inputDate(commentDTO.getInputDate())
				.boardEntity(boardEntity)
				.build();
	}

}
