package it.univaq.we.internshipTutor.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "company")
public class Company implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id; //persistence id

    @Transient // ignore by persistence
    private UUID uuid; // internal id

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<Internship> internships;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "company")
    private User user;

    @Column(name = "name", nullable = false, length = 255)
    @NotEmpty
    private String name;

    @Column(name = "address", nullable = false, length = 255)
    @NotEmpty
    private String address;

    @Column(name = "fiscal_code", nullable = false, length = 255)
    @NotEmpty
    private String fiscalCode;

    @Column(name = "vat_number", nullable = false, length = 255)
    @NotEmpty
    private String vatNumber;

    @Column(name = "attorney", nullable = false, length = 255)
    @NotEmpty
    private String attorney;

    @Column(name = "jurisdiction", nullable = false, length = 255)
    @NotEmpty
    private String jurisdiction;

    @Column(name = "active", nullable = true)
    private Boolean active;

    // constructor used by persistence
    public Company() {}

    // internal contrusctor
    public Company(UUID uuid) {
        setUuid(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Internship> getInternships() {
        return internships;
    }

    public void setInternships(List<Internship> internships) {
        this.internships = internships;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getAttorney() {
        return attorney;
    }

    public void setAttorney(String attorney) {
        this.attorney = attorney;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    // https://stackoverflow.com/questions/5031614/the-jpa-hashcode-equals-dilemma
    // note that I don't use direct field access inside my entity classes and
    // call getters instead. That's because Persistence provider (PP) might
    // want to load entity data lazily. And I don't use
    //    this.getClass() == other.getClass()
    // for the same reason. In order to support laziness PP might need to wrap
    // my entity object in some kind of proxy, i.e. subclassing it.
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Company))
            return false;
        return getUuid().equals(((Company) obj).getUuid());
    }

    // equals() and hashCode() rely on non-changing data only. Thus we
    // guarantee that no matter how field values are changed we won't
    // lose our entity in hash-based Sets.
    @Override
    public int hashCode() {
        if (this.getUuid() == null){
            this.setUuid(UUID.randomUUID());
        }
        return getUuid().hashCode();
    }

    //Return info's about degree
    public String getInfo(){ return this.getName(); }
}
