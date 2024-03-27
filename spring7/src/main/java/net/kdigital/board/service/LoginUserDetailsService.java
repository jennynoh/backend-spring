package net.kdigital.board.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.LoginUserDetails;
import net.kdigital.board.dto.UserDTO;
import net.kdigital.board.entity.UserEntity;
import net.kdigital.board.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	/**
	 * DB table에 접근해서 데이터를 가져옴 => UserRepository 
	 * 사용자가 로그인을 하면 SecurityConfig가 username을 여기로 전달함 
	 */
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// userId 검증 로직 작성 
		log.info("{}", userId);
		UserEntity userEntity = userRepository.findById(userId)
				.orElseThrow(() -> {
					throw new UsernameNotFoundException("error 발생");
				});
		
		UserDTO userDTO = UserDTO.toDTO(userEntity);
		// UserDetails로 반환 (UserDTO -> UserDetails)
		return new LoginUserDetails(userDTO);
		
//		if(userEntity.isPresent()) {
//			UserEntity entity = userEntity.get();
//			UserDTO userDTO = UserDTO.toDTO(entity);
//			// UserDetails로 반환 (UserDTO -> UserDetails)
//			return new LoginUserDetails(userDTO);
//		}
		// 저장된 유저 정보가 없을 때 null 반환 
//		return null;
	}

}
