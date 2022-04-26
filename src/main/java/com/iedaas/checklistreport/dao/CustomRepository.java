package com.iedaas.checklistreport.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class CustomRepository {

    private static final Logger logger = LoggerFactory.getLogger(CustomRepository.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getChecklistRequestTitle(UUID checklistRequestUid){
        String sql = String.format("select title from checklist_request where checklist_request_uid='%s'", checklistRequestUid);
        String checklistRequestTitle = jdbcTemplate.queryForObject(sql, String.class);
        logger.info("checklist_request_uid :={}, sql :={}", checklistRequestUid, sql);
        return checklistRequestTitle;
    }

    public String getChecklist(UUID checklistUid){
        String sql = String.format("select checklist from checklist where checklist_uid='%s'", checklistUid);
        String checklist = jdbcTemplate.queryForObject(sql, String.class);
        logger.info("checklist_uid :={}, sql :={}", checklistUid, sql);
        return checklist;
    }

    public void updateCommentStatus(UUID commentUid){
        String sql = String.format("update comment set status=3 where comment_uid='%s'", commentUid);
        jdbcTemplate.update(sql);
    }
}
