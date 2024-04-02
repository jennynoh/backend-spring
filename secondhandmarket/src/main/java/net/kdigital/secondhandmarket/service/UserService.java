package net.kdigital.secondhandmarket.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.secondhandmarket.dto.UserDTO;
import net.kdigital.secondhandmarket.entity.UserEntity;
import net.kdigital.secondhandmarket.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * 회원가입 처리 
	 * @param userDTO
	 * @return 회원가입 성공여부 
	 */
	public boolean joinProc(UserDTO userDTO) {
		boolean isExistUser = userRepository.existsById(userDTO.getMemberId());
		if(isExistUser) return false;
		
		userDTO.setMemberPw(bCryptPasswordEncoder.encode(userDTO.getMemberPw()));
		
		UserEntity entity = UserEntity.toEntity(userDTO);
		userRepository.save(entity);
		
		return true;
	}
	
}
