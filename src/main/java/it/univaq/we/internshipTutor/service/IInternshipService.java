package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Internship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInternshipService {

     List<Internship> findAll();

    Page<Internship> findAll(Pageable pageable);

     Internship findInternshipById(Long id);

    <S extends Internship> S save(S internship);

    void deleteInternshipById(Long id);

    List<Internship> findInternshipsByStudentInternshipsAndCompleted(Long id);

    List<Internship> findInternshipsByStudentInternshipsAndActiveBeforeCompleted(Long id);

    List<Internship> findInternshipsByStudentInternshipsAndActiveFalse(Long id);



}
