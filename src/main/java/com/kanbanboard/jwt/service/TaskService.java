package com.kanbanboard.jwt.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanbanboard.exceptions.ResourceNotFoundException;
import com.kanbanboard.jwt.dao.TasksRepository;
import com.kanbanboard.jwt.entity.Tasks;


@Service
public class TaskService {
	
	@Autowired
	private TasksRepository tasksRepository;
	
	public Tasks addTask(Tasks t) 
	{
		tasksRepository.save(t);
		return t;
	}
	
	public List<Tasks> getTasks()
	{
		List<Tasks> lt=tasksRepository.findAll();
		return lt;
	}
	public List<Tasks> addTasks(List<Tasks> t)
	{
		tasksRepository.saveAll(t);
		return t;
	}
	public String delete(long id) 
	{
		tasksRepository.deleteById(id);
		return "Deleted Sucessfully";
	}
	public Tasks updateTasks(Tasks t) throws Throwable
	{
		Long id=t.getId();
		
		@SuppressWarnings("rawtypes")
		Supplier s1= ()->new ResourceNotFoundException("Tasks Does not exist in the database");
		@SuppressWarnings("unchecked")
		Tasks t1=tasksRepository.findById(id).orElseThrow(s1);
		
		t1.setTitle(t.getTitle());
			t1.setDescription(t.getDescription());
			tasksRepository.save(t1);
			return t1;	
		
	}
}
