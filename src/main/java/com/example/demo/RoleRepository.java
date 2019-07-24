package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;

public interface RoleRepository extends CrudRepository<Role, Long>{
    Role findByRole(String role);
}
