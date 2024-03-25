package net.kdigital.board.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.board.dto.UserDTO;
import net.kdigital.board.entity.UserEntity;
import net.kdigital.board.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * 회원가입 처리 
	 * @param userDTO
	 */
	public boolean joinProc(UserDTO userDTO) {
		
		// 존재하는 아이디는 회원가입 불가 
		boolean isExistUser = userRepository.existsById(userDTO.getUserId());
		if(isExistUser) return false;
		
		// 비밀번호 암호화 : bCryptPasswordEncoder
		userDTO.setUserPwd(bCryptPasswordEncoder.encode(userDTO.getUserPwd())); 
		
		// entity로 변환하여 저장 
		UserEntity entity = UserEntity.toEntity(userDTO);
		userRepository.save(entity);
		
		return true;
	}
	

}
