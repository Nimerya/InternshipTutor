package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Company;
import it.univaq.we.internshipTutor.model.Internship;
import it.univaq.we.internshipTutor.model.Student;
import it.univaq.we.internshipTutor.model.StudentInternship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IStudentInternshipService{

    List<StudentInternship> findAll();

    Page<StudentInternship> findAll(Pageable pageable);

    StudentInternship findStudentInternshipById(Long id);

    <S extends StudentInternship> S save(S studentInternship);

    void deleteStudentInternshipById(Long id);

    // List of all internship that student have done
    List<StudentInternship> completedInternshipsByStudent(Student s);

    List<StudentInternship> completedInternshipsByCompany(Company c);

    //List of all internships that are in progress (respect to the student)
    List<StudentInternship> ongoingInternshipsByStudent(Student s);

    List<StudentInternship> ongoingInternshipsByCompany(Company c);

    //List of all internships for wich the student awaiting to be accepted
    List<StudentInternship> internshipsAwaitingForApproval(Student s);

    //List of all student internship that represent the list of students that are candidate for a certain internship
    Page<StudentInternship> findCandidatesByInternship(Pageable p, Internship i);

    List<StudentInternship> findStudentInternshipByStudentAndInternship(Student student, Internship internship);

    void acceptStudentInternship(Long id);

    void rejectStudentInternship(Long id);





}
