package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.IProfessorInternshipCountProjection;
import it.univaq.we.internshipTutor.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {


    List<Professor> findAll();

    Page<Professor> findAll(Pageable pageable);

    Professor findProfessorById(Long id);

    <S extends Professor> S save(S professor);

    void deleteProfessorById(Long id);

    @Query(value =  "SELECT p.id as id, p.department_id as department, p.first_name as firstName, p.last_name as lastName, COUNT(professor_id) as count FROM professor p LEFT JOIN student_internship si ON p.id = si.professor_id GROUP BY si.professor_id ORDER BY count DESC LIMIT ?1", nativeQuery = true)
    List<IProfessorInternshipCountProjection> mostRequestedProfessors(int limit);
}
