package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long>{

    public List<Company> findAll();

    public Company findBy(Long id);
}
