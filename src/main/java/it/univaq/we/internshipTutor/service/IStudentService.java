package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {

    public List<Student> findAll();

    Page<Student> findAll(Pageable pageable);

    public Student findStudentById(Long id);

    <S extends Student> S save(S student);

    void deleteStudentById(Long id);

}
