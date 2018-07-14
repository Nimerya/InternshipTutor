package it.univaq.we.internshipTutor.repository;

import it.univaq.we.internshipTutor.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long>{

    List<Company> findAll();

    Company findCompanyById(Long id);

    Page<Company> findAll(Pageable pageable);

    <S extends Company> S save(S company);

    void deleteCompanyById(Long id);
}