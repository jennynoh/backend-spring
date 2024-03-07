package net.kdigital.spring6.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.spring6.dto.FriendDTO;
import net.kdigital.spring6.entity.FriendEntity;
import net.kdigital.spring6.repository.FriendRepository;

@Service
@Slf4j
public class FriendService {
	
	private FriendRepository friendRepository;
	
	public FriendService(FriendRepository friendRepository) {
		this.friendRepository = friendRepository;
	}


	// 친구정보 db에 전송 
	public void insertFriend(FriendDTO friendDTO) {
		log.info("service에 도착!");
		
		// 1) 전달받은 DTO를 Entity로 변환
		// DTO -> Entity
		FriendEntity entity = FriendDTO.toEntity(friendDTO);
		
		// Entity -> DTO
		// FriendDTO dto = FriendEntity.toDTO(entity);
		
		// 2) Repository로 넘겨서 저장
		friendRepository.save(entity);
		
	}


	// repository에서 데이터 받아오기 
	public List<FriendDTO> selectAll() {
		List<FriendEntity> list = friendRepository.findAll();
		List<FriendDTO> friendDTOList = new ArrayList<>();
		list.forEach((entity) -> friendDTOList.add(FriendEntity.toDTO(entity)));
		
		return friendDTOList;
	}
	
}
