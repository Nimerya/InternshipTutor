package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.StudentInternship;

import java.util.List;

public interface IStudentInternshipService{

    List<StudentInternship> findAll();

    StudentInternship findStudentInternshipById(Long id);

    <S extends StudentInternship> S save(S studentInternship);

}
