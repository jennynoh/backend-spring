package net.kdigital.spring6.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


	public void deleteOne(Long friendSeq) {
		friendRepository.deleteById(friendSeq);
	}


	@SuppressWarnings("static-access")
	public FriendDTO selectOne(Long friendSeq) {
		// optional 객체: 객체가 없을 수 있어 Null pointer exception 관리하는 객
		Optional<FriendEntity> entity = friendRepository.findById(friendSeq); 
		if(entity.isPresent()) {
			FriendEntity friendEntity = entity.get();
			return FriendEntity.toDTO(friendEntity);
		} 
		return null;
	}


	public void updateProc(FriendDTO friendDTO) {
		FriendEntity entity = FriendDTO.toEntity(friendDTO);
		friendRepository.updateFriend(entity);
	}


	
}
