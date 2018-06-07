package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    public List<Professor> findAll();

    public Professor findBy(Long id);
}
