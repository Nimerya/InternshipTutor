package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Student;
import it.univaq.we.internshipTutor.model.StudentInternship;
import it.univaq.we.internshipTutor.repository.StudentInternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<StudentInternship> findAll(Pageable pageable){return studentInternshipRepository.findAll(pageable);}

    @Override
    public StudentInternship findStudentInternshipById(Long id){ return studentInternshipRepository.findStudentInternshipById(id);}

    @Override
    public <S extends StudentInternship>S save(S studentInternship){ return studentInternshipRepository.save(studentInternship); }

    @Override
    public void deleteStudentInternshipById(Long id){ studentInternshipRepository.deleteStudentInternshipById(id); }

    // List of all internship that student have done
    @Override
    public List<StudentInternship> completedInternships(Student s){
        return studentInternshipRepository.findStudentInternshipsByStudentAndCompletedTrue(s);
    }

    //List of all internships that are in progress (respect to the student)
    @Override
    public List<StudentInternship> ongoingInternships(Student s){
        return studentInternshipRepository.studentInternshipsInProgress(s.getId());
    }

    //List of all internships for wich the student awaiting to be accepted
    @Override
    public List<StudentInternship> internshipsAwaitingForApproval(Student s){
        return studentInternshipRepository.studentInternshipsAwaitingAccepted(s.getId());
    }





}
