package com.iedaas.checklistreport.dto;

import java.util.UUID;

public class ReportDTO {

    private UUID reportUid;
    private UUID entityUid;
    private String entityType;
    private String reportTitle;
    private String reportDescription;

    private ReporterDTO reporterDTO;

    private UUID entityOwnerUid;
    private UserDTO entityOwner;

    private String entityTitle;

    public UUID getReportUid() {
        return reportUid;
    }

    public void setReportUid(UUID reportUid) {
        this.reportUid = reportUid;
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

    public ReporterDTO getReporterDTO() {
        return reporterDTO;
    }

    public void setReporterDTO(ReporterDTO reporterDTO) {
        this.reporterDTO = reporterDTO;
    }

    public UserDTO getEntityOwner() {
        return entityOwner;
    }

    public void setEntityOwner(UserDTO entityOwner) {
        this.entityOwner = entityOwner;
    }

    public UUID getEntityOwnerUid() {
        return entityOwnerUid;
    }

    public String getEntityTitle() {
        return entityTitle;
    }

    public void setEntityTitle(String entityTitle) {
        this.entityTitle = entityTitle;
    }
}
