package it.univaq.we.internshipTutor.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Transient
    private UUID uuid;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="department")
    List<Degree> degrees;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="department")
    List<Professor> professors;

    @Column(name = "name", nullable = false, length = 255)
    @NotEmpty
    private String name;

    @Column(name = "description_it_it", nullable = true, length = 255)
    private String descriptionItIt;

    @Column(name = "description_en_gb", nullable = true, length = 255)
    @NotEmpty
    private String descriptionEnGb;

    public Department() {}

    public Department(UUID uuid) { setUuid(uuid); }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (!(obj instanceof Department))
            return false;
        return getUuid().equals(((Department) obj).getUuid());
    }

    @Override
    public int hashCode() {
        return getUuid().hashCode();
    }

    //Return info's about department
    public String getInfo(){ return this.getName(); }
}
