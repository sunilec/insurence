package com.ies.repo;

import com.ies.entity.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EducationEntityRepo extends JpaRepository<EducationEntity, Integer>{

	public EducationEntity findByCaseNumber(Integer caseNumb);
}
