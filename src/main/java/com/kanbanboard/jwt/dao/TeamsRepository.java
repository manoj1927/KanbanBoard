package com.kanbanboard.jwt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kanbanboard.jwt.entity.Teams;

@Repository
public interface TeamsRepository extends JpaRepository<Teams,Long>{

	

	
	//Teams findById(long id);

}






