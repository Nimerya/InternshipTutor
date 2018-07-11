package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Student;

import java.util.List;

public interface IStudentService {

    public List<Student> findAll();

    public Student findStudentById(Long id);

    <S extends Student> S save(S student);

    void deleteStudentById(Long id);

}
