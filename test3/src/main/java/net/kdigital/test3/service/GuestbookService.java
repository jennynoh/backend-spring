package net.kdigital.test3.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.test3.dto.GuestbookDTO;
import net.kdigital.test3.entity.GuestbookEntity;
import net.kdigital.test3.repository.GuestbookRepository;

@Service
@Slf4j
public class GuestbookService {
	private GuestbookRepository guestbookRepository;
	
	public GuestbookService(GuestbookRepository guestbookRepository) {
		this.guestbookRepository = guestbookRepository;
	}
	
	// 1) 입력: 방명록 정보 db에 전송 
	public void insertGuestbook(GuestbookDTO guestbookDTO) {
		log.info("저장: {}", guestbookDTO);
		LocalDateTime now = LocalDateTime.now();
		guestbookDTO.setRegdate(now);
		GuestbookEntity entity = GuestbookDTO.toEntity(guestbookDTO);
		guestbookRepository.save(entity);
	}
	
	
	// 2) 조회: db에서 방명록 정보 받아오기
	public List<GuestbookDTO> selectAll() {
		List<GuestbookDTO> guestbookDTOList = new ArrayList<>();
		List<GuestbookEntity> list = guestbookRepository.findAll();
		list.forEach((entity)->guestbookDTOList.add(GuestbookEntity.toDTO(entity)));
		
		return guestbookDTOList;
	}
	
	// 3) 삭제: 방명록 삭제 
	public void deleteOne(Long guestSeq) {
		guestbookRepository.deleteById(guestSeq);
	}

	// 4) pwd가 일치하는 방명록만 가져오기 
	public List<GuestbookDTO> selectMine(String pwd) {
		List<GuestbookDTO> mybookDTOList = new ArrayList<>();
		List<GuestbookEntity> list = guestbookRepository.findByPwd(pwd);
		list.forEach((entity)->mybookDTOList.add(GuestbookEntity.toDTO(entity)));
		return mybookDTOList;
	}

}
