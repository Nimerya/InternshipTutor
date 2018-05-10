package it.univaq.we.internshipTutor.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Internship {
    private int id;
    private String title;
    private String address;
    private String city;
    private String province;
    private String state;
    private Byte remote;
    private String schedule;
    private int length;
    private String modeItIt;
    private String modeEnGb;
    private String goalsItIt;
    private String goalsEnGb;
    private Byte refund;
    private String detailsItIt;
    private String detailsEnGb;
    private String facilitations;
    private Byte active;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "city", nullable = false, length = 255)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "province", nullable = false, length = 255)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "state", nullable = false, length = 255)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "remote", nullable = true)
    public Byte getRemote() {
        return remote;
    }

    public void setRemote(Byte remote) {
        this.remote = remote;
    }

    @Basic
    @Column(name = "schedule", nullable = true, length = -1)
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @Basic
    @Column(name = "length", nullable = false)
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Basic
    @Column(name = "mode_it-IT", nullable = false, length = 255)
    public String getModeItIt() {
        return modeItIt;
    }

    public void setModeItIt(String modeItIt) {
        this.modeItIt = modeItIt;
    }

    @Basic
    @Column(name = "mode_en-GB", nullable = true, length = 255)
    public String getModeEnGb() {
        return modeEnGb;
    }

    public void setModeEnGb(String modeEnGb) {
        this.modeEnGb = modeEnGb;
    }

    @Basic
    @Column(name = "goals_it-IT", nullable = true, length = -1)
    public String getGoalsItIt() {
        return goalsItIt;
    }

    public void setGoalsItIt(String goalsItIt) {
        this.goalsItIt = goalsItIt;
    }

    @Basic
    @Column(name = "goals_en-GB", nullable = true, length = 45)
    public String getGoalsEnGb() {
        return goalsEnGb;
    }

    public void setGoalsEnGb(String goalsEnGb) {
        this.goalsEnGb = goalsEnGb;
    }

    @Basic
    @Column(name = "refund", nullable = true)
    public Byte getRefund() {
        return refund;
    }

    public void setRefund(Byte refund) {
        this.refund = refund;
    }

    @Basic
    @Column(name = "details_it-IT", nullable = true, length = -1)
    public String getDetailsItIt() {
        return detailsItIt;
    }

    public void setDetailsItIt(String detailsItIt) {
        this.detailsItIt = detailsItIt;
    }

    @Basic
    @Column(name = "details_en-GB", nullable = true, length = -1)
    public String getDetailsEnGb() {
        return detailsEnGb;
    }

    public void setDetailsEnGb(String detailsEnGb) {
        this.detailsEnGb = detailsEnGb;
    }

    @Basic
    @Column(name = "facilitations", nullable = true, length = -1)
    public String getFacilitations() {
        return facilitations;
    }

    public void setFacilitations(String facilitations) {
        this.facilitations = facilitations;
    }

    @Basic
    @Column(name = "active", nullable = true)
    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Internship that = (Internship) o;
        return id == that.id &&
                length == that.length &&
                Objects.equals(title, that.title) &&
                Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) &&
                Objects.equals(province, that.province) &&
                Objects.equals(state, that.state) &&
                Objects.equals(remote, that.remote) &&
                Objects.equals(schedule, that.schedule) &&
                Objects.equals(modeItIt, that.modeItIt) &&
                Objects.equals(modeEnGb, that.modeEnGb) &&
                Objects.equals(goalsItIt, that.goalsItIt) &&
                Objects.equals(goalsEnGb, that.goalsEnGb) &&
                Objects.equals(refund, that.refund) &&
                Objects.equals(detailsItIt, that.detailsItIt) &&
                Objects.equals(detailsEnGb, that.detailsEnGb) &&
                Objects.equals(facilitations, that.facilitations) &&
                Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, address, city, province, state, remote, schedule, length, modeItIt, modeEnGb, goalsItIt, goalsEnGb, refund, detailsItIt, detailsEnGb, facilitations, active);
    }
}
