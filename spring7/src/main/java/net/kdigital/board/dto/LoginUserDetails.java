package net.kdigital.board.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.ToString;

@ToString
public class LoginUserDetails implements UserDetails {
	private String userId;
	private String userName;
	private String userPwd;
	private String email;
	private String roles;
	private Boolean enabled;	
	
	// 생성자 
	public LoginUserDetails(String userId, String userName, String userPwd, String email, String roles,
			Boolean enabled) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.email = email;
		this.roles = roles;
		this.enabled = enabled;
	}

	public LoginUserDetails(UserDTO userDTO) {
		super();
		this.userId = userDTO.getUserId();
		this.userName = userDTO.getUserName();
		this.userPwd = userDTO.getUserPwd();
		this.email = userDTO.getEmail();
		this.roles = userDTO.getRoles();
		this.enabled = userDTO.getEnabled();
	}

	// 계정의 권한 반환 요청 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return this.userPwd;
	}

	@Override
	public String getUsername() {
		return this.userId;
	}

	
	// boolean으로 설정된것: 원래는 DB에서 읽어와야함 
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
