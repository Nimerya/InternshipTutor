package it.univaq.we.internshipTutor.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "degree")
public class Degree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Transient
    private UUID uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    @NotNull
    private Department department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "degree")
    private List<Student> students;

    @Column(name = "name", nullable = false, length = 255)
    @NotEmpty
    private String name;

    @Column(name = "class", nullable = false, length = 255)
    @NotEmpty
    private String clazz;

    public Degree() {}

    public Degree(UUID uuid) {
        setUuid(uuid);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
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
        if (!(obj instanceof Degree))
            return false;
        return getUuid().equals(((Degree) obj).getUuid());
    }

    @Override
    public int hashCode() {
        return getUuid().hashCode();
    }

    //Return info's about degree
    public String getInfo(){ return this.getName(); }
}
