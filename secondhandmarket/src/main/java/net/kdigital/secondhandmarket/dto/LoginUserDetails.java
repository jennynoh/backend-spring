package net.kdigital.secondhandmarket.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.ToString;

@ToString
public class LoginUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String phone;
	private boolean enabled;
	private String rolename;
	
	public LoginUserDetails(String memberId, String memberPwd, String memberName, String phone, boolean enabled, String rolename) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.phone = phone;
		this.enabled = enabled;
		this.rolename = rolename;
	}
	
	public LoginUserDetails(UserDTO userDTO) {
		super();
		this.memberId = userDTO.getMemberId();
		this.memberPwd = userDTO.getMemberPw();
		this.memberName = userDTO.getMemberName();
		this.phone = userDTO.getPhone();
		this.enabled = userDTO.isEnabled();
		this.rolename = userDTO.getRolename();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority(){
			private static final long serialVersionUID = 1L;
			
			@Override
			public String getAuthority() {
				return null;
			}
		});
		return collection;
	}

	@Override
	public String getPassword() {
		return this.memberPwd;
	}

	@Override
	public String getUsername() {
		return this.memberId;
	}
	
	// 사용자 이름 반환
	public String getUserName() {
		return this.memberName;
	}

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
