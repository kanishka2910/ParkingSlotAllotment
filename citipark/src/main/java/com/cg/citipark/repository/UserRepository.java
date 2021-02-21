package com.cg.citipark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.citipark.beans.User;


public interface UserRepository extends JpaRepository<User,Integer>{

	@Query(value ="SELECT u FROM User u WHERE email=:email")
	User findByEmail(String email);
}
