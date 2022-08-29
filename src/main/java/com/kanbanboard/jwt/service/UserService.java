package com.kanbanboard.jwt.service;

import com.kanbanboard.exceptions.ResourceNotFoundException;
import com.kanbanboard.jwt.dao.RoleDao;
import com.kanbanboard.jwt.dao.UserDao;
import com.kanbanboard.jwt.entity.Role;
import com.kanbanboard.jwt.entity.User;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserServiceInterface
{

	public static final Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setEmail("admin");
        adminUser.setGender("Male");
        adminUser.setPhoneNo("1234567890");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);
        
//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        user.setEmail(user.getEmail());
        

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

	@Override
	public Boolean deleteUser(String userId) throws ResourceNotFoundException {
		
		User b= userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		userDao.delete(b);
		if(null==b)
		{
			logger.info("Resource is not found in the database hence can't delete the admin ");
			return false;
		}
		logger.info("Resource is successfully deleted in the database");
		return true;
	}

	@Override
	public User updateUserById(String userId, User user) throws ResourceNotFoundException {
		
		User b= userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		user.setEmail(user.getEmail());
		user.setUserFirstName(user.getUserFirstName());
		user.setUserLastName(user.getUserLastName());
		user.setPhoneNo(user.getPhoneNo());
		user.setGender(user.getGender());
		user.setUserName(user.getUserName());
		user.setUserPassword(user.getUserPassword());
		User update= userDao.save(user);
		logger.info("Successfully updated the admin");
		return update;
	}

//	@Override
//	public User getUserById(String userId) throws ResourceNotFoundException {
//		User b= userDao.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
//		User u= userDao.getById(userId);
//		logger.info("Getting admin by Id");
//		
//		return ;
//	}

	@Override
	public List<User> getListOfUsers() {
		List<User> u =userDao.findAll();
		return u;
	}
    
}
