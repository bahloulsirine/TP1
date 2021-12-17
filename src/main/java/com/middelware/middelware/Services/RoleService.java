package com.middelware.middelware.Services;

import com.middelware.middelware.Models.Role;
import com.middelware.middelware.Repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Component
@Transactional
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;
    public Role geRoleById(Long roleId){return roleRepo.findById(roleId).get();}
}
