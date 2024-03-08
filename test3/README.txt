2024년 3월 8일

[실습] 방명록 작성하기 (Guestbook)

-주요기능
1. 방명록 글쓰기

2. 전체 방명록 보기

3. 방명록 삭제
- js promt로 비밀번호 입력받아 일치하는 방명록만 삭제 

-주요 request
	/ 첫요청 (Get) - MainController
	/insert 입력화면 요청  (Get) - GuestbookController
	/insert 저장요청  (Post) - GuestbookController
	/list 목록요청 (Get) - GuestbookController
	/deleteOne 삭제요청 (Get) - GuestbookController (비밀번호를 입력받아 일치하는 방명록만 삭제)
	
	/update 수정을 위한 화면 요청 (Get) - GuestbookController (데이터 수정을 위한 화면요청)
	/updateProc 수정처리요청 (Get) - 