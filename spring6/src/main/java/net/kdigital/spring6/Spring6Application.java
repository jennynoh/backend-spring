package net.kdigital.spring6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import net.kdigital.spring6.dto.Sample;

@SpringBootApplication
public class Spring6Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring6Application.class, args);
		
//		// 1) 기본생성자로 생성 
//		Sample s1 = new Sample();
//		s1.setSeq(1L);
//		s1.setUserid("홍길동");
//		s1.setUserpwd("1234");
//		s1.setContent("오늘은 목요일입니다.");
//		
//		System.out.println(s1);
//		
//		// 2) 오버로딩 생성자로 생성 - 객체 속성의 순서를 기억하고 넣어야함 
//		Sample s2 = new Sample(2L, "전우치", "1111", "금나와라 뚝딱");
//		System.out.println(s2);
//		
//		// 3) Builder 패턴으로 생성 
//		// 순서 상관 없음, 값을 넣지 않으면 null로 초기화됨 
//		Sample s3 = Sample.builder()
//				.seq(3L)
//				.userid("손오공")
//				.userpwd("2222")
//				.content("안녕하세요.")
//				.build();
//		
//		System.out.println(s3);
//		
//		// 4) 클래스 내부에 선언한 builder 패턴
//		Sample s4 = Sample.builder()
//				.userid("pepapig")
//				.content("i am a pig.")
//				.build();
//		
//		System.out.println(s4);
		
	}

}
