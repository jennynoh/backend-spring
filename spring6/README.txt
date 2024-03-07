2024년 3월 7일

**작업내용
1. 클라이언트로부터 받은 데이터를 DB에 CRUD

2. 요청 url 종류
	/ : 첫 요청 (GET) - MainController
	/insert : 화면요청 (GET) - FriendController (데이터 입력을 위한 화면요청)
	/insert : 저장요청 (POST) - FriendController (DB에 저장하기 위한 요청)
	/selectOne : 조회요청 (GET) - FriendCOntroller (1명 조회 - seq번호로)
	/list : 목록요청 (GET) - FriendController (DB 데이터 전체조회)

*참고: builder 패턴으로 객체생성하는 방법 
-lombok에서 제공하는 @Builder를 이용하여 생성

	