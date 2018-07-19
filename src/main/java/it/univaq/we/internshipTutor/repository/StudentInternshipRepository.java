package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.StudentInternship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentInternshipRepository extends JpaRepository<StudentInternship, Long> {

    List<StudentInternship> findAll();

    Page<StudentInternship> findAll(Pageable pageable);

    StudentInternship findStudentInternshipById(Long id);

    <S extends StudentInternship> S save(S studentInternship);

    void deleteStudentInternshipById(Long id);

    // List of all internships that student have done
    List<StudentInternship> findStudentInternshipsByStudentAndCompleteTrue(Long id);

    //List of all internships that are in progress (respect to the student)
    @Query(value =  "SELECT si.student_id," +
            "               si.internship_id as internship_id," +
            "               si.cfu as cfu," +
            "               si.professor_id as professor_id," +
            "               si.review as review," +
            "               si.accepted as accepted," +
            "               si.completed as completed," +
            "               si.id as id" +
            "        FROM student_internship si" +
            "        WHERE si.student_id = ? and si.completed = FALSE and si.accepted = TRUE ", nativeQuery = true)
    List<StudentInternship> studentInternshipsInProgress(Long id);

    //List of all internships for wich the student awaiting to be accepted
    @Query(value =  "SELECT si.student_id," +
            "               si.internship_id as internship_id," +
            "               si.cfu as cfu," +
            "               si.professor_id as professor_id," +
            "               si.review as review," +
            "               si.accepted as accepted," +
            "               si.completed as completed," +
            "               si.id as id" +
            "        FROM student_internship si" +
            "        WHERE si.student_id = ? and si.completed = FALSE and si.accepted = FALSE ", nativeQuery = true)
    List<StudentInternship> studentInternshipsAwaitingAccepted(Long id);

}
