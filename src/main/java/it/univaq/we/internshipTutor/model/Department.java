package it.univaq.we.internshipTutor.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="department")
    List<Degree> degrees;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="department")
    List<Professor> professors;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description_it-IT", nullable = true, length = 255)
    private String descriptionItIt;

    @Column(name = "description_en-GB", nullable = true, length = 255)
    private String descriptionEnGb;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<Degree> degrees) {
        this.degrees = degrees;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionItIt() {
        return descriptionItIt;
    }

    public void setDescriptionItIt(String descriptionItIt) {
        this.descriptionItIt = descriptionItIt;
    }

    public String getDescriptionEnGb() {
        return descriptionEnGb;
    }

    public void setDescriptionEnGb(String descriptionEnGb) {
        this.descriptionEnGb = descriptionEnGb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
