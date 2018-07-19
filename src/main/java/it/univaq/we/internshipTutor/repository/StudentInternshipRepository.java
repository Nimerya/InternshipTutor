package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Internship;
import it.univaq.we.internshipTutor.model.Student;
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

    // List of all student internships that student have done
    List<StudentInternship> findStudentInternshipsByStudentAndCompleteTrue(Student s);

    //List of all student internships that are in progress (respect to the student)
    List<StudentInternship> findStudentInternshipsByStudentAndAcceptedTrueAndCompletedFalse(Student s);

    //List of all student internships for wich the student awaiting to be accepted
    List<StudentInternship> findStudentInternshipsByStudentAndAcceptedFalseAndCompletedFalse(Student s);

    //List of all student internships for wich the student awaiting to be accepted, knowing the internship id
    List<StudentInternship> findStudentInternshipsByInternshipAndAcceptedFalse(Internship i);

}
