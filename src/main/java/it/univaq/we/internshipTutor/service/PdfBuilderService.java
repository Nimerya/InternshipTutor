package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.Company;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PdfBuilderService implements IPdfBuilderService {

    @Value("${spring.file.path.docs}")
    private String docsPath;

    @Override
    public String builtAgreement(Company company) {

        String agreementForm = docsPath + "/forms/form_agreement.pdf";
        String fileName = "precompiled_agreement_" + company.getName()+".pdf";

        try {
            Path path = Paths.get(agreementForm);
            byte[] fileContent = Files.readAllBytes(path);

            PDDocument form = PDDocument.load(new ByteArrayInputStream(fileContent));
            PDDocumentCatalog docCatalog = form.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();
            PDField azienda = acroForm.getField("azienda");
            PDField indirizzo_sede_legale = acroForm.getField("indirizzo_sede_legale");
            PDField codice_fiscale = acroForm.getField("codice_fiscale");
            PDField rappresentante = acroForm.getField("rappresentante");
            PDField foro_competente = acroForm.getField("foro_competente");

            azienda.setValue(company.getName());
            indirizzo_sede_legale.setValue(company.getAddress());
            codice_fiscale.setValue(company.getVatNumber());
            rappresentante.setValue(company.getAttorney());
            foro_competente.setValue(company.getJurisdiction());

            form.save(new File(docsPath, fileName));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public void clean(String fileName) throws IOException {
        Path path = Paths.get(docsPath+fileName);
        Files.deleteIfExists(path);
    }
}
