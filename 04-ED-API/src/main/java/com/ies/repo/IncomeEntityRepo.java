package com.ies.repo;

import com.ies.entity.IncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IncomeEntityRepo extends JpaRepository<IncomeEntity, Integer> {

	public IncomeEntity findByCaseNumber(Integer caseNumb);

}
