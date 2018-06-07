package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Internship;
import java.util.List;

public interface IInternshipService {

    public List<Internship> findAll();

    public Internship findInternshipById(Long id);

}
