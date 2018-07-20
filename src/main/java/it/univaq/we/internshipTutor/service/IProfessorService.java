package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.IProfessorInternshipCountProjection;
import it.univaq.we.internshipTutor.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProfessorService {

    List<Professor> findAll();

    Page<Professor> findAll(Pageable pageable);

    Professor findProfessorById(Long id);

    <S extends Professor> S save(S professor);

    void deleteProfessorById(Long id);

    List<IProfessorInternshipCountProjection> mostRequestedProfessors(int limit);
}
