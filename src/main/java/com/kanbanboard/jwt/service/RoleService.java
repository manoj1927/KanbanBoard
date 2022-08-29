package com.kanbanboard.jwt.service;

import com.kanbanboard.jwt.dao.RoleDao;
import com.kanbanboard.jwt.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
