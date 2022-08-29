package com.kanbanboard.jwt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
public class Teams {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max=30)
	//@Pattern(regexp = "^[A-Za-z 0-9]+$")
	private String teamLeadName;
	
	@NotNull
	@Size(max=30)
	//@Pattern(regexp = "^[A-Za-z 0-9]+$")
	private String teamMembersName;
	
	@NotNull
	@Size(max=30)
	//@Pattern(regexp = "^[A-Za-z 0-9]+$")
	private String teamName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTeamLeadName() {
		return teamLeadName;
	}

	public void setTeamLeadName(String teamLeadName) {
		this.teamLeadName = teamLeadName;
	}

	public String getTeamMembersName() {
		return teamMembersName;
	}

	public void setTeamMembersName(String teamMembersName) {
		this.teamMembersName = teamMembersName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		return "Teams [id=" + id + ", teamLeadName=" + teamLeadName + ", teamMembersName=" + teamMembersName
				+ ", teamName=" + teamName + "]";
	}
	

}