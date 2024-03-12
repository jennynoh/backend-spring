package net.kdigital.spring6.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import net.kdigital.spring6.entity.FriendEntity;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {

//	@Modifying   // 바로 db에 접근하는 것이 아니라 cache 메모리에 저장해 두는 것 
//	@Transactional // 전체를 rollback할 수 있게 해주는 것: Transactional
//	@Query(value="""
//			UPDATE friend
//			SET 
//			fname = :#{#entity.fname}
//			, age = :#{#entity.age}
//			, phone = :#{#entity.phone}
//			, birthday = :#{#entity.birthday}
//			, active = :#{#entity.active}
//			WHERE friend_seq = :#{#entity.friendSeq}
//			""", nativeQuery=true)
//	public void updateFriend(@Param("entity") FriendEntity entity);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE FriendEntity " +
	               "SET fname = :fname, " +
	               "age = :age, " +
	               "phone = :phone, " +
	               "birthday = :birthday, " +
	               "active = :active " +
	               "WHERE friendSeq = :friendSeq", nativeQuery = false)
	public void updateFriend(@Param("fname") String fname, 
	                         @Param("age") int age, 
	                         @Param("phone") String phone, 
	                         @Param("birthday") LocalDateTime birthday, 
	                         @Param("active") boolean active, 
	                         @Param("friendSeq") Long friendSeq);


}
