package net.kdigital.secondhandmarket.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.secondhandmarket.dto.BoardDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="market_board")
public class BoardEntity {
	
	@Id
	@SequenceGenerator(
			name="market_board_seq"
			, sequenceName="market_board_seq"
			, initialValue=1
			, allocationSize=1)
	@GeneratedValue(generator="market_board_seq")
	@Column(name="board_num")
	private Long boardNum;
	
	@Column(name="member_id")
	private String memberId;
	
	private String title;
	private String contents;
	
	@Column(name="input_date")
	private LocalDateTime inputDate;
	
	private String category;
	
	@Column(name="soldout", insertable=false)
	private String soldout;
	
	@Column(name="buyer_id")
	private String buyerId;
	
	// 댓글 entity와 foreign key 연결
	@OneToMany(mappedBy="boardEntity"
			, cascade=CascadeType.REMOVE
			, orphanRemoval=true
			, fetch=FetchType.LAZY)
	@OrderBy("comment_num asc")
	private List<CommentEntity> commentEntity = new ArrayList<>();
	
	public static BoardEntity toEntity(BoardDTO boardDTO) {
		return BoardEntity.builder()
				.boardNum(boardDTO.getBoardNum())
				.memberId(boardDTO.getMemberId())
				.title(boardDTO.getTitle())
				.contents(boardDTO.getContents())
				.inputDate(boardDTO.getInputDate())
				.category(boardDTO.getCategory())
				.soldout(boardDTO.getSoldout())
				.buyerId(boardDTO.getBuyerId())
				.build();
	}
	

}


