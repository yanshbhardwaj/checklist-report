package com.iedaas.checklistreport.service;

import com.iedaas.checklistreport.dao.CustomRepository;
import com.iedaas.checklistreport.dao.OwnerRepository;
import com.iedaas.checklistreport.dto.ReportDTO;
import com.iedaas.checklistreport.dao.ReportRepository;
import com.iedaas.checklistreport.dto.ReporterDTO;
import com.iedaas.checklistreport.dto.UserDTO;
import com.iedaas.checklistreport.entity.Owner;
import com.iedaas.checklistreport.entity.Report;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private CustomRepository customRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ReportDTO addReport(Owner requestUser, ReportDTO reportDTO){
        Report report = modelMapper.map(reportDTO, Report.class);
        report.setReporter(requestUser);
        Report reportDb = reportRepository.save(report);
        if(report.getEntityType().equals("comment")){
            customRepository.updateCommentStatus(report.getEntityUid());
        }
        ReportDTO reportDTODb = modelMapper.map(reportDb, ReportDTO.class);
        if(report.getEntityType().equals("checklistRequest")){
            String entityTitle = customRepository.getChecklistRequestTitle(report.getEntityUid());
            reportDTODb.setEntityTitle(entityTitle);
        }
        else if(report.getEntityType().equals("checklist")){
            String entityTitle = customRepository.getChecklist(report.getEntityUid());
            reportDTODb.setEntityTitle(entityTitle);
        }
        ReporterDTO reporterDTO = modelMapper.map(report.getReporter(), ReporterDTO.class);
        reportDTODb.setReporterDTO(reporterDTO);
        Owner entityOwner = ownerRepository.getOwnerByUUID(String.valueOf(report.getEntityOwnerUid()));
        UserDTO entityOwnerDTO = new UserDTO(entityOwner.getReporterUid(), entityOwner.getReporterName(), entityOwner.getReporterImage());
        reportDTODb.setEntityOwner(entityOwnerDTO);
        return reportDTODb;
    }

    public List<ReportDTO> getAllReports(Owner requestUser, Optional<Integer> page, Optional<Integer> size){
        Page<Report> reportPage = reportRepository.findAll(PageRequest.of(page.orElse(0), size.orElse(5)));
        List<ReportDTO> reportDTOList = new ArrayList<>();
        for(Report report : reportPage){
            ReportDTO reportDTO = modelMapper.map(report, ReportDTO.class);
            if(report.getEntityType().equals("checklistRequest")){
                String entityTitle = customRepository.getChecklistRequestTitle(report.getEntityUid());
                reportDTO.setEntityTitle(entityTitle);
            }
            else if(report.getEntityType().equals("checklist")){
                String entityTitle = customRepository.getChecklist(report.getEntityUid());
                reportDTO.setEntityTitle(entityTitle);
            }
            ReporterDTO reporterDTO = modelMapper.map(report.getReporter(), ReporterDTO.class);
            reportDTO.setReporterDTO(reporterDTO);
            Owner entityOwner = ownerRepository.getOwnerByUUID(String.valueOf(report.getEntityOwnerUid()));
            UserDTO entityOwnerDTO = new UserDTO(entityOwner.getReporterUid(), entityOwner.getReporterName(), entityOwner.getReporterImage());
            reportDTO.setEntityOwner(entityOwnerDTO);
            reportDTOList.add(reportDTO);
        }
        return reportDTOList;
    }

    public List<ReportDTO> getAllReportsByStatus(Owner requestUser, String status, Optional<Integer> page, Optional<Integer> size){
        Page<Report> reportPage = reportRepository.findAllByStatus(status, PageRequest.of(page.orElse(0), size.orElse(5)));
        List<ReportDTO> reportDTOList = new ArrayList<>();
        for(Report report : reportPage){
            ReportDTO reportDTO = modelMapper.map(report, ReportDTO.class);
            if(report.getEntityType().equals("checklistRequest")){
                String entityTitle = customRepository.getChecklistRequestTitle(report.getEntityUid());
                reportDTO.setEntityTitle(entityTitle);
            }
            else if(report.getEntityType().equals("checklist")){
                String entityTitle = customRepository.getChecklist(report.getEntityUid());
                reportDTO.setEntityTitle(entityTitle);
            }
            ReporterDTO reporterDTO = modelMapper.map(report.getReporter(), ReporterDTO.class);
            reportDTO.setReporterDTO(reporterDTO);
            Owner entityOwner = ownerRepository.getOwnerByUUID(String.valueOf(report.getEntityOwnerUid()));
            UserDTO entityOwnerDTO = new UserDTO(entityOwner.getReporterUid(), entityOwner.getReporterName(), entityOwner.getReporterImage());
            reportDTO.setEntityOwner(entityOwnerDTO);
            reportDTOList.add(reportDTO);
        }
        return reportDTOList;
    }

    public List<ReportDTO> getAllReportsByEntity(Owner requestUser, String entity, Optional<Integer> page, Optional<Integer> size){
        Page<Report> reportPage = reportRepository.findAllByEntity(entity, PageRequest.of(page.orElse(0), size.orElse(5)));
        List<ReportDTO> reportDTOList = new ArrayList<>();
        for(Report report : reportPage){
            ReportDTO reportDTO = modelMapper.map(report, ReportDTO.class);
            if(report.getEntityType().equals("checklistRequest")){
                String entityTitle = customRepository.getChecklistRequestTitle(report.getEntityUid());
                reportDTO.setEntityTitle(entityTitle);
            }
            else if(report.getEntityType().equals("checklist")){
                String entityTitle = customRepository.getChecklist(report.getEntityUid());
                reportDTO.setEntityTitle(entityTitle);
            }
            ReporterDTO reporterDTO = modelMapper.map(report.getReporter(), ReporterDTO.class);
            reportDTO.setReporterDTO(reporterDTO);
            Owner entityOwner = ownerRepository.getOwnerByUUID(String.valueOf(report.getEntityOwnerUid()));
            UserDTO entityOwnerDTO = new UserDTO(entityOwner.getReporterUid(), entityOwner.getReporterName(), entityOwner.getReporterImage());
            reportDTO.setEntityOwner(entityOwnerDTO);
            reportDTOList.add(reportDTO);
        }
        return reportDTOList;
    }

    public ReportDTO getReportByUUID(Owner requestUser, UUID reportUid){
        Report report = reportRepository.findReportByUUID(String.valueOf(reportUid));
        Owner entityOwner = ownerRepository.getOwnerByUUID(String.valueOf(report.getEntityOwnerUid()));
        UserDTO entityOwnerDTO = new UserDTO(entityOwner.getReporterUid(), entityOwner.getReporterName(), entityOwner.getReporterImage());
        ReportDTO reportDTO = modelMapper.map(report, ReportDTO.class);
        reportDTO.setEntityOwner(entityOwnerDTO);
        return reportDTO;
    }

    public Boolean updateReport(Owner requestUser, UUID reportUid, String action){
        boolean updated = false;
        if(requestUser.getRole().equals("admin")) {
            Report report = reportRepository.findReportByUUID(String.valueOf(reportUid));
            report.setReportAction(action);
            updated = true;
        }
        return updated;
    }

    public void deleteReportByUUID(Owner requestUser, UUID reportUid){
        Report report = reportRepository.findReportByUUID(String.valueOf(reportUid));
        reportRepository.delete(report);
    }
}
