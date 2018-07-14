package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Degree;
import it.univaq.we.internshipTutor.repository.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DegreeService implements IDegreeService {

    @Autowired
    private DegreeRepository degreeRepository;

    @Override
    public List<Degree> findAll(){
        return degreeRepository.findAll();
    }

    @Override
    public Page<Degree> findAll(Pageable pageable) {
        return degreeRepository.findAll(pageable);
    }

    @Override
    public Degree findDegreeById(Long id){
        return degreeRepository.findDegreeById(id);
    }

    @Override
    public <S extends Degree>S save(S degree){
        return degreeRepository.save(degree);
    }

    @Override
    public void deleteDegreeById(Long id){
        degreeRepository.deleteDegreeById(id);
    }



}
