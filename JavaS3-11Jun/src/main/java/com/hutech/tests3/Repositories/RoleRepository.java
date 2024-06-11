package com.hutech.tests3.Repositories;


import com.hutech.tests3.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, String> {
    @Query("SELECT r FROM Role r where r.role_name=?1 ")
    Role findOneByName(String name);
}