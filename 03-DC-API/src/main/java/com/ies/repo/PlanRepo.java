package com.ies.repo;

import java.util.List;

import com.ies.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PlanRepo extends JpaRepository<Plan, Integer>{
	
	@Query("select distinct(planName) from Plan")
	public List<String> getPlanNames();
}
