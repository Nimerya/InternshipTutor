package it.univaq.we.internshipTutor.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "internship")
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Transient
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "internship")
    private List<StudentInternship> studentInternships;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "city", nullable = false, length = 255)
    private String city;

    @Column(name = "province", nullable = false, length = 255)
    private String province;

    @Column(name = "state", nullable = false, length = 255)
    private String state;

    @Column(name = "remote", nullable = true)
    private Byte remote;

    @Column(name = "schedule", nullable = true, length = -1)
    private String schedule;

    @Column(name = "length", nullable = false)
    private int length;

    @Column(name = "mode_it-IT", nullable = false, length = 255)
    private String modeItIt;

    @Column(name = "mode_en-GB", nullable = true, length = 255)
    private String modeEnGb;

    @Column(name = "goals_it-IT", nullable = true, length = -1)
    private String goalsItIt;

    @Column(name = "goals_en-GB", nullable = true, length = -1)
    private String goalsEnGb;

    @Column(name = "refund", nullable = true)
    private Byte refund;

    @Column(name = "details_it-IT", nullable = true, length = -1)
    private String detailsItIt;

    @Column(name = "details_en-GB", nullable = true, length = -1)
    private String detailsEnGb;

    @Column(name = "facilitations", nullable = true, length = -1)
    private String facilitations;

    @Column(name = "active", nullable = true)
    private Byte active;

    public Internship() {}

    public Internship(UUID uuid) { setUuid(uuid); }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<StudentInternship> getStudentInternships() {
        return studentInternships;
    }

    public void setStudentInternships(List<StudentInternship> studentInternships) {
        this.studentInternships = studentInternships;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Byte getRemote() {
        return remote;
    }

    public void setRemote(Byte remote) {
        this.remote = remote;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getModeItIt() {
        return modeItIt;
    }

    public void setModeItIt(String modeItIt) {
        this.modeItIt = modeItIt;
    }

    public String getModeEnGb() {
        return modeEnGb;
    }

    public void setModeEnGb(String modeEnGb) {
        this.modeEnGb = modeEnGb;
    }

    public String getGoalsItIt() {
        return goalsItIt;
    }

    public void setGoalsItIt(String goalsItIt) {
        this.goalsItIt = goalsItIt;
    }

    public String getGoalsEnGb() {
        return goalsEnGb;
    }

    public void setGoalsEnGb(String goalsEnGb) {
        this.goalsEnGb = goalsEnGb;
    }

    public Byte getRefund() {
        return refund;
    }

    public void setRefund(Byte refund) {
        this.refund = refund;
    }

    public String getDetailsItIt() {
        return detailsItIt;
    }

    public void setDetailsItIt(String detailsItIt) {
        this.detailsItIt = detailsItIt;
    }

    public String getDetailsEnGb() {
        return detailsEnGb;
    }

    public void setDetailsEnGb(String detailsEnGb) {
        this.detailsEnGb = detailsEnGb;
    }

    public String getFacilitations() {
        return facilitations;
    }

    public void setFacilitations(String facilitations) {
        this.facilitations = facilitations;
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
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
        if (!(obj instanceof Internship))
            return false;
        return getUuid().equals(((Internship) obj).getUuid());
    }

    @Override
    public int hashCode() {
        return getUuid().hashCode();
    }
}
