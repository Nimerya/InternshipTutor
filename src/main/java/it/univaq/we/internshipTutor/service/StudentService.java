package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.repository.StudentRepository;
import it.univaq.we.internshipTutor.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() { return studentRepository.findAll(); }

    public Student findStudentById(Long id) { return studentRepository.findStudentById(id); }

    @Override
    public <S extends Student>S save(S student){
        return studentRepository.save(student);
    }


    @Override
    public void deleteStudentById(Long id){
        studentRepository.deleteStudentById(id);
    }

}
