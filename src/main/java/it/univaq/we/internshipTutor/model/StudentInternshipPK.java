package it.univaq.we.internshipTutor.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StudentInternshipPK implements Serializable {
    private int studentId;
    private int internshipId;
    private int professorId;

    @Column(name = "student_id", nullable = false)
    @Id
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Column(name = "internship_id", nullable = false)
    @Id
    public int getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(int internshipId) {
        this.internshipId = internshipId;
    }

    @Column(name = "professor_id", nullable = false)
    @Id
    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentInternshipPK that = (StudentInternshipPK) o;
        return studentId == that.studentId &&
                internshipId == that.internshipId &&
                professorId == that.professorId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, internshipId, professorId);
    }
}
