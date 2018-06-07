package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Department;
import java.util.List;

public interface IDepartmentService {

    List<Department> findAll();

    Department findDepartmentById(Long id);

    <S extends Department> S save(S department);

}
