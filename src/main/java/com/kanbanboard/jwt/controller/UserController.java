package com.kanbanboard.jwt.controller;

import com.kanbanboard.exceptions.ResourceNotFoundException;
import com.kanbanboard.jwt.entity.User;
import com.kanbanboard.jwt.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.PostConstruct;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public ResponseEntity< User> registerNewUser(@RequestBody User user) {
    	User u= userService.registerNewUser(user);
        return  ResponseEntity.ok(u);
    }
    
    @GetMapping(path = "/getAllUsers")
    public ResponseEntity<List<User>> getUsers()
    {
    	List<User> list= userService.getListOfUsers();
    	return ResponseEntity.ok(list);
    }
    
    @PutMapping(path = "/updateUserById/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "userId")String userId,@RequestBody User u) throws ResourceNotFoundException
    {
    	User update= userService.updateUserById(userId, u);
    	return ResponseEntity.ok(update);
    }
    
    @DeleteMapping(path = "/deleteById/{userId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable(value = "userId")String userId) throws ResourceNotFoundException
    {
    	Boolean b= userService.deleteUser(userId);
    	return ResponseEntity.ok(b);
    	
    }
    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
}
