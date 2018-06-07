package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface DegreeRepository extends JpaRepository<Degree,Long>{

    public List<Degree> findAll();

    public Degree findBy(Long id);
}
