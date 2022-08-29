package com.kanbanboard.jwt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kanbanboard.jwt.dao.TasksRepository;
import com.kanbanboard.jwt.entity.Tasks;
import com.kanbanboard.jwt.service.TaskService;



@SpringBootTest
class TaskServiceTest {
	
	@Autowired
	TaskService taskService;
	
	@MockBean
	TasksRepository tasksRepository;
	

	
	@Test
	void testAddTask()
	{
		Tasks t1 = new Tasks();
		t1.setId((long) 1);
		t1.setTitle("task1");
		t1.setDescription("status");
		Mockito.when(tasksRepository.save(t1)).thenReturn(t1);
		assertThat(taskService.addTask(t1)).isEqualTo(t1);
		
	}
	
	@Test
	void testGetOrders()
	{
		Tasks t1 = new Tasks();
		t1.setId((long) 1);
		t1.setTitle("task1");
		t1.setDescription("status");
		

		Tasks t2 = new Tasks();
		t2.setId((long) 2);
		t2.setTitle("task2");
		t2.setDescription("status2");
		
		List<Tasks> tl= new ArrayList<>();
		tl.add(t1);
		tl.add(t2);
		
		Mockito.when(tasksRepository.findAll()).thenReturn(tl);
		assertThat(taskService.getTasks()).isEqualTo(tl);		
	}
	
	@Test
	void testDeleteTasks() 
	{
		Tasks t1 = new Tasks();
		t1.setId((long) 1);
		t1.setTitle("task1");
		t1.setDescription("status");
		Optional<Tasks> t = Optional.of(t1);
		
		Mockito.when(tasksRepository.findById((long) 1)).thenReturn(t);
		Mockito.when(tasksRepository.existsById(t1.getId())).thenReturn(false);
		
		assertFalse(tasksRepository.existsById(t1.getId()));
	}

}