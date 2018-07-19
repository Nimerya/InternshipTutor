package it.univaq.we.internshipTutor.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "student_internship")
public class StudentInternship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Transient
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    @NotNull(message = "this field is mandatory")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "internship_id", nullable = false)
    @NotNull(message = "this field is mandatory")
    private Internship internship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    @NotNull(message = "this field is mandatory")
    private Professor professor;

    @Column(name = "cfu", nullable = false)
    @NotNull(message = "this field is mandatory")
    @Min(1)
    private Integer cfu;

    @Column(name = "review", nullable = true)
    @Min(1)
    @Max(5)
    private Integer review;

    @Column(name = "accepted", nullable = true)
    private Boolean accepted;

    @Column(name = "completed", nullable = true)
    private Boolean completed;

    public StudentInternship() {}

    public StudentInternship(UUID uuid) { setUuid(uuid); }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Internship getInternship() {
        return internship;
    }

    public void setInternship(Internship internship) {
        this.internship = internship;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Integer getCfu() {
        return cfu;
    }

    public void setCfu(Integer cfu) {
        this.cfu = cfu;
    }

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof StudentInternship))
            return false;
        return getUuid().equals(((StudentInternship) obj).getUuid());
    }

    @Override
    public int hashCode() {
        if (this.getUuid() == null){
            this.setUuid(UUID.randomUUID());
        }
        return getUuid().hashCode();
    }

    //Return info's about sudent internship
    public String getInfo(){
        return this.getStudent().getMatriculationNumber() +" - "+
                this.getInternship().getTitle() +" - "+
                this.getInternship().getCompany().getName(); }
}
