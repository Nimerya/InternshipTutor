package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long> {

     List<Internship> findAll();

     Internship findInternshipById(Long id);

    <S extends Internship> S save(S internship);

}
