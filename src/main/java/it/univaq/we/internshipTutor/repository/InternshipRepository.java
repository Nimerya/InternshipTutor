package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Company;
import it.univaq.we.internshipTutor.model.Internship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long> {

    List<Internship> findAll();

    Page<Internship> findAll(Pageable pageable);

    Internship findInternshipById(Long id);

    <S extends Internship> S save(S internship);

    void deleteInternshipById(Long id);

    //List of all active internships published by a given company
    List<Internship> findInternshipsByCompanyAndActiveTrue(Company c);

    //List of all inactive internships published by a given company
    List<Internship> findInternshipsByCompanyAndActiveFalse(Company c);


}
