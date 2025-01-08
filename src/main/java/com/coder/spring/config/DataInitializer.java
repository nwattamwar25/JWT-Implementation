package com.coder.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.coder.spring.models.EmployeeRole;
import com.coder.spring.models.Role;
import com.coder.spring.repository.RoleRepository;

@Component
public class DataInitializer {

    @Autowired
    RoleRepository roleRepository;

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        for (EmployeeRole role : EmployeeRole.values()) {
            if (!roleRepository.findByName(role).isPresent()) {
                Role newRole = new Role();
                newRole.setName(role);
                roleRepository.save(newRole);
            }
        }
    }
}