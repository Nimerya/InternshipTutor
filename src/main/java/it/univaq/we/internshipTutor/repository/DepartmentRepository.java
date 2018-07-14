package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    List<Department> findAll();

    Page<Department> findAll(Pageable pageable);

    Department findDepartmentById(Long id);

    <S extends Department> S save(S department);

    void deleteDepartmentById(Long id);


}
