package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDepartmentService {

    List<Department> findAll();

    Page<Department> findAll(Pageable pageable);

    Department findDepartmentById(Long id);

    <S extends Department> S save(S department);

    void deleteDepartmentById(Long id);



}
