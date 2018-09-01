package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICompanyService {

    void registerCompany(Company c, User u) throws Exception;

    List<Company> findAll();

    Page<Company> findAll(Pageable pageable);

    Company findCompanyById(Long id);

    <S extends Company> S save(S company);

    void deleteCompanyById(Long id);

    List<ICompanyStudentInternshipCountProjection> companiesWithMostStudents(int limit);

    List<IBestCompanyProjection> bestCompanies(int limit);

    List<IWorstCompanyProjection> worstCompanies(int limit);

    List<Company> findCompaniesByActiveFalse();

    Page<Company> findCompaniesByActiveFalse(Pageable pageable);


}
