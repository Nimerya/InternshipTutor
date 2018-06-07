package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Internship;
import java.util.List;

public interface IInternshipService {

     List<Internship> findAll();

     Internship findInternshipById(Long id);

    <S extends Internship> S save(S internship);


}
