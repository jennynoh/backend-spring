package net.kdigital.test3.entity;

import java.time.LocalDateTime;

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
import net.kdigital.test3.dto.GuestbookDTO;

@Entity
@Setter
@Getter
@Table(name="guestbook")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestbookEntity {
	@Id
	@Column(name="guest_seq")
	@SequenceGenerator(name="guestbook_seq", sequenceName="guestbook_seq"
	, initialValue=1, allocationSize=1)
	@GeneratedValue(generator="guestbook_seq")
	private Long guestSeq;
	
	@Column(name="guest_name", nullable=false)
	private String guestname;
	
	@Column(name="guest_pwd", nullable=false)
	private String pwd;
	
	@Column(name="guest_text")
	private String text;
	
	@Column(name="regdate")
	private LocalDateTime regdate;
	
	// Entity -> DTO 메서드
	public static GuestbookDTO toDTO(GuestbookEntity guestbookEntity) {
		return GuestbookDTO.builder()
				.guestSeq(guestbookEntity.getGuestSeq())
				.guestname(guestbookEntity.getGuestname())
				.pwd(guestbookEntity.getPwd())
				.text(guestbookEntity.getText())
				.regdate(guestbookEntity.getRegdate())
				.build();
	}

}
