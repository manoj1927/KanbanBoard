package com.kanbanboard.jwt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kanbanboard.jwt.entity.Teams;
import com.kanbanboard.jwt.service.TeamsService;




@RestController
@RequestMapping("/TeamsAPI")
@CrossOrigin
public class TeamsController {
	
	@Autowired
	private TeamsService teamsService;
	
	@PostMapping("/AddTeams")
	public ResponseEntity<Teams> createTeams(@Valid @RequestBody Teams t) {
		Teams t1 = teamsService.createTeams(t);
		ResponseEntity<Teams> rr = new ResponseEntity<Teams>(t1, HttpStatus.OK);
		return rr;
	}
	
	
	
	@PutMapping("/ModifyTeams")
	public ResponseEntity<Teams> updateTeams(@Valid @RequestBody Teams t) throws Throwable {
		Teams t1 = teamsService.updateTeams(t);
		ResponseEntity<Teams> rr = new ResponseEntity<Teams>(t1, HttpStatus.OK);
		return rr;
	}
	
	@GetMapping("/getTeams")
		public ResponseEntity <List<Teams>> getTeams() throws Throwable {
			List<Teams> t1 = teamsService.getTeams();
		    //Teams t1 = teamsService.getTeams(t);
		 
			ResponseEntity <List<Teams>> rr = new ResponseEntity<List<Teams>>(t1,HttpStatus.OK);
			return rr;
		}
	
	
	//@GetMapping("/getTeams")
	//public ResponseEntity<Teams> getTeams(@Valid @RequestBody Teams t) throws Throwable {
		//Teams t1 = teamsService.getTeams(t);
		//ResponseEntity<Teams> rr = new ResponseEntity<Teams>(t1, HttpStatus.OK);
		//return rr;
	//}
		
	@DeleteMapping("/Removeteams/{id}")
	public String deleteTeams(@Valid @PathVariable long id) throws Throwable {
		teamsService.removeTeams(id);
		return "Teams Removed Successfully";
	}

}