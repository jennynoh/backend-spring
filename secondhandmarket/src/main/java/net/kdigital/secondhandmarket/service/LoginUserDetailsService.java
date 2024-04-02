package net.kdigital.secondhandmarket.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.secondhandmarket.dto.LoginUserDetails;
import net.kdigital.secondhandmarket.dto.UserDTO;
import net.kdigital.secondhandmarket.entity.UserEntity;
import net.kdigital.secondhandmarket.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findById(username)
				.orElseThrow(() -> {
					throw new UsernameNotFoundException("error 발생");
				});
		
		UserDTO userDTO = UserDTO.toDTO(userEntity);
		return new LoginUserDetails(userDTO);
	}

}
