package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Action;
import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.dao.RoleAction;
import com.management.student.studentresult.repository.ActionRepository;
import com.management.student.studentresult.repository.RoleActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private RoleActionRepository roleActionRepository;
    @Autowired
    private RoleService roleService;

    public Action addAction(Action action) {
        return actionRepository.save(action);
    }

    public List<String> getActionsByRole(String roleName) {
        Role role = roleService.getRoleByName(roleName);
        if (role == null)
            return null;
        List<RoleAction> roleActionList =  roleActionRepository.findByRoleAndStatus(role, "ACTIVE");
        return roleActionList.stream().map( roleAction -> {
            return roleAction.getAction().getName();
        }).collect(Collectors.toList());
    }

}