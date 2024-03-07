package net.kdigital.spring6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.spring6.entity.FriendEntity;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {

}
