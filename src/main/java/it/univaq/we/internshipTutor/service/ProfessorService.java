package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.IProfessorInternshipCountProjection;
import it.univaq.we.internshipTutor.model.Professor;
import it.univaq.we.internshipTutor.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Professor> findAll(Pageable pageable){return professorRepository.findAll(pageable);}

    @Override
    public Professor findProfessorById(Long id){ return professorRepository.findProfessorById(id);}

    @Override
    public <S extends Professor>S save(S professor){
        return professorRepository.save(professor);
    }

    @Override
    public void deleteProfessorById(Long id){
        professorRepository.deleteProfessorById(id);
    }

    @Override
    public List<IProfessorInternshipCountProjection> mostRequestedProfessors(int limit){
        return professorRepository.mostRequestedProfessors(limit);
    }
}
