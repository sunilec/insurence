package com.ies.repo;

import java.util.List;

import com.ies.entity.KidsEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface KidsEntityRepo extends JpaRepository<KidsEntity, Integer> {
	
	public List<KidsEntity> findByCaseNumber(Integer caseNumb);
}
