package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Professor;

import java.util.List;

public interface IProfessorService {

    List<Professor> findAll();

    Professor findBy(Long id);
}
