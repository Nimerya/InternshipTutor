package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Professor;
import it.univaq.we.internshipTutor.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProfessorService implements IProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public List<Professor> findAll(){ return professorRepository.findAll();}

    @Override
    public Professor findProfessorById(Long id){ return professorRepository.findProfessorById(id);}

    @Override
    public Professor save(Professor professor){
        return professorRepository.save(professor);
    }
}
