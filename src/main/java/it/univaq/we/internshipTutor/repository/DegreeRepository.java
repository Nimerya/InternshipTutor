package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface DegreeRepository extends JpaRepository<Degree,Long>{

    List<Degree> findAll();

    Degree findDegreeById(Long id);

    <S extends Degree> S save(S degree);
}
