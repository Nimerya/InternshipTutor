package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Degree;
import java.util.List;

public interface IDegreeService {

    List<Degree> findAll();

    Degree findDegreeById(Long id);

    <S extends Degree> S save(S degree);

}
