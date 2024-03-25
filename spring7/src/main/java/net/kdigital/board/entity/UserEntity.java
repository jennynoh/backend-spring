package net.kdigital.board.entity;

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
import net.kdigital.board.dto.UserDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="boarduser")
public class UserEntity {
	
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_name", nullable=false)
	private String userName;
	
	@Column(name="user_pwd", nullable=false)
	private String userPwd;
	
	@Column(nullable=false)
	private String email;
	
	private String roles;
	private boolean enabled;
	
	public static UserEntity toEntity(UserDTO userDTO) {
		return UserEntity.builder()
				.userId(userDTO.getUserId())
				.userName(userDTO.getUserName())
				.userPwd(userDTO.getUserPwd())
				.email(userDTO.getEmail())
				.roles(userDTO.getRoles())
				.enabled(userDTO.getEnabled())
				.build();
	}
	
	

}
