package net.kdigital.test3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import net.kdigital.test3.entity.GuestbookEntity;

public interface GuestbookRepository extends JpaRepository<GuestbookEntity, Long> {

	@Transactional
	@Query(value="SELECT * FROM guestbook WHERE guest_pwd = :value", nativeQuery=true)
    List<GuestbookEntity> findByPwd(@Param("value") String value);
	
	@Modifying
	@Transactional
	@Query(value="""
			UPDATE guestbook
			SET 
			guest_text = :#{#entity.text}
			, regdate = :#{#entity.regdate}
			WHERE guest_seq = :#{#.entity.guestSeq}
			""", nativeQuery=true)
	public void updateText(@Param("entity") GuestbookEntity entity);

}
