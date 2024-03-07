package net.kdigital.spring6.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.spring6.entity.FriendEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FriendDTO {
	private Long friendSeq;
	private String fname;
	private int age;
	private String phone;
	private LocalDate birthday;
	private boolean active;
	
	public static FriendEntity toEntity(FriendDTO friendDTO) {
		return FriendEntity.builder()
				.fname(friendDTO.getFname())
				.age(friendDTO.getAge())
				.phone(friendDTO.getPhone())
				.birthday(friendDTO.getBirthday())
				.active(friendDTO.isActive())
				.build();
	}
	
}

/*
CREATE TABLE friend
(
	friend_seq NUMBER PRIMARY KEY
	, fname VARCHAR2(30) NOT NULL
	, age NUMBER(3) DEFAULT 1
	, phone VARCHAR2(20) UNIQUE
	, birthday DATE DEFAULT sysdate
	, active CHAR(1) DEFAULT 1
	
);
*/