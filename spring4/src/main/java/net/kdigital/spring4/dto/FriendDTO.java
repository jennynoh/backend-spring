package net.kdigital.spring4.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@ToString
public class FriendDTO {
	// client side에서 보내는 이름과 같아야 받을 수 있음 
	private String fname;
	private int age;
	private String phone;
	private LocalDate birthday;
	private boolean active;
	
	// 데이터를 전송할 때 기본 생성자가 필요함 
	// client side에서 @ModelAttribute로 데이터를 보내면 기본생성자를 호출함 
	
}
