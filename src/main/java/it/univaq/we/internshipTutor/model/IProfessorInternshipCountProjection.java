package it.univaq.we.internshipTutor.model;

public interface IProfessorInternshipCountProjection {
        Long getId();
        Integer getCount();
        String getFirstName();
        String getLastName();
        Department getDepartment();
}
