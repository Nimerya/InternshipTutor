package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.*;
import it.univaq.we.internshipTutor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static it.univaq.we.internshipTutor.model.Popup.WAR_MSG_EN;
import static it.univaq.we.internshipTutor.model.Popup.WAR_MSG_EN_DEL;
import static it.univaq.we.internshipTutor.model.Popup.WAR_MSG_EN_SAVE;

@Controller
public class StudentInternshipController {

    @Autowired
    StudentInternshipService studentinternshipService;

    @Autowired
    StudentService studentService;

    @Autowired
    InternshipService internshipService;

    @Autowired
    ProfessorService professorService;

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    PdfBuilderService pdfBuilderService;


    @RequestMapping(value = {"/admin/create/studentinternship"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("studentinternship") StudentInternship studentinternship, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.studentinternship", result);
            redirectAttributes.addFlashAttribute("studentinternship", studentinternship);
            return "redirect:/admin/create/studentinternship";
        }
        if ((studentinternship.getAccepted() && studentinternship.getRejected()) ||
                (studentinternship.getRejected() && studentinternship.getCompleted()) ||
                (!studentinternship.getAccepted() && studentinternship.getCompleted())) {

            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "There are inconsistencies with the options."));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.studentinternship", result);
            redirectAttributes.addFlashAttribute("studentinternship", studentinternship);
            return "redirect:/admin/create/studentinternship";
        }

        try {
            // else perform the insertion
            studentinternshipService.save(studentinternship);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/create/studentinternship";
        }


        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/admin/create/studentinternship";
    }

    @RequestMapping(value = {"/admin/update/studentinternship"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("studentinternship") StudentInternship studentinternship, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.studentinternship", result);
            redirectAttributes.addFlashAttribute("studentinternship", studentinternship);
            return "redirect:/admin/update/studentinternship/" + studentinternship.getId();
        }

        if ((studentinternship.getAccepted() && studentinternship.getRejected()) ||
                (studentinternship.getRejected() && studentinternship.getCompleted()) ||
                (!studentinternship.getAccepted() && studentinternship.getCompleted())) {

            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "There are inconsistencies with the options."));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.studentinternship", result);
            redirectAttributes.addFlashAttribute("studentinternship", studentinternship);
            return "redirect:/admin/update/studentinternship" + studentinternship.getId();
        }

        try {
            // else perform the insertion
            String oldTrainingProject = studentinternshipService.findStudentInternshipById(studentinternship.getId()).getTrainingProject();
            studentinternship.setTrainingProject(oldTrainingProject);
            studentinternshipService.save(studentinternship);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/update/studentinternship/" + studentinternship.getId();
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/admin/update/studentinternship/" + studentinternship.getId();
    }

    @RequestMapping(value = {"/admin/delete/studentinternship/{id}"}, method = RequestMethod.POST)
    public String doDelete(ModelMap model, @PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {

        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/admin/update/studentinternship/" + id;
        }

        try {
            studentinternshipService.deleteStudentInternshipById(id);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_DEL));
            redirectAttributes.addFlashAttribute("studentinternship", studentinternshipService.findStudentInternshipById(id));
            return "redirect:/admin/update/studentinternship/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/admin/create/studentinternship";
    }

    @RequestMapping(value = {"/company/accept/studentinternship"}, method = RequestMethod.POST)
    public String doAcceptByCompany(RedirectAttributes redirectAttributes,
                                    @RequestParam("id") Long id,
                                    @RequestParam("trainingProjectFile") MultipartFile trainingProjectFile) {

        //TODO check that the company is accepting a student into one of its own internships
        //TODO check that this studentinternship is active

        StudentInternship studentInternship = studentinternshipService.findStudentInternshipById(id);
        Long internshipId = studentInternship.getInternship().getId();

        try {
            studentInternship.setTrainingProject(fileUploadService.uploadPdf(trainingProjectFile, "training_project_" + studentInternship.getInfo() + '_'));
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! Check the file you have uploaded"));
            return "redirect:/company/accept/studentinternship/" + id;
        }

        try {
            studentinternshipService.acceptStudentInternship(id);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/company/accept/studentinternship/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/company/update/internship/" + internshipId;
    }

    @RequestMapping(value = {"/company/accept/studentinternship/{id}"}, method = RequestMethod.GET)
    public String renderAcceptByCompany(ModelMap model, @PathVariable(value = "id") Long id, Pageable pageable, RedirectAttributes redirectAttributes) {

        //TODO security checks (only the authorized company can see this form)
        //TODO do not allow to access this page if the studentinternship has already been accepted

        StudentInternship studentinternship = studentinternshipService.findStudentInternshipById(id);
        model.addAttribute("studentinternship", studentinternship);

        Student student = studentinternship.getStudent();
        model.addAttribute("student", student);

        Internship intership = studentinternship.getInternship();
        model.addAttribute("internship", intership);

        Professor professor = studentinternship.getProfessor();
        model.addAttribute("professor", professor);

        Page<StudentInternship> studentinternships = studentinternshipService.findCandidatesByInternship(pageable, studentinternship.getInternship());
        PageWrapper<StudentInternship> page = new PageWrapper<>(studentinternships, "/company/accept/studentinternship/" + id);
        model.addAttribute("studentinternships", page.getContent());
        model.addAttribute("page", page);

        return "company_accept_studentinternship";
    }

    @RequestMapping(value = {"/company/reject/studentinternship/{id}"}, method = RequestMethod.GET)
    public String doRejectByCompany(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {

        //TODO check that the company is accepting a student into one of its own internships
        //TODO check that this studentinternship is active

        Long internshipId = studentinternshipService.findStudentInternshipById(id).getInternship().getId();

        try {
            studentinternshipService.rejectStudentInternship(id);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/company/update/internship/" + internshipId;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/company/update/internship/" + internshipId;
    }


    @RequestMapping(value = {"/student/create/review/{id}"}, method = RequestMethod.GET)
    public String renderCreateReviewByStudent(ModelMap model, @PathVariable(value = "id") Long id,
                                              RedirectAttributes redirectAttributes) {

        //TODO security checks

        StudentInternship studentinternship = studentinternshipService.findStudentInternshipById(id);
        model.addAttribute("studentinternship", studentinternship);

        Student student = studentinternship.getStudent();
        model.addAttribute("student", student);

        Internship intership = studentinternship.getInternship();
        model.addAttribute("internship", intership);

        Professor professor = studentinternship.getProfessor();
        model.addAttribute("professor", professor);

        return "review_by_student";
    }

    @RequestMapping(value = {"/student/create/review"}, method = RequestMethod.POST)
    public String doCreateReviewByStudent(RedirectAttributes redirectAttributes,
                                    @RequestParam("id") Long id, @RequestParam("review") Integer review) {

        //TODO security checks

        StudentInternship studentInternship = studentinternshipService.findStudentInternshipById(id);

        if (review < 1 || review > 5){
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Review value not valid."));
            return "redirect:/student/create/review/" + id;
        }

        try {
            studentInternship.setReview(review);
            studentinternshipService.save(studentInternship);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/student/create/review/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/student/dashboard";
    }

    @RequestMapping(value = {"/company/create/finalreport/{id}"}, method = RequestMethod.GET)
    public String renderCreateFinalReportByCompany(ModelMap model, @PathVariable(value = "id") Long id,
                                        RedirectAttributes redirectAttributes) {

        //TODO security checks (only the authorized company can see this form)
        //TODO do not allow to access this page if the studentinternship is not completed

        StudentInternship studentinternship = studentinternshipService.findStudentInternshipById(id);
        model.addAttribute("studentinternship", studentinternship);

        Student student = studentinternship.getStudent();
        model.addAttribute("student", student);

        Internship intership = studentinternship.getInternship();
        model.addAttribute("internship", intership);

        Professor professor = studentinternship.getProfessor();
        model.addAttribute("professor", professor);

        return "final_report_create_by_company";
    }

    @RequestMapping(value = {"/company/create/finalreport"}, method = RequestMethod.POST)
    public String doCreateFinalReportByCompany(RedirectAttributes redirectAttributes,
                                    @RequestParam("id") Long id,
                                    @RequestParam("finalReportFile") MultipartFile finalReportFile) {

        //TODO security/integrity checks

        StudentInternship studentInternship = studentinternshipService.findStudentInternshipById(id);

        try {
            studentInternship.setFinalReport(fileUploadService.uploadPdf(finalReportFile, "final_report_" + studentInternship.getInfo() + '_'));
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! Check the file you have uploaded"));
            return "redirect:/company/create/finalreport/" + id;
        }

        try {
            studentinternshipService.save(studentInternship);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/company/dashboard";
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/company/dashboard";
    }


    @RequestMapping(value = {"/admin/create/studentinternship"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model, Pageable pageable) {

        if (!model.containsAttribute("studentinternship")) {
            model.addAttribute("studentinternship", new StudentInternship(UUID.randomUUID()));
        }

        Page<StudentInternship> studentinternships = studentinternshipService.findAll(pageable);
        PageWrapper<StudentInternship> page = new PageWrapper<>(studentinternships, "/admin/create/studentinternship");
        model.addAttribute("studentinternships", page.getContent());
        model.addAttribute("page", page);

        List<Professor> professors = professorService.findAll();
        model.addAttribute("professors", professors);

        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        List<Internship> internships = internshipService.findActiveInternships();
        model.addAttribute("internships", internships);

        return "studentinternship_create";
    }

    @RequestMapping(value = {"/admin/update/studentinternship/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, Pageable pageable, @PathVariable(value = "id") Long id) {


        if (!model.containsAttribute("studentinternship")) {
            StudentInternship d = studentinternshipService.findStudentInternshipById(id);
            model.addAttribute("studentinternship", d);
        }

        Page<StudentInternship> studentinternships = studentinternshipService.findAll(pageable);
        PageWrapper<StudentInternship> page = new PageWrapper<>(studentinternships, "/admin/update/studentinternship/" + id);
        model.addAttribute("studentinternships", page.getContent());
        model.addAttribute("page", page);

        List<Professor> professors = professorService.findAll();
        model.addAttribute("professors", professors);

        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        List<Internship> internships = internshipService.findActiveInternships();
        model.addAttribute("internships", internships);

        return "studentinternship_update";
    }

    @RequestMapping(value = {"/admin/report/studentinternships"}, method = RequestMethod.GET)
    public String renderReport(ModelMap model, Pageable pageable) {

        Page<StudentInternship> studentinternships = studentinternshipService.findAll(pageable);
        PageWrapper<StudentInternship> page = new PageWrapper<>(studentinternships, "/admin/report/studentinternships");
        model.addAttribute("collection", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("nameS", "studentinternship");
        model.addAttribute("nameP", "Student - Internships");
        model.addAttribute("fileType", "Training Project");

        return "report_studentinternships";
    }

}
