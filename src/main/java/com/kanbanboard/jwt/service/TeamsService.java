package com.kanbanboard.jwt.service;

import java.util.List;
import java.util.function.Supplier;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanbanboard.exceptions.TeamsNotFoundException;
import com.kanbanboard.jwt.dao.TeamsRepository;
import com.kanbanboard.jwt.entity.Teams;






@Service
public class TeamsService {
	
	@Autowired
	private TeamsRepository teamsRepository;

	public Teams createTeams(Teams t) {
		teamsRepository.save(t);
		return t;
	}
	
	public List<Teams> getTeams() {
		List<Teams> t = teamsRepository.findAll();
		return t;
	}
	//public String getTeams(Teams t) throws Throwable {
		//Supplier s1 = ()-> new TeamsNotFoundException("Teams Not Found");
		//teamsRepository.getTeams();
		//return "Teams has been getting successfully";
		
		
		
	//}
	
	

	public String removeTeams(long id) throws Throwable {
		
		Supplier s1 = ()-> new TeamsNotFoundException("No Teams with the given Id");
		teamsRepository.deleteById(id);
		return "Teams has been removed successfully";
	}
	
	public Teams updateTeams(Teams t) throws Throwable {
		
		Supplier s1 = ()-> new TeamsNotFoundException("Teams Not Found");
		long id = t.getId();
		Teams t1 = teamsRepository.findById(id).orElseThrow(s1);
		t1.setTeamLeadName(t.getTeamLeadName());
		t1.setTeamMembersName(t.getTeamMembersName());
		t1.setTeamName(t.getTeamName());
		
		
		teamsRepository.save(t1);
		return t1;
	}

	

		
}