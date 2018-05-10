package it.univaq.we.internshipTutor.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Company {
    private int id;
    private String name;
    private String address;
    private String fiscalCode;
    private String vatNumber;
    private String attorney;
    private String jurisdiction;

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
    @Column(name = "address", nullable = false, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "fiscal_code", nullable = true, length = 255)
    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    @Basic
    @Column(name = "vat_number", nullable = true, length = 255)
    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    @Basic
    @Column(name = "attorney", nullable = false, length = 255)
    public String getAttorney() {
        return attorney;
    }

    public void setAttorney(String attorney) {
        this.attorney = attorney;
    }

    @Basic
    @Column(name = "jurisdiction", nullable = false, length = 255)
    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id &&
                Objects.equals(name, company.name) &&
                Objects.equals(address, company.address) &&
                Objects.equals(fiscalCode, company.fiscalCode) &&
                Objects.equals(vatNumber, company.vatNumber) &&
                Objects.equals(attorney, company.attorney) &&
                Objects.equals(jurisdiction, company.jurisdiction);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, address, fiscalCode, vatNumber, attorney, jurisdiction);
    }
}
