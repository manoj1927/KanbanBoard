package com.kanbanboard.jwt.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.kanbanboard.jwt.dao.TeamsRepository;
import com.kanbanboard.jwt.entity.Teams;
import com.kanbanboard.jwt.service.TeamsService;


@SpringBootTest
public class TeamsServiceImplTest {
	
	@Autowired
	TeamsService teamsService;
	
	@MockBean
	TeamsRepository teamsRepository;
	
	@Test
	void testCreateTeams() {
		
		Teams t = new Teams();
		t.setId(123);
		t.setTeamLeadName("roshan");
		t.setTeamMembersName("anu,ramya,pooja");
		t.setTeamName("fullstack");
		
		
		Mockito.when(teamsRepository.save(t)).thenReturn(t);
		 assertThat(teamsService.createTeams(t)).isEqualTo(t);
	}
	
	@Test
	void testUpdateTeams() throws Throwable {
		Teams t = new Teams();
		t.setId(123);
		t.setTeamLeadName("arun");
		t.setTeamMembersName("nagu,raju,priya");
		t.setTeamName("python");
		
		
		Optional<Teams> t1 = Optional.of(t);
		Mockito.when(teamsRepository.findById((long) 123)).thenReturn(t1);
		Mockito.when(teamsRepository.save(t)).thenReturn(t);
		t.setTeamLeadName("arun");
		t.setTeamMembersName("nagu,raju,priya");
		t.setTeamName("python");
		
		assertThat(teamsService.updateTeams(t)).isEqualTo(t);
	}
	
	@Test
	void testDeleteTeams() throws Throwable {
		Teams t = new Teams();
		t.setId(123);
		t.setTeamLeadName("kush");
		t.setTeamMembersName("karthik,roopa,shubha");
		t.setTeamName("sql");
		
		
		Optional<Teams> t1 = Optional.of(t);
		Mockito.when(teamsRepository.findById((long) 123)).thenReturn(t1);
		assertThat(teamsService.removeTeams(123)).isEqualTo("Teams has been removed successfully");
	}

}