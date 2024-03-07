2024년 2월 29일 

*** SpringBoot 1 생성
- index 구동
- MainController - Controller test

*** 작업시 주의사항
	모든 프로젝트의 요청 경로는 동일하면 안됨
	동일할 경우 요청방식이 달라야함
	
	@GetMapping(“/”) : 요청
	
	@PostMapping(“/”) // 가능
	@GetMapping(“/”) // 불가능
	
	@DeleteMapping()
	@PutMapping()

