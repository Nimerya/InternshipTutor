package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    Role findRoleById(Long id);

    Role findRoleByName(String name);

    <S extends Role> S save(S role);

    void deleteRoleById(Long id);
}
