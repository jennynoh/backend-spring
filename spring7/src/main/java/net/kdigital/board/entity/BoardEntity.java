package net.kdigital.board.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

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
	@CreationTimestamp // 객체가 처음 생성될 때 날짜를 자동으로 지정해주는 기능 
	private LocalDateTime createDate;
	
	@Column(name="update_date")
	@LastModifiedDate // 객체가 수정될 때 자동으로 날짜를 업데이트 해주는 기능 
	private LocalDateTime updateDate;
	
	@Column(name="original_file_name")
	private String originalFileName;
	
	@Column(name="saved_file_name")
	private String savedFileName;
	
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
				.originalFileName(boardEntity.getOriginalFileName())
				.savedFileName(boardEntity.getSavedFileName())
				.build();
	}
	
	
}



