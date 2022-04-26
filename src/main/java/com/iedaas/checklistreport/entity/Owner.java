package com.iedaas.checklistreport.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
public class Owner {
    @Id
    @Column(name = "user_id")
    private int reporterId;

    @Column(name = "user_uid")
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID reporterUid;

    @Column(name = "full_name")
    private String reporterName;

    @Column(name = "image_url")
    private String reporterImage;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "owner")
    private Set<Report> reports;

    public Owner() {
    }

    public Owner(int reporterId, UUID reporterUid, String reporterName, String reporterImage, String role) {
        this.reporterId = reporterId;
        this.reporterUid = reporterUid;
        this.reporterName = reporterName;
        this.reporterImage = reporterImage;
        this.role = role;
    }

    public int getReporterId() {
        return reporterId;
    }

    public UUID getReporterUid() {
        return reporterUid;
    }

    public String getReporterName() {
        return reporterName;
    }

    public String getReporterImage() {
        return reporterImage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "reporterUid=" + reporterUid +
                ", reporterName='" + reporterName + '\'' +
                ", reporterImage='" + reporterImage + '\'' +
                '}';
    }
}
