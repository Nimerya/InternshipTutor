package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findAll();

    Page<Professor> findAll(Pageable pageable);

    Professor findProfessorById(Long id);

    <S extends Professor> S save(S professor);

    void deleteProfessorById(Long id);
}
