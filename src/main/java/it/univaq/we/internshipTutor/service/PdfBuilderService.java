package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

@Service
public class PdfBuilderService implements IPdfBuilderService {

    @Autowired
    UserService userService;

    @Value("${spring.file.path.docs}")
    private String docsPath;

    @Override
    public String buildAgreement(Company company) {

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
            form.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public String buildTrainingProject(StudentInternship studentInternship) {

        String trainingProjectForm = docsPath + "/forms/form_training_project.pdf";
        String fileName = "precompiled_training_project_" + studentInternship.getInternship().getTitle()+"_"+studentInternship.getStudent().getMatriculationNumber()+".pdf";

        try {
            Path path = Paths.get(trainingProjectForm);
            byte[] fileContent = Files.readAllBytes(path);

            Student s = studentInternship.getStudent();
            Company c = studentInternship.getInternship().getCompany();
            Internship i = studentInternship.getInternship();

            PDDocument form = PDDocument.load(new ByteArrayInputStream(fileContent));
            PDDocumentCatalog docCatalog = form.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();
            PDField nominativo_tirocinante = acroForm.getField("nominativo_tirocinante");
            PDField tirocinante_nascita_luogo = acroForm.getField("tirocinante_nascita_luogo");
            PDField tirocinante_nascita_provincia = acroForm.getField("tirocinante_nascita_provincia");
            PDField data_nascita_giorno = acroForm.getField("data_nascita_giorno");
            PDField data_nascita_mese = acroForm.getField("data_nascita_mese");
            PDField data_nascita_anno = acroForm.getField("data_nascita_anno");
            PDField tirocinante_residenza = acroForm.getField("tirocinante_residenza");
            PDField tirocinante_residenza_provincia = acroForm.getField("tirocinante_residenza_provincia");
            PDField tirocinante_codice_fiscale = acroForm.getField("tirocinante_codice_fiscale");
            PDField tirocinante_telefono = acroForm.getField("tirocinante_telefono");
            PDField check_handicap_si = acroForm.getField("check_handicap_si");
            PDField check_handicap_no = acroForm.getField("check_handicap_no");
            PDField azienda_ospitante = acroForm.getField("azienda_ospitante");
            PDField luogo_effettuazione_tirocinio = acroForm.getField("luogo_effettuazione_tirocinio");
            PDField periodo_di_tirocinio_n = acroForm.getField("periodo_di_tirocinio_n");
            PDField numero_cfu = acroForm.getField("numero_cfu");
            PDField tutore_universitario = acroForm.getField("tutore_universitario");
            PDField telefono_tutore_universitario = acroForm.getField("telefono_tutore_universitario");
            PDField tutore_aziendale = acroForm.getField("tutore_aziendale");
            PDField telefono_tutore_aziendale = acroForm.getField("telefono_tutore_aziendale");
            PDField obiettivo_tirocinio_1 = acroForm.getField("obiettivo_tirocinio_1");
            PDField obiettivo_tirocinio_2 = acroForm.getField("obiettivo_tirocinio_2");
            PDField obiettivo_tirocinio_3 = acroForm.getField("obiettivo_tirocinio_3");
            PDField obiettivo_tirocinio_4 = acroForm.getField("obiettivo_tirocinio_4");
            PDField modalita_tirocinio_1 = acroForm.getField("modalita_tirocinio_1");
            PDField modalita_tirocinio_2 = acroForm.getField("modalita_tirocinio_2");
            PDField modalita_tirocinio_3 = acroForm.getField("modalita_tirocinio_3");
            PDField modalita_tirocinio_4 = acroForm.getField("modalita_tirocinio_4");
            PDField facilitazioni = acroForm.getField("facilitazioni");

            nominativo_tirocinante.setValue(s.getUser().getFirstName());
            tirocinante_nascita_luogo.setValue(s.getBirthplaceCity());
            tirocinante_nascita_provincia.setValue(s.getBirthplaceProvince());

            // date.getDay() is deprecated
            Calendar birthdayCalendar = Calendar.getInstance();
            birthdayCalendar.setTime(s.getBirthday());

            data_nascita_giorno.setValue(String.valueOf(birthdayCalendar.get(Calendar.DAY_OF_MONTH)));
            data_nascita_mese.setValue(String.valueOf(birthdayCalendar.get(Calendar.MONTH)));
            data_nascita_anno.setValue(String.valueOf(birthdayCalendar.get(Calendar.YEAR)).substring(2));

            tirocinante_residenza.setValue(s.getResidenceAddress());
            tirocinante_residenza_provincia.setValue(s.getResidenceProvince());
            tirocinante_codice_fiscale.setValue(s.getFiscalCode());
            tirocinante_telefono.setValue(s.getUser().getPhoneNumber());

            if(s.getHandicap()){
                ((PDCheckBox) check_handicap_si).check();
            }else{
                ((PDCheckBox) check_handicap_no).check();
            }
            azienda_ospitante.setValue(c.getName());
            luogo_effettuazione_tirocinio.setValue(i.getAddress());
            periodo_di_tirocinio_n.setValue(String.valueOf(i.getLength()));

            numero_cfu.setValue(String.valueOf(studentInternship.getCfu()));
            tutore_universitario.setValue(studentInternship.getProfessor().getFirstName()+studentInternship.getProfessor().getLastName());
            telefono_tutore_universitario.setValue(studentInternship.getProfessor().getPhoneNumber());
            User companyTutor = userService.findUserByCompany(c.getId());
            tutore_aziendale.setValue(companyTutor.getFirstName()+companyTutor.getLastName());
            telefono_tutore_aziendale.setValue(companyTutor.getPhoneNumber());

            // TODO check language
            String obiettivo = i.getGoalsItIt();
            String modalita = i.getModeItIt();

            try{
                obiettivo_tirocinio_1.setValue(obiettivo.substring(0, 65+1));
                obiettivo_tirocinio_2.setValue(obiettivo.substring(65, 65*2+1));
                obiettivo_tirocinio_3.setValue(obiettivo.substring(65*2+1, 65*3+1));
                obiettivo_tirocinio_4.setValue(obiettivo.substring(65*3+1, 65*4+1));

                modalita_tirocinio_1.setValue(modalita.substring(0, 65+1));
                modalita_tirocinio_2.setValue(modalita.substring(65, 65*2+1));
                modalita_tirocinio_3.setValue(modalita.substring(65*2+1, 65*3+1));
                modalita_tirocinio_4.setValue(modalita.substring(65*3+1, 65*4+1));
            }catch (StringIndexOutOfBoundsException e){
                //e.printStackTrace();
            }

            facilitazioni.setValue(i.getFacilitations());

            form.save(new File(docsPath, fileName));
            form.close();

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
