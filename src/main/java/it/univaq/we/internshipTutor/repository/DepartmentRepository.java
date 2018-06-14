package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    List<Department> findAll();

    Department findDepartmentById(Long id);

    <S extends Department> S save(S department);

    void deleteDepartmentById(Long id);


}
