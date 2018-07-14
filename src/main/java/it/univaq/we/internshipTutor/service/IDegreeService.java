package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Degree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDegreeService {

    List<Degree> findAll();

    Page<Degree> findAll(Pageable pageable);

    Degree findDegreeById(Long id);

    <S extends Degree> S save(S degree);

    void deleteDegreeById(Long id);


}
