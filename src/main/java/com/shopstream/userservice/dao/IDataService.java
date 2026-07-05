package com.shopstream.userservice.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopstream.userservice.entity.User;
@Repository
public interface IDataService extends JpaRepository<User, UUID>{
	
	Optional<User> findByEmail(String email);

}
