package it.univaq.we.internshipTutor.service;

import it.univaq.we.internshipTutor.model.*;
import org.apache.commons.text.WordUtils;
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
        String fileName = "precompiled_agreement_" + company.getName() + ".pdf";

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
        String fileName = "precompiled_training_project_" + studentInternship.getInternship().getTitle() + "_" + studentInternship.getStudent().getMatriculationNumber() + ".pdf";

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
            PDField numero_ore_tirocinio = acroForm.getField("numero_ore_tirocinio");
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

            if (s.getHandicap()) {
                ((PDCheckBox) check_handicap_si).check();
            } else {
                ((PDCheckBox) check_handicap_no).check();
            }
            azienda_ospitante.setValue(c.getName());
            luogo_effettuazione_tirocinio.setValue(i.getAddress());
            numero_ore_tirocinio.setValue(String.valueOf(i.getLength()));

            numero_cfu.setValue(String.valueOf(studentInternship.getCfu()));
            tutore_universitario.setValue(studentInternship.getProfessor().getFirstName() + studentInternship.getProfessor().getLastName());
            telefono_tutore_universitario.setValue(studentInternship.getProfessor().getPhoneNumber());
            User companyTutor = userService.findUserByCompany(c.getId());
            tutore_aziendale.setValue(companyTutor.getFirstName() + companyTutor.getLastName());
            telefono_tutore_aziendale.setValue(companyTutor.getPhoneNumber());



            String obiettivo = i.getGoalsItIt();
            String modalita = i.getModeItIt();
            /*Numero di righe disponibili nella sezione obiettivo e modalità del pdf*/
            int numberOfRows = 4;

            try {
                int lenObiettivo = obiettivo.length();
                /*Numero di caratteri da inserire in ogni riga della sezione obiettivo del pdf*/
                /*Nota: lenObiettivo potrebbe non essere divisibile per 4, quindi calcoliamo un float*/
                float subtotalObiettivo = lenObiettivo / numberOfRows;
                /*Prendiamo la parte intera superiore del valore calcolato precedentemente:
                 * se la parte intera inferiore è minore di 65 (dopo una verifica manuale è
                 * stato deciso che il numero di caratteri "accettabili" in una sola riga non
                 * potesse essere superiore a 65) allora settiamo il valore a 65, altrimenti
                 * lasciamo il valore calcolato */
                int charsInEachObjectiveRow = ((int) Math.floor(subtotalObiettivo) < 65) ? 65 : (int) Math.floor(subtotalObiettivo);
                String wrapped = WordUtils.wrap(obiettivo, charsInEachObjectiveRow);
                String[] wrapsObiettivo = wrapped.split(System.lineSeparator());

                /*I wrap comutati vengono inseriti effettivamente nelle rispettive righe della sezione obiettivo del pdf*/
                obiettivo_tirocinio_1.setValue(wrapsObiettivo[0]);
                obiettivo_tirocinio_2.setValue((wrapsObiettivo.length >= 2) ? wrapsObiettivo[1] : "");
                obiettivo_tirocinio_3.setValue((wrapsObiettivo.length >= 3) ? wrapsObiettivo[2] : "");
                obiettivo_tirocinio_4.setValue((wrapsObiettivo.length >= 4) ? wrapsObiettivo[3] : "");

            }catch (Exception e){
                // non c'è l'obiettivo
            }

            try{
                int lenModalita = modalita.length();
                /*Numero di caratteri da inserire in ogni riga della sezione modalità del pdf*/
                /*Nota: lenModalita potrebbe non essere divisibile per 4, quindi calcoliamo un float*/
                float subtotalModalita = lenModalita / numberOfRows;
                /*Prendiamo la parte intera superiore del valore calcolato precedentemente:
                 * se la parte intera inferiore è minore di 65 (dopo una verifica manuale è
                 * stato deciso che il numero di caratteri "accettabili" in una sola riga non
                 * potesse essere superiore a 65) allora settiamo il valore a 65, altrimenti
                 * lasciamo il valore calcolato */
                int charsInEachModalityRow = ((int) Math.floor(subtotalModalita) < 65) ? 65 : (int) Math.floor(subtotalModalita);
                String wrapped = WordUtils.wrap(modalita, charsInEachModalityRow);
                String[] wrapsModalita = wrapped.split(System.lineSeparator());

                /*I wrap comutati vengono inseriti effettivamente nelle rispettive righe della sezione modalita del pdf*/
                modalita_tirocinio_1.setValue(wrapsModalita[0]);
                modalita_tirocinio_2.setValue((wrapsModalita.length >= 2) ? wrapsModalita[1] : "");
                modalita_tirocinio_3.setValue((wrapsModalita.length >= 3) ? wrapsModalita[2] : "");
                modalita_tirocinio_4.setValue((wrapsModalita.length >= 4) ? wrapsModalita[3] : "");
            }catch (Exception e){
                // non c'è la modalità
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
    public String buildFinalReport(StudentInternship studentInternship) {

        String finalReportForm = docsPath + "/forms/form_final_report.pdf";
        String fileName = "precompiled_final_report_" + studentInternship.getInfo() + ".pdf";

        try {
            Path path = Paths.get(finalReportForm);
            byte[] fileContent = Files.readAllBytes(path);

            Student s = studentInternship.getStudent();
            Company c = studentInternship.getInternship().getCompany();
            Internship i = studentInternship.getInternship();

            PDDocument form = PDDocument.load(new ByteArrayInputStream(fileContent));
            PDDocumentCatalog docCatalog = form.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();
            PDField azienda = acroForm.getField("azienda");
            PDField codice_fiscale_azienda = acroForm.getField("codice_fiscale_azienda");
            PDField tirocinante_cognome = acroForm.getField("tirocinante_cognome");
            PDField tirocinante_nome = acroForm.getField("tirocinante_nome");
            PDField codice_identificativo_tirocinio = acroForm.getField("codice_identificativo_tirocinio");
            PDField sede_tirocinio = acroForm.getField("sede_tirocinio");
            PDField tirocinante_nome_cognome = acroForm.getField("tirocinante_nome_cognome");
            PDField ore = acroForm.getField("ore");

            azienda.setValue(c.getName());
            codice_fiscale_azienda.setValue(c.getVatNumber());
            tirocinante_cognome.setValue(s.getUser().getLastName());
            tirocinante_nome.setValue(s.getUser().getFirstName());
            codice_identificativo_tirocinio.setValue("id associazione studente-tirocinio: " + studentInternship.getId());
            sede_tirocinio.setValue(i.getAddress());
            tirocinante_nome_cognome.setValue(s.getUser().getFirstName() + " " + s.getUser().getLastName());
            ore.setValue(String.valueOf(i.getLength()));

            form.save(new File(docsPath, fileName));
            form.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public void clean(String fileName) throws IOException {
        Path path = Paths.get(docsPath + fileName);
        Files.deleteIfExists(path);
    }
}
