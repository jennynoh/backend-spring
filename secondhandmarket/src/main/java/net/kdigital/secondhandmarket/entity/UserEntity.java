package net.kdigital.secondhandmarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.secondhandmarket.dto.UserDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="market_member")
public class UserEntity {
	@Id
	@Column(name="member_id", nullable=false)
	private String memberId;
	
	@Column(name="member_pw", nullable=false)
	private String memberPw;
	
	@Column(name="member_name", nullable=false)
	private String memberName;
	
	@Column(nullable=false)
	private String phone;
	
	private boolean enabled;
	
	private String rolename;
	
	public static UserEntity toEntity(UserDTO userDTO) {
		return UserEntity.builder()
				.memberId(userDTO.getMemberId())
				.memberPw(userDTO.getMemberPw())
				.memberName(userDTO.getMemberName())
				.phone(userDTO.getPhone())
				.enabled(userDTO.isEnabled())
				.rolename(userDTO.getRolename())
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
