package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Company;
import it.univaq.we.internshipTutor.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import it.univaq.we.internshipTutor.model.Internship;
import java.util.List;

@Service
@Transactional
public class InternshipService implements IInternshipService {

    @Autowired
    private InternshipRepository internshipRepository;

    @Override
    public List<Internship> findAll() {
        return internshipRepository.findAll();
    }

    @Override
    public List<Internship> findActiveInternships() {
        return internshipRepository.findInternshipsByActiveTrue();
    }

    @Override
    public Page<Internship> findActiveInternships(Pageable pageable) {
        return internshipRepository.findInternshipsByActiveTrue(pageable);
    }

    @Override
    public Page<Internship> findAll(Pageable pageable){return internshipRepository.findAll(pageable);}

    public Internship findInternshipById(Long id) {
        return internshipRepository.findInternshipById(id);
    }

    @Override
    public <S extends Internship>S save(S internship){
        return internshipRepository.save(internship);
    }

    @Override
    public void deleteInternshipById(Long id){
        internshipRepository.deleteInternshipById(id);
    }

    //List of all active internships published by a given company
    @Override
    public List<Internship> findActiveInternships(Company c){
        return internshipRepository.findInternshipsByCompanyAndActiveTrue(c);
    }

    //List of all inactive internships published by a given company
    @Override
    public List<Internship> findInactiveInternships(Company c){
        return internshipRepository.findInternshipsByCompanyAndActiveFalse(c);
    }

    @Override
    public Page<Internship> findIntershipsByQuery(Pageable pageable, String q){
        return internshipRepository.findInternshipByQuery(pageable, q, q, q, q, q);
    }



}
