package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Student;
import it.univaq.we.internshipTutor.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {

    void registerStudent(Student s, User u) throws Exception;

    List<Student> findAll();

    Page<Student> findAll(Pageable pageable);

    Student findStudentById(Long id);

    <S extends Student> S save(S student);

    void deleteStudentById(Long id);

}
