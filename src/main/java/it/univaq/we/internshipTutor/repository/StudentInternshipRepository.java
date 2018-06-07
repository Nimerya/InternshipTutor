package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.StudentInternship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentInternshipRepository extends JpaRepository<StudentInternship, Long> {

    public List<StudentInternship> findAll();

    public StudentInternship findBy(Long id);
}
