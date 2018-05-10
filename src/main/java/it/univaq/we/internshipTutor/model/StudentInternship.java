package it.univaq.we.internshipTutor.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_internship", schema = "internship_tutor", catalog = "")
@IdClass(StudentInternshipPK.class)
public class StudentInternship {
    private int studentId;
    private int internshipId;
    private Integer cfu;
    private int professorId;
    private Integer review;
    private Byte accepted;

    @Id
    @Column(name = "student_id", nullable = false)
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Id
    @Column(name = "internship_id", nullable = false)
    public int getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(int internshipId) {
        this.internshipId = internshipId;
    }

    @Basic
    @Column(name = "cfu", nullable = true)
    public Integer getCfu() {
        return cfu;
    }

    public void setCfu(Integer cfu) {
        this.cfu = cfu;
    }

    @Id
    @Column(name = "professor_id", nullable = false)
    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    @Basic
    @Column(name = "review", nullable = true)
    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    @Basic
    @Column(name = "accepted", nullable = true)
    public Byte getAccepted() {
        return accepted;
    }

    public void setAccepted(Byte accepted) {
        this.accepted = accepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentInternship that = (StudentInternship) o;
        return studentId == that.studentId &&
                internshipId == that.internshipId &&
                professorId == that.professorId &&
                Objects.equals(cfu, that.cfu) &&
                Objects.equals(review, that.review) &&
                Objects.equals(accepted, that.accepted);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, internshipId, cfu, professorId, review, accepted);
    }
}
