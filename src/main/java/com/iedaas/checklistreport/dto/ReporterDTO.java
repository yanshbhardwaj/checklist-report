package com.iedaas.checklistreport.dto;

import java.util.UUID;

public class ReporterDTO {

    private UUID reporterUid;

    private String reporterName;

    private String reporterImage;

    public UUID getReporterUid() {
        return reporterUid;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterUid(UUID reporterUid) {
        this.reporterUid = reporterUid;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getReporterImage() {
        return reporterImage;
    }

    public void setReporterImage(String reporterImage) {
        this.reporterImage = reporterImage;
    }
}
