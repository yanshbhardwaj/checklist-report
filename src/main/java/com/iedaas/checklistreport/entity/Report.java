package com.iedaas.checklistreport.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private int reportId;

    @Column(name = "report_uid")
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID reportUid=UUID.randomUUID();

    @Column(name = "entity_uid")
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID entityUid;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "entity_owner_uid")
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID entityOwnerUid;
    @Column(name = "report_title")
    private String reportTitle;

    @Column(name = "report_description")
    private String reportDescription;

    @Column(name = "report_action")
    private String reportAction;

    @ManyToOne
    @JoinColumn(name = "reporter_id", referencedColumnName = "user_id")
    private Owner owner;

    public Report() {
    }

    public Report(UUID reportUid, UUID entityUid, String entityType, String reportTitle,
                  String reportDescription)
    {
        this.reportUid = reportUid;
        this.entityUid = entityUid;
        this.entityType = entityType;
        this.reportTitle = reportTitle;
        this.reportDescription = reportDescription;
    }

    public int getReportId() {
        return reportId;
    }

    public UUID getReportUid() {
        return reportUid;
    }

    public UUID getEntityUid() {
        return entityUid;
    }

    public void setEntityUid(UUID entityUid) {
        this.entityUid = entityUid;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public Owner getReporter() {
        return owner;
    }

    public void setReporter(Owner owner) {
        this.owner = owner;
    }

    public UUID getEntityOwnerUid() {
        return entityOwnerUid;
    }

    public void setEntityOwnerUid(UUID entityOwnerUid) {
        this.entityOwnerUid = entityOwnerUid;
    }

    public String getReportAction() {
        return reportAction;
    }

    public void setReportAction(String reportAction) {
        this.reportAction = reportAction;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportUid=" + reportUid +
                ", entityUid=" + entityUid +
                ", entityType='" + entityType + '\'' +
                ", entityOwnerUid=" + entityOwnerUid +
                ", reportTitle='" + reportTitle + '\'' +
                ", reportDescription='" + reportDescription + '\'' +
                ", owner=" + owner +
                '}';
    }
}
