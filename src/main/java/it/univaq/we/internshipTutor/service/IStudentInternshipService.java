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


}
