package it.univaq.we.internshipTutor.model;

public interface IProfessorInternshipCountProjection {
        Long getId();
        String getDepartment();
        String getFirstName();
        String getLastName();
        String getEmail();
        Integer getCount();

}
