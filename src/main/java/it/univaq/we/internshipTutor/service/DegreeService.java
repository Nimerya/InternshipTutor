package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Degree;
import it.univaq.we.internshipTutor.repository.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Degree findBy(Long id){
        return degreeRepository.findBy(id);
    }


}
