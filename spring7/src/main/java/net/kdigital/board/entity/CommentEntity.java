package net.kdigital.board.entity;

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
import net.kdigital.board.dto.CommentDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="comments")
public class CommentEntity {
	
	
	@Id
	@SequenceGenerator(
			name="comment_seq"
			, sequenceName="comment_seq"
			, initialValue=1
			, allocationSize=1)
	@GeneratedValue(generator="comment_seq")
	@Column(name="comment_num")
	private Long commentNum;
	
	/* Board 와 Comment 는 1:n 관계 
	 * Comment 는 n 의 위치, 조인컬럼은 boardNum
	 * 1) 부모(board)가 객체로 선언되어있어야함
	 * 2) 관계를 맺을 때 @ManyToOne 관계 설정
	 * 3) FetchType: EAGER (부모 끌려오면 같이 로드됨), LAZY (default, 요청할때 로드) 
	 * 4) @JoinColumn 설정 
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="board_num")
	private BoardEntity boardEntity;
	
	@Column(name="comment_writer")
	private String commentWriter;
	
	@Column(name="comment_text")
	private String commentText;
	
	@Column(name="create_date")
	@CreationTimestamp
	private LocalDateTime createDate;
	
	public static CommentEntity toEntity(CommentDTO commentDTO, BoardEntity boardEntity) {
		return CommentEntity.builder()
				.commentNum(commentDTO.getCommentNum())
				.commentWriter(commentDTO.getCommentWriter())
				.commentText(commentDTO.getCommentText())
				.createDate(commentDTO.getCreateDate())
				.boardEntity(boardEntity)
				.build();
	}

}
