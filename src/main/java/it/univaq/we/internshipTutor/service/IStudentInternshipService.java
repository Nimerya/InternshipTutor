package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.StudentInternship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentInternshipService{

    List<StudentInternship> findAll();

    Page<StudentInternship> findAll(Pageable pageable);

    StudentInternship findStudentInternshipById(Long id);

    <S extends StudentInternship> S save(S studentInternship);

    void deleteStudentInternshipById(Long id);

    // List of all internship that student have done
    List<StudentInternship> findStudentInternshipsByStudentAndCompleteTrue(Long id);

    //List of all internships that are in progress (respect to the student)
    List<StudentInternship> studentInternshipsInProgress(Long id);

    //List of all internships for wich the student awaiting to be accepted
    List<StudentInternship> studentInternshipsAwaitingAccepted(Long id);



}
