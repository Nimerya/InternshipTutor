package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    Page<Role> findAll(Pageable pageable);

    Role findRoleById(Long id);

    Role findRoleByName(String name);

    <S extends Role> S save(S role);

    void deleteRoleById(Long id);
}
