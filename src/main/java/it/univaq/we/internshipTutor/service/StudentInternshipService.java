package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Company;
import it.univaq.we.internshipTutor.model.Internship;
import it.univaq.we.internshipTutor.model.Student;
import it.univaq.we.internshipTutor.model.StudentInternship;
import it.univaq.we.internshipTutor.repository.InternshipRepository;
import it.univaq.we.internshipTutor.repository.StudentInternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StudentInternshipService implements IStudentInternshipService  {

    @Autowired
    private StudentInternshipRepository studentInternshipRepository;

    @Autowired
    private InternshipRepository internshipRepository;

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

    // List of all student internship that student have done
    @Override
    public List<StudentInternship> completedInternshipsByStudent(Student s){
        return studentInternshipRepository.findStudentInternshipsByStudentAndCompletedTrue(s);
    }

    @Override
    public List<StudentInternship> completedInternshipsByCompany(Company c){
        List<Internship> internships = internshipRepository.findInternshipsByCompanyAndActiveTrue(c);
        List<StudentInternship> studentInternships = new ArrayList<>();
        try{
            for(Internship i : internships){
                List<StudentInternship> si = studentInternshipRepository.findStudentInternshipsByInternshipAndAcceptedTrueAndCompletedTrue(i);
                if(si.size() > 0){
                    studentInternships.addAll(si);
                }
            }
        }catch (NullPointerException e){
            //e.printStackTrace();
        }
        return studentInternships;
    }

    //List of all student internships that are in progress (respect to the student)
    @Override
    public List<StudentInternship> ongoingInternshipsByStudent(Student s){
        return studentInternshipRepository.findStudentInternshipsByStudentAndAcceptedTrueAndCompletedFalse(s);
    }

    @Override
    public List<StudentInternship> ongoingInternshipsByCompany(Company c){
        List<Internship> internships = internshipRepository.findInternshipsByCompanyAndActiveTrue(c);
        List<StudentInternship> studentInternships = new ArrayList<>();
        try{
            for(Internship i : internships){
                List<StudentInternship> si = studentInternshipRepository.findStudentInternshipsByInternshipAndAcceptedTrueAndCompletedFalse(i);
                if(si.size() > 0){
                    studentInternships.addAll(si);
                }
            }
        }catch (NullPointerException e){
            //e.printStackTrace();
        }
        return studentInternships;
    }

    //List of all student internships for wich the student awaiting to be accepted
    @Override
    public List<StudentInternship> internshipsAwaitingForApproval(Student s){
        return studentInternshipRepository.findStudentInternshipsByStudentAndAcceptedFalseAndRejectedFalseAndCompletedFalse(s);
    }


    //List of all student internship that represent the list of students that are candidate for a certain internship
    @Override
    public Page<StudentInternship> findCandidatesByInternship(Pageable p, Internship i){
        return studentInternshipRepository.findStudentInternshipsByInternship/*AndAcceptedFalseAndRejectedFalse*/(p, i);
    }

    @Override
    public void acceptStudentInternship(Long id){
        StudentInternship studentInternship = this.findStudentInternshipById(id);
        studentInternship.setAccepted(Boolean.TRUE);
        studentInternship.setRejected(Boolean.FALSE);
        this.save(studentInternship);
    }

    @Override
    public void rejectStudentInternship(Long id){
        StudentInternship studentInternship = this.findStudentInternshipById(id);
        studentInternship.setAccepted(Boolean.FALSE);
        studentInternship.setRejected(Boolean.TRUE);
        this.save(studentInternship);
    }
}
