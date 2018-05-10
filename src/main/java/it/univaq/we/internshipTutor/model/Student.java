package it.univaq.we.internshipTutor.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "degree_id", nullable = false)
    private Degree degree;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<StudentInternship> studentInternships;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "student")
    private User user;

    @Column(name = "birthday", nullable = false)
    private Timestamp birthday;

    @Column(name = "matriculation_number", nullable = false, length = 255)
    private String matriculationNumber;

    @Column(name = "birthplace_city", nullable = false, length = 255)
    private String birthplaceCity;

    @Column(name = "birthplace_province", nullable = false, length = 255)
    private String birthplaceProvince;

    @Column(name = "birthplace_state", nullable = false, length = 255)
    private String birthplaceState;

    @Column(name = "residence_adress", nullable = false, length = 255)
    private String residenceAdress;

    @Column(name = "residence_city", nullable = false, length = 255)
    private String residenceCity;

    @Column(name = "residence_province", nullable = false, length = 255)
    private String residenceProvince;

    @Column(name = "residence_state", nullable = false, length = 255)
    private String residenceState;

    @Column(name = "fiscal_code", nullable = false, length = 255)
    private String fiscalCode;

    @Column(name = "handicap", nullable = false)
    private byte handicap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
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

    public String getResidenceAdress() {
        return residenceAdress;
    }

    public void setResidenceAdress(String residenceAdress) {
        this.residenceAdress = residenceAdress;
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

    public byte getHandicap() {
        return handicap;
    }

    public void setHandicap(byte handicap) {
        this.handicap = handicap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
