package it.univaq.we.internshipTutor.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Student {
    private int id;
    private Timestamp birthday;
    private String matriculationNumber;
    private String birthplaceCity;
    private String birthplaceProvince;
    private String birthplaceState;
    private String residenceAdress;
    private String residenceCity;
    private String residenceProvince;
    private String residenceState;
    private String fiscalCode;
    private byte handicap;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "birthday", nullable = false)
    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "matriculation_number", nullable = false, length = 255)
    public String getMatriculationNumber() {
        return matriculationNumber;
    }

    public void setMatriculationNumber(String matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }

    @Basic
    @Column(name = "birthplace_city", nullable = false, length = 255)
    public String getBirthplaceCity() {
        return birthplaceCity;
    }

    public void setBirthplaceCity(String birthplaceCity) {
        this.birthplaceCity = birthplaceCity;
    }

    @Basic
    @Column(name = "birthplace_province", nullable = false, length = 255)
    public String getBirthplaceProvince() {
        return birthplaceProvince;
    }

    public void setBirthplaceProvince(String birthplaceProvince) {
        this.birthplaceProvince = birthplaceProvince;
    }

    @Basic
    @Column(name = "birthplace_state", nullable = false, length = 255)
    public String getBirthplaceState() {
        return birthplaceState;
    }

    public void setBirthplaceState(String birthplaceState) {
        this.birthplaceState = birthplaceState;
    }

    @Basic
    @Column(name = "residence_adress", nullable = false, length = 255)
    public String getResidenceAdress() {
        return residenceAdress;
    }

    public void setResidenceAdress(String residenceAdress) {
        this.residenceAdress = residenceAdress;
    }

    @Basic
    @Column(name = "residence_city", nullable = false, length = 255)
    public String getResidenceCity() {
        return residenceCity;
    }

    public void setResidenceCity(String residenceCity) {
        this.residenceCity = residenceCity;
    }

    @Basic
    @Column(name = "residence_province", nullable = false, length = 255)
    public String getResidenceProvince() {
        return residenceProvince;
    }

    public void setResidenceProvince(String residenceProvince) {
        this.residenceProvince = residenceProvince;
    }

    @Basic
    @Column(name = "residence_state", nullable = false, length = 255)
    public String getResidenceState() {
        return residenceState;
    }

    public void setResidenceState(String residenceState) {
        this.residenceState = residenceState;
    }

    @Basic
    @Column(name = "fiscal_code", nullable = false, length = 255)
    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    @Basic
    @Column(name = "handicap", nullable = false)
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
        return id == student.id &&
                handicap == student.handicap &&
                Objects.equals(birthday, student.birthday) &&
                Objects.equals(matriculationNumber, student.matriculationNumber) &&
                Objects.equals(birthplaceCity, student.birthplaceCity) &&
                Objects.equals(birthplaceProvince, student.birthplaceProvince) &&
                Objects.equals(birthplaceState, student.birthplaceState) &&
                Objects.equals(residenceAdress, student.residenceAdress) &&
                Objects.equals(residenceCity, student.residenceCity) &&
                Objects.equals(residenceProvince, student.residenceProvince) &&
                Objects.equals(residenceState, student.residenceState) &&
                Objects.equals(fiscalCode, student.fiscalCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, birthday, matriculationNumber, birthplaceCity, birthplaceProvince, birthplaceState, residenceAdress, residenceCity, residenceProvince, residenceState, fiscalCode, handicap);
    }
}
