package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Professor;

import java.util.List;

public interface IProfessorService {

    List<Professor> findAll();

    Professor findProfessorById(Long id);

    <S extends Professor> S save(S professor);
}
