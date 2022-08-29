package com.kanbanboard.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kanbanboard.jwt.entity.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	//Project findById(long id);
}
