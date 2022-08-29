package com.kanbanboard.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kanbanboard.jwt.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
	
}
