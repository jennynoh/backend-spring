2024년 3월 7일

**작업내용
1. 클라이언트로부터 받은 데이터를 DB에 CRUD

2. 요청 url 종류
	/ : 첫 요청 (GET) - MainController
	/insert : 화면요청 (GET) - FriendController (데이터 입력을 위한 화면요청)
	/insert : 저장요청 (POST) - FriendController (DB에 저장하기 위한 요청)
	/selectOne : 조회요청 (GET) - FriendCOntroller (1명 조회 - seq번호로)
	/list : 목록요청 (GET) - FriendController (DB 데이터 전체조회)
	
	/update : 화면요청 (GET)
	/updateProc 수정처리요청 (POST)
	/delectOne : 1명 삭제 (GET)

3. 추가작업
	- findAll() 시 sort하기 - 파라미터 추가
	- 유효성 검증 
		(1) js로 유효성 검증
		(2) Spring에서 제공하는 validate 이용 - annotation으로 처리
			1) dependency 추가 - spring.io => projects > spring initializer -> dependency 추가 
			2) DTO에서 검증 annotation & 메세지 추가
			3) Controller에서 오류 발생 시 해당 오류를 담을 객체 (bind 처리) 필요 
			4) 검증 오류 발생 시 view로 return
			5) 오류가 없는 경우 저장, 수정 수행 
	- updateProc에서 JPQL로 하지 않고 예전 방식으로 변경 


*참고: builder 패턴으로 객체생성하는 방법 
-lombok에서 제공하는 @Builder를 이용하여 생성

	