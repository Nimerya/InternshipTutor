package it.univaq.we.internshipTutor.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<Internship> internships;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "fiscal_code", nullable = true, length = 255)
    private String fiscalCode;

    @Column(name = "vat_number", nullable = true, length = 255)
    private String vatNumber;

    @Column(name = "attorney", nullable = false, length = 255)
    private String attorney;

    @Column(name = "jurisdiction", nullable = false, length = 255)
    private String jurisdiction;


}
