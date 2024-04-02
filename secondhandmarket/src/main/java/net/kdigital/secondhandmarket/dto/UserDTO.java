package net.kdigital.secondhandmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.secondhandmarket.entity.UserEntity;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String phone;
	private boolean enabled;
	private String rolename;
	
	public static UserDTO toDTO(UserEntity userEntity) {
		return UserDTO.builder()
				.memberId(userEntity.getMemberId())
				.memberPw(userEntity.getMemberPw())
				.memberName(userEntity.getMemberName())
				.phone(userEntity.getPhone())
				.enabled(userEntity.isEnabled())
				.rolename(userEntity.getRolename())
				.build();
	}
	
	
}

/**
 * CREATE TABLE market_member
(
    member_id VARCHAR2(20) PRIMARY KEY
    , member_pw VARCHAR2(100) NOT NULL
    , member_name VARCHAR2(20) NOT NULL
    , phone VARCHAR2(20) NOT NULL
    , enabled CHAR(1) DEFAULT '1'
    , rolename VARCHAR2(20) DEFAULT 'ROLE_USER'
);
 */
