package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAll();

    Page<Role> findAll(Pageable pageable);

    Role findRoleById(Long id);

    Role findRoleByName(String name);

    <S extends Role> S save(S role);

    void deleteRoleById(Long id);
}
