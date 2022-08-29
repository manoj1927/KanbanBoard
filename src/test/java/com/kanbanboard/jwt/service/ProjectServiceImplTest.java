package com.kanbanboard.jwt.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kanbanboard.jwt.dao.ProjectRepository;
import com.kanbanboard.jwt.entity.Project;
import com.kanbanboard.jwt.service.ProjectService;


@SpringBootTest
public class ProjectServiceImplTest {
	
	@Autowired
	ProjectService projectService;
	
	@MockBean
	ProjectRepository projectRepository;
	
	@Test
	void testCreateProject() {
		
		Project p = new Project();
		p.setId(123);
		p.setProjectName("IDigital");
		p.setDescription("Kanban Board");
		
		Mockito.when(projectRepository.save(p)).thenReturn(p);
		 assertThat(projectService.createProject(p)).isEqualTo(p);
	}
	
	@Test
	void testUpdateProject() throws Throwable {
		Project p = new Project();
		p.setId(123);
		p.setProjectName("IDigital");
		p.setDescription("Kanban Board");
		
		Optional<Project> p1 = Optional.of(p);
		Mockito.when(projectRepository.findById((long) 123)).thenReturn(p1);
		Mockito.when(projectRepository.save(p)).thenReturn(p);
		p.setProjectName("IDigital9");
		p.setDescription("KanbanBoard");
		
		assertThat(projectService.updateProject(p)).isEqualTo(p);
	}
	
	@Test
	void testDeleteProject() throws Throwable {
		Project p = new Project();
		p.setId(123);
		p.setProjectName("IDigital");
		p.setDescription("KanbanBoard");
		
		Optional<Project> p1 = Optional.of(p);
		Mockito.when(projectRepository.findById((long) 123)).thenReturn(p1);
		assertThat(projectService.removeProject(123)).isEqualTo("Project has been removed successfully");
	}

}
