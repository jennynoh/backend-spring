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
	@Size(min=2, max=10, message="이름을 2~10자 이내로 입력해주세요.")
	private String fname;  // 글자수 제한 or null 여부 확인 
	@Min(value=15, message="나이를 15세 이상으로 입력해주세요.")
	private int age;  // 15살 이상만 가입 
	@Pattern(regexp="01[016789]\\d{4}\\d{4}", message="전화번호를 올바른 형식으로 입력해주세요.")
	private String phone;  // 숫자 11자리 (정규표현식)
	@PastOrPresent(message="과거 날짜를 선택해주세요.")
	@NotNull(message="생년월일을 선택해주세요.")
	private LocalDate birthday; // 현재날짜 기준 과거/미래 지정 할 수 있음 (과거날짜 입력)
	private boolean active;
	
	// unique 조건 check은 '중복확인' 버튼으로 db로 넘어가서 작업해야함 
	// 비밀번호 확인: js에서 가능 
	
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