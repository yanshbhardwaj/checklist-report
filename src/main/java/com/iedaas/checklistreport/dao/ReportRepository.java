package com.iedaas.checklistreport.dao;

import com.iedaas.checklistreport.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query(value = "select * from report r where r.report_uid=?1", nativeQuery = true)
    Report findReportByUUID(String reportUid);

    @Query(value = "select * from report r where r.report_action=?1", nativeQuery = true)
    Page<Report> findAllByStatus(String reportAction, Pageable pageable);

    @Query(value = "select * from report r where r.entity_type=?1", nativeQuery = true)
    Page<Report> findAllByEntity(String entityType, Pageable pageable);
}
