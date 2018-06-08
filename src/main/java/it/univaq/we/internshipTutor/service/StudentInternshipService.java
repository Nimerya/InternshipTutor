package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.StudentInternship;
import it.univaq.we.internshipTutor.repository.StudentInternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentInternshipService implements IStudentInternshipService  {

    @Autowired
    private StudentInternshipRepository studentInternshipRepository;

    @Override
    public List<StudentInternship> findAll(){ return studentInternshipRepository.findAll();}

    @Override
    public StudentInternship findStudentInternshipById(Long id){ return studentInternshipRepository.findStudentInternshipById(id);}

    @Override
    public <S extends StudentInternship>S save(S studentInternship){
        return studentInternshipRepository.save(studentInternship);
    }

}
