package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Company;
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

    //List of all active internships published by a given company
    List<Internship> findActiveInternships(Company c);

    //List of all inactive internships published by a given company
    List<Internship> findInactiveInternships(Company c);





}
