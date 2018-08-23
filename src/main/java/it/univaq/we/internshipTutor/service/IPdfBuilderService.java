package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Company;

import java.io.File;
import java.io.IOException;

public interface IPdfBuilderService {

    String builtAgreement(Company company);

    void clean(String fileName) throws IOException;

}
