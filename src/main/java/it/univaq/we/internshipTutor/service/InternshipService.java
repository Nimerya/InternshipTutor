package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import it.univaq.we.internshipTutor.model.Internship;
import java.util.List;

@Service
@Transactional
public class InternshipService implements IInternshipService {

    @Autowired
    private InternshipRepository internshipRepository;

    public List<Internship> findAll() {
        return internshipRepository.findAll();
    }

    public Internship findInternshipById(Long id) {
        return internshipRepository.findInternshipById(id);
    }

    @Override
    public Internship save(Internship internship){
        return internshipRepository.save(internship);
    }
}
