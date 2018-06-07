package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Company;

import java.util.List;

public interface ICompanyService {

    List<Company> findAll();

    Company findCompanyById(Long id);

    <S extends Company> S save(S company);
}
