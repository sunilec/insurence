package com.ies.repo;

import com.ies.entity.CaseWorker;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CaseWorkerRepo extends JpaRepository<CaseWorker, Integer> {
	
	
	public CaseWorker findByCaseWorkerEmail(String email);
	
	public CaseWorker findByCaseWorkerEmailAndCaseWorkerPwd(String email, String pwd);
	


}
