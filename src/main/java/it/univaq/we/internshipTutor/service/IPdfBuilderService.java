package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Company;
import it.univaq.we.internshipTutor.model.Student;
import it.univaq.we.internshipTutor.model.StudentInternship;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public interface IPdfBuilderService {

    String buildAgreement(Company company);

    String buildTrainingProject(StudentInternship studentInternship);

    String buildFinalReport(StudentInternship studentInternshipInfo);

    void clean(String fileName) throws IOException;

}
