2024년 3월 5일

**작업내용 
1. Controller 단에서 객체 (FriendDTO)나 파일이 첨부되어 전송될 때 사용하는 annotaion
   - @ModelAttribute
   - 메소드의 매개변수 위치에 사용 
 
 2. 클라이언트에서 서버로 데이터를 전송할 때 객체로 받을 경우 기본생성자가 호출, 값을 넣을 때 Setter 자동 호출 
    - DTO 객체를 이용해 데이터를 받을 때는 반드시 기본생성자, Setter 필요 

3. 서버에서 클라이언트로 데이터를 보낼 때에는 Model 객체가 필요함
   - spring container에 Model model로 요

4. html에서 thymeleaf로 데이터를 꺼낼 때에는 Getter가 필요
   