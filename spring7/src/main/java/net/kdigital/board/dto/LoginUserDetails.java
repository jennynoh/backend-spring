package net.kdigital.board.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.ToString;

@ToString
public class LoginUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
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
		// role이 여러개인 사람의 role을 담을 array list 
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority(){
			private static final long serialVersionUID = 1L;
			// GrantedAuthority는 인터페이스이기 때문에 new를 할 수 없음 
			@Override
			public String getAuthority() {
				return null;
			}
			
		});
		return collection;
	}

	@Override
	public String getPassword() {
		return this.userPwd;
	}

	// ID 반환(Override): 
	@Override
	public String getUsername() {
		return this.userId;
	}
	
	// 사용자 이름 반환할 떼 
	public String getUserName() {
		return this.userName;
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
