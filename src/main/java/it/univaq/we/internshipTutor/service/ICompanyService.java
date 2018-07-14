package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Company;
import it.univaq.we.internshipTutor.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICompanyService {

    void registerCompany(Company c, User u);

    List<Company> findAll();

    Page<Company> findAll(Pageable pageable);

    Company findCompanyById(Long id);

    <S extends Company> S save(S company);

    void deleteCompanyById(Long id);
}
