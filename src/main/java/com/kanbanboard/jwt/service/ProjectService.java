package com.kanbanboard.jwt.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanbanboard.exceptions.ProjectNotFoundException;
import com.kanbanboard.jwt.dao.ProjectRepository;
import com.kanbanboard.jwt.entity.Project;


@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	public Project createProject(Project p) {
		projectRepository.save(p);
		return p;
	}
	
	public String removeProject(long id) throws Throwable {
		
		Supplier s1 = ()-> new ProjectNotFoundException("No Project with the given Id");
		projectRepository.deleteById(id);
		return "Project has been removed successfully";
	}
	
	public Project updateProject(Project p) throws Throwable {
		
		Supplier s1 = ()-> new ProjectNotFoundException("Project Not Found");
		long id = p.getId();
		Project p1 = projectRepository.findById(id).orElseThrow(s1);
		p1.setProjectName(p.getProjectName());
		p1.setDescription(p.getDescription());
		projectRepository.save(p1);
		return p1;
	}
	
	public List<Project> getProject() {
		List<Project> p1 = projectRepository.findAll();
		return p1;
	}
}
