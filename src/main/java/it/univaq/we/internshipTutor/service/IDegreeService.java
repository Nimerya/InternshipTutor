package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Degree;
import java.util.List;

public interface IDegreeService {

    public List<Degree> findAll();

    public Degree findBy(Long id);

}
