package com.iedaas.checklistreport.dao;

import com.iedaas.checklistreport.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    @Query(value = "select * from user where user_uid=?1", nativeQuery = true)
    Owner getOwnerByUUID(String ownerUid);
}
