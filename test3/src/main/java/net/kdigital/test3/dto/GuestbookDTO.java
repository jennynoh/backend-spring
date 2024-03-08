package net.kdigital.test3.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.test3.entity.GuestbookEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GuestbookDTO {
	private Long guestSeq;
	private String guestname;
	private String pwd;
	private String text;
	private LocalDateTime regdate;

	// Entity -> DTO 메서드
	public static GuestbookEntity toEntity(GuestbookDTO guestbookDTO) {
		return GuestbookEntity.builder()
				.guestname(guestbookDTO.getGuestname())
				.pwd(guestbookDTO.getPwd())
				.text(guestbookDTO.getText())
				.regdate(guestbookDTO.getRegdate())
				.build();
	}

}
