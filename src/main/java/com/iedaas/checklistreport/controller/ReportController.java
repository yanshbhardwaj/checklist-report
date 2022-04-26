package com.iedaas.checklistreport.controller;

import com.iedaas.checklistreport.AuthorizationFilter;
import com.iedaas.checklistreport.dto.ReportDTO;
import com.iedaas.checklistreport.entity.Owner;
import com.iedaas.checklistreport.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ReportController {

    @Autowired
    private AuthorizationFilter authorizationFilter;

    @Autowired
    private ReportService reportService;

    @PostMapping("/report")
    public ReportDTO addReport(HttpServletRequest request, @RequestBody ReportDTO reportDTO){
        Owner owner = authorizationFilter.authenticate(request);
        ReportDTO addedReportDTO = reportService.addReport(owner, reportDTO);
        return addedReportDTO;
    }

    @GetMapping("/report/{reportUid}")
    public ReportDTO getReportByUUID(HttpServletRequest request, @PathVariable UUID reportUid){
        Owner requestUser = authorizationFilter.authenticate(request);
        if(requestUser.getRole().equals("admin")){
            ReportDTO reportDTO = reportService.getReportByUUID(requestUser, reportUid);
            return reportDTO;
        }
        else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/report")
    public List<ReportDTO> getAllReports(HttpServletRequest request,@RequestParam Optional<String> status, @RequestParam Optional<String> entity, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        Owner requestUser = authorizationFilter.authenticate(request);
        if(page.isPresent()){
            if(page.get()<=0 || page.get()>50) {
                page = Optional.of(0);
            }
            else {
                page = Optional.of(page.get() - 1);
            }
        }
        if(size.isPresent()){
            if(size.get()<0 || size.get()>50){
                size = Optional.of(5);
            }
        }
        if(requestUser.getRole().equals("admin")){
            if(status.isPresent()){
                List<ReportDTO> reportDTOList = reportService.getAllReportsByStatus(requestUser, status.get(), page, size);
                return reportDTOList;
            }
            else if(entity.isPresent()){
                List<ReportDTO> reportDTOList = reportService.getAllReportsByEntity(requestUser, entity.get(), page, size);
                return reportDTOList;
            }
            else {
                List<ReportDTO> reportDTOList = reportService.getAllReports(requestUser, page, size);
                return reportDTOList;
            }
        }
        else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/report/{reportUid}/{action}")
    public void updateReport(HttpServletRequest request, @PathVariable UUID reportUid, @PathVariable String action){
        Owner requestUser = authorizationFilter.authenticate(request);
        boolean updated;
        try{
            updated = reportService.updateReport(requestUser, reportUid, action);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(!updated){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/report/{reportUid}")
    public void deleteReport(HttpServletRequest request, @PathVariable UUID reportUid){
        Owner requestUser = authorizationFilter.authenticate(request);
        reportService.deleteReportByUUID(requestUser, reportUid);
    }
}
