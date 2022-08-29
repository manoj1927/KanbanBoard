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
import org.springframework.web.client.RestTemplate;

import com.kanbanboard.jwt.entity.Tasks;
import com.kanbanboard.jwt.service.TaskService;


@CrossOrigin
@RestController
@RequestMapping("/KanbanApi")
public class TasksController {
	
	@Autowired
	private TaskService taskService;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@PostMapping("/addTask")
	public ResponseEntity<Tasks> addTask(@Valid @RequestBody Tasks t)
	{
		Tasks ts =taskService.addTask(t);
		ResponseEntity<Tasks> re=new ResponseEntity<Tasks>(ts,HttpStatus.OK);
		return re;
		
	}

	@GetMapping("/getTasks")
	public ResponseEntity<List<Tasks>> getTasks()
	{
		//this.restTemplate.getForObject( null,List.class);//place the url in place of null 
		List<Tasks> lt = taskService.getTasks();
		ResponseEntity<List<Tasks>> re=new ResponseEntity<List<Tasks>>(lt,HttpStatus.OK);
		return re;
		
	}
	
	@PutMapping("/updateTasks")
	public ResponseEntity<Tasks> updateTasks(@RequestBody Tasks t) throws Throwable
	{
		Tasks t1=taskService.updateTasks(t);
		ResponseEntity<Tasks> re =new ResponseEntity<Tasks>(t1,HttpStatus.OK);
		return re;
	}
	
	@DeleteMapping(path="/deleteTask/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable long id)
	{
		taskService.delete(id);
		
		ResponseEntity<String> re=new ResponseEntity<String>("Deleted",HttpStatus.OK);
		return re;
	}
}
