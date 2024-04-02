package net.kdigital.secondhandmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.secondhandmarket.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
