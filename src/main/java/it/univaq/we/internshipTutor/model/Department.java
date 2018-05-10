package it.univaq.we.internshipTutor.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Department {
    private int id;
    private String name;
    private String descriptionItIt;
    private String descriptionEnGb;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description_it-IT", nullable = true, length = 255)
    public String getDescriptionItIt() {
        return descriptionItIt;
    }

    public void setDescriptionItIt(String descriptionItIt) {
        this.descriptionItIt = descriptionItIt;
    }

    @Basic
    @Column(name = "description_en-GB", nullable = true, length = 255)
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
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(descriptionItIt, that.descriptionItIt) &&
                Objects.equals(descriptionEnGb, that.descriptionEnGb);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, descriptionItIt, descriptionEnGb);
    }
}
