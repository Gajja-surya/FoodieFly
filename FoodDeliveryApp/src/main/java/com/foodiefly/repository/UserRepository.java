package com.foodiefly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodiefly.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

//	User findById(int userId);
	 Optional<User> findByUsername(String username);
	
}
