package it.univaq.we.internshipTutor.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_internship", schema = "internship_tutor", catalog = "")
public class StudentInternship {
    private int id;
    private Integer cfu;
    private Integer review;
    private Byte accepted;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cfu", nullable = true)
    public Integer getCfu() {
        return cfu;
    }

    public void setCfu(Integer cfu) {
        this.cfu = cfu;
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
        return id == that.id &&
                Objects.equals(cfu, that.cfu) &&
                Objects.equals(review, that.review) &&
                Objects.equals(accepted, that.accepted);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, cfu, review, accepted);
    }
}
