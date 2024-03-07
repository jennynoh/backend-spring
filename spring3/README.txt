2024년 2월 29일

**작업 내용
1) 클라이언트 요청과 함께 전송된 파라미터 receive
	요청의 종류
		- /: get요청 
		- /param/send: get요청 + 데이터 전송 (name, age)
		- /param/sendForm: get요청 + form으로 직접 입력한 값 전송 
		
	@RequestParam defaultValue

2) 서버에서 클라이언트로 응답할 때 데이터를 실어보내기 위해
	- Model 객체를 요청
	- model.addAttribute() 메소드에 key, value 쌍으로 데이터를 넣음
	- Forwarding 방식으로 응답을 보냄 

3) 클라이언트에서 데이터를 받을 때에는 Thymeleaf 필요
	- <html xmlns:th="http://thymeleaf.org">로 target 페이지에 설정
	- 데이터가 표시될 위치에 [[ ${변수명} ]]