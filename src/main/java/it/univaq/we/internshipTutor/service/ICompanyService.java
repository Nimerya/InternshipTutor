package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Company;

import java.util.List;

public interface ICompanyService {

    List<Company> findAll();

    Company findBy(Long id);
}
