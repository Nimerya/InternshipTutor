package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long> {

    public List<Internship> findAll();

    public Internship findInternshipById(Long id);

}
