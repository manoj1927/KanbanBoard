package com.kanbanboard.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kanbanboard.jwt.entity.Tasks;


@Repository
public interface TasksRepository  extends JpaRepository<Tasks,Long>{
	

}
