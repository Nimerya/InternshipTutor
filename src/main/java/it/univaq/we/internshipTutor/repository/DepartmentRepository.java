package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    public List<Department> findAll();

    public Department findBy(Long id);
}
