package net.kdigital.spring6.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
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
	@SuppressWarnings("null")
	public void insertFriend(FriendDTO friendDTO) {
		log.info("service에 도착! {}", friendDTO.toString());
		
		// 1) 전달받은 DTO를 Entity로 변환
		// DTO -> Entity
		FriendEntity entity = FriendDTO.toEntity(friendDTO);
		log.info("service에 도착! {}", entity.toString());
		// Entity -> DTO
		// FriendDTO dto = FriendEntity.toDTO(entity);
		
		// 2) Repository로 넘겨서 저장
		friendRepository.save(entity);
		
	}


	// repository에서 데이터 받아오기 
	public List<FriendDTO> selectAll() {
		// 정렬되지 않은 상태로 받아오기 => List<FriendEntity> list = friendRepository.findAll();
		// 이름 순 정렬 
		List<FriendEntity> list = friendRepository.findAll(Sort.by(Sort.Direction.ASC, "fname"));
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


	// @Transactional
	public void updateProc(FriendDTO friendDTO) {
		// 1) find한 데이터의 값을 set으로 변경 => update
		Optional<FriendEntity> entity = friendRepository.findById(friendDTO.getFriendSeq());
		
		if(entity.isPresent()) {
			FriendEntity f = entity.get();
			f.setFname(friendDTO.getFname());
			f.setAge(friendDTO.getAge());
			f.setPhone(friendDTO.getPhone());
			f.setBirthday(friendDTO.getBirthday());
			f.setActive(friendDTO.isActive());
			
			friendRepository.deleteById(friendDTO.getFriendSeq());
			friendRepository.save(f);
		}
		
//		// 2) JPQL
//		 FriendEntity entity = FriendDTO.toEntity(friendDTO);
//		 friendRepository.updateFriend(entity);
		
		
	}


	
}
