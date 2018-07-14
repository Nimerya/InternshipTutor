package it.univaq.we.internshipTutor.model;

import javax.persistence.*;
import java.sql.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Transient
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "degree_id", nullable = false)
    @NotNull
    private Degree degree;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<StudentInternship> studentInternships;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "student")
    private User user;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "matriculation_number", nullable = false, length = 255)
    @NotEmpty
    private String matriculationNumber;

    @Column(name = "birthplace_city", nullable = false, length = 255)
    @NotEmpty
    private String birthplaceCity;

    @Column(name = "birthplace_province", nullable = false, length = 255)
    @NotEmpty
    private String birthplaceProvince;

    @Column(name = "birthplace_state", nullable = false, length = 255)
    @NotEmpty
    private String birthplaceState;

    @Column(name = "residence_address", nullable = false, length = 255)
    private String residenceAddress;

    @Column(name = "residence_city", nullable = false, length = 255)
    @NotEmpty
    private String residenceCity;

    @Column(name = "residence_province", nullable = false, length = 255)
    @NotEmpty
    private String residenceProvince;

    @Column(name = "residence_state", nullable = false, length = 255)
    @NotEmpty
    private String residenceState;

    @Column(name = "fiscal_code", nullable = false, length = 255)
    @NotEmpty
    private String fiscalCode;

    @Column(name = "handicap", nullable = false)
    private Boolean handicap;

    public Student() {}

    public Student(UUID uuid) { setUuid(uuid); }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public List<StudentInternship> getStudentInternships() {
        return studentInternships;
    }

    public void setStudentInternships(List<StudentInternship> studentInternships) {
        this.studentInternships = studentInternships;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMatriculationNumber() {
        return matriculationNumber;
    }

    public void setMatriculationNumber(String matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }

    public String getBirthplaceCity() {
        return birthplaceCity;
    }

    public void setBirthplaceCity(String birthplaceCity) {
        this.birthplaceCity = birthplaceCity;
    }

    public String getBirthplaceProvince() {
        return birthplaceProvince;
    }

    public void setBirthplaceProvince(String birthplaceProvince) {
        this.birthplaceProvince = birthplaceProvince;
    }

    public String getBirthplaceState() {
        return birthplaceState;
    }

    public void setBirthplaceState(String birthplaceState) {
        this.birthplaceState = birthplaceState;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getResidenceCity() {
        return residenceCity;
    }

    public void setResidenceCity(String residenceCity) {
        this.residenceCity = residenceCity;
    }

    public String getResidenceProvince() {
        return residenceProvince;
    }

    public void setResidenceProvince(String residenceProvince) {
        this.residenceProvince = residenceProvince;
    }

    public String getResidenceState() {
        return residenceState;
    }

    public void setResidenceState(String residenceState) {
        this.residenceState = residenceState;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public Boolean getHandicap() {
        return handicap;
    }

    public void setHandicap(Boolean handicap) {
        this.handicap = handicap;
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
        if (!(obj instanceof Student))
            return false;
        return getUuid().equals(((Student) obj).getUuid());
    }

    @Override
    public int hashCode() {
        return getUuid().hashCode();
    }

    //Return info's about student
    public String getInfo(){ return this.getMatriculationNumber(); }
}
