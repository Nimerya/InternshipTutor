package it.univaq.we.internshipTutor.repository;

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

    // interships completed (terminated) given the student id
    List<Internship> findInternshipsByStudentInternshipsAndCompleted(Long id);

    // active internships but not yet completed
    List<Internship> findInternshipsByStudentInternshipsAndActiveAndCompletedFalse(Long id);

    // not yet accepted internships (w.r.t. the student)
    List<Internship> findInternshipsByStudentInternshipsAndActiveTrueAndAcceptedFalse(Long id);

}
