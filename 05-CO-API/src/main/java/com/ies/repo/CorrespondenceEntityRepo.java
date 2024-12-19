package com.ies.repo;

import com.ies.entity.CorrespondenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CorrespondenceEntityRepo extends JpaRepository<CorrespondenceEntity, Integer>{

    public List<CorrespondenceEntity> findByNoticeStatus(String status);

}
