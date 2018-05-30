package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Department;
import java.util.List;

public interface IDepartmentService {

    public List<Department> findAll();

    public Department findBy(Long id);

}
