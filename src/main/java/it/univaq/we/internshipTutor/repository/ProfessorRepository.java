package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findAll();

    Professor findProfessorById(Long id);

    <S extends Professor> S save(S professor);

    void deleteProfessorById(Long id);
}
