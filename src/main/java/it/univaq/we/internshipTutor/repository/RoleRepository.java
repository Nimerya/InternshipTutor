package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAll();

    Role findRoleById(Long id);

    <S extends Role> S save(S role);

    void deleteRoleById(Long id);
}
