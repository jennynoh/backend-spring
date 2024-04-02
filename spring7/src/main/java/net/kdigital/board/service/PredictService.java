package net.kdigital.board.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.Iris;

@Service
@RequiredArgsConstructor
@Slf4j
public class PredictService {
	// 실제 fast api와 붙는 부분 
	@Value("${iris.predict.server}")
	String url;
	private final RestTemplate restTemplate;
	

	public Map<String, String> predictRest(Iris iris) {
		// RestTemplate 
		Map<String, String> error = new HashMap<>();  // 에러를 담을 맵 
		Map<String, String> result = new HashMap<>();   // 정상 결과를 담을 맵 
		
		try {
			// 헤더설정 
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			
			// iris를 보내면 결과를 map으로 받음
			ResponseEntity<Map> response = restTemplate.postForEntity(url, iris, Map.class); 
			result = response.getBody();
			
		} catch(Exception e) {
			// 에러코드 생성 
			// http client error exception으로 받으면 에러 코드를 뽑아낼 수 있음 
			error.put("statusCode", "450");
			error.put("body", "오류 발생");
			return error;
		}
		
		return result;
	}

}
