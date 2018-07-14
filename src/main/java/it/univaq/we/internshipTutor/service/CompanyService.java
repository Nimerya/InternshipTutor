package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Company;
import it.univaq.we.internshipTutor.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() { return companyRepository.findAll();}

    @Override
    public Page<Company> findAll(Pageable pageable){return companyRepository.findAll(pageable);}

    @Override
    public Company findCompanyById(Long id) { return companyRepository.findCompanyById(id);}

    @Override
    public <S extends Company>S save(S company){
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompanyById(Long id){
        companyRepository.deleteCompanyById(id);
    }
}

