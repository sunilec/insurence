package com.ies.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.ies.binding.Entries;
import com.ies.entity.*;
import com.ies.repo.*;
import com.ies.service.EdService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EdServiceImpl implements EdService {

	@Autowired
	private EligDetailsEntityRepo eligRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private IncomeEntityRepo incomeRepo;

	@Autowired
	private EducationEntityRepo eduRepo;

	@Autowired
	private KidsEntityRepo kidsRepo;

	@Override
	public String eligibilityCheck(Integer caseNo) {

		User user = userRepo.findById(caseNo).get();
		String planName = user.getPlanName();

		Double totalIncome = 0.0;
		if ("SNAP".equals(planName)) {

			IncomeEntity findByCaseNumber = incomeRepo.findByCaseNumber(caseNo);
			Double monthly = findByCaseNumber.getMonthlySalaryIncome();
			Double property = findByCaseNumber.getPropertyIncome();
			Double rent = findByCaseNumber.getRentIncome();
			totalIncome = monthly + property + rent;

			if (totalIncome < 300) {
				EligDetailsEntity entity = new EligDetailsEntity();
				entity.setPlanName(planName);
				entity.setPlanStatus("Approved");
				entity.setEligStartDate(LocalDate.now());
				entity.setEligEndDate(LocalDate.now().plusYears(2));
				entity.setBenefitAmt(250);
				entity.setDenialReason("NA");
				entity.setUser(user);
				eligRepo.save(entity);
				
				user.setElig(entity);
				userRepo.save(user);
				
				return "Approved";
			} else {
				EligDetailsEntity entity = new EligDetailsEntity();
				entity.setPlanName(planName);
				entity.setPlanStatus("Denied");
				entity.setDenialReason("Income Condition Failed");
				entity.setUser(user);
				eligRepo.save(entity);
				
				user.setElig(entity);
				userRepo.save(user);
				
				return "Denied";
			}
		}
		if ("CCAP".equals(planName)) {

			IncomeEntity findByCaseNumber = incomeRepo.findByCaseNumber(caseNo);
			Double monthly = findByCaseNumber.getMonthlySalaryIncome();
			Double property = findByCaseNumber.getPropertyIncome();
			Double rent = findByCaseNumber.getRentIncome();
			totalIncome = monthly + property + rent;

			List<KidsEntity> kids = kidsRepo.findByCaseNumber(caseNo);
			int count = 0;
			for (KidsEntity kid : kids) {
				if (kid.getKidAge() > 16) {
					count++;
				}
			}
			if (totalIncome < 500 && count == 0) {
				EligDetailsEntity entity = new EligDetailsEntity();
				entity.setPlanName(planName);
				entity.setPlanStatus("Approved");
				entity.setEligStartDate(LocalDate.now());
				entity.setEligEndDate(LocalDate.now().plusYears(2));
				entity.setBenefitAmt(250);
				entity.setDenialReason("NA");
				entity.setUser(user);
				eligRepo.save(entity);
				
				user.setElig(entity);
				userRepo.save(user);
				
				return "Approved";
			} else {
				EligDetailsEntity entity = new EligDetailsEntity();
				entity.setPlanName(planName);
				entity.setPlanStatus("Denied");
				if (count != 0) {
					entity.setDenialReason("Kids Age Above 16");
					entity.setUser(user);
					eligRepo.save(entity);
					return "Denied";
				}
				entity.setDenialReason("Income Condition Failed");
				entity.setUser(user);
				eligRepo.save(entity);
				
				user.setElig(entity);
				userRepo.save(user);
				
				return "Denied";
			}
		}

		if ("Medicaid".equals(planName)) {

			IncomeEntity findByCaseNumber = incomeRepo.findByCaseNumber(caseNo);
			Double monthly = findByCaseNumber.getMonthlySalaryIncome();
			Double property = findByCaseNumber.getPropertyIncome();
			Double rent = findByCaseNumber.getRentIncome();
			totalIncome = monthly + property + rent;

			if (totalIncome < 320) {
				EligDetailsEntity entity = new EligDetailsEntity();
				entity.setPlanName(planName);
				entity.setPlanStatus("Approved");
				entity.setEligStartDate(LocalDate.now());
				entity.setEligEndDate(LocalDate.now().plusYears(2));
				entity.setBenefitAmt(150);
				entity.setDenialReason("NA");
				entity.setUser(user);
				eligRepo.save(entity);
				
				user.setElig(entity);
				userRepo.save(user);
				
				return "Approved";
			} else {
				EligDetailsEntity entity = new EligDetailsEntity();
				entity.setPlanName(planName);
				entity.setPlanStatus("Denied");
				entity.setDenialReason("Income Condition Failed");
				entity.setUser(user);
				eligRepo.save(entity);
				
				user.setElig(entity);
				userRepo.save(user);
				
				return "Denied";
			}
		}

		if ("RIW".equals(planName)) {

			IncomeEntity findByCaseNumber = incomeRepo.findByCaseNumber(caseNo);
			Double salary = findByCaseNumber.getMonthlySalaryIncome();

			EducationEntity education = eduRepo.findByCaseNumber(caseNo);

			if (salary.equals(0) && "Bachelor".equals(education.getHighestDegree())) {
				EligDetailsEntity entity = new EligDetailsEntity();
				entity.setPlanName(planName);
				entity.setPlanStatus("Approved");
				entity.setEligStartDate(LocalDate.now());
				entity.setEligEndDate(LocalDate.now().plusYears(2));
				entity.setBenefitAmt(250);
				entity.setDenialReason("NA");
				entity.setUser(user);
				eligRepo.save(entity);
				
				user.setElig(entity);
				userRepo.save(user);
				
				return "Approved";
			} else {
				EligDetailsEntity entity = new EligDetailsEntity();
				entity.setPlanName(planName);
				entity.setPlanStatus("Denied");
				if (!salary.equals(0)) {
					entity.setDenialReason("Salary Condition Failed");
					entity.setUser(user);
					eligRepo.save(entity);
					
					user.setElig(entity);
					userRepo.save(user);
					
					return "Denied";
				}
				entity.setDenialReason("Education Condition Failed");
				entity.setUser(user);
				eligRepo.save(entity);
				return "Denied";
			}
		}

		if ("Medicare".equals(planName)) {

			String strDate = user.getDob();
			LocalDate parse = LocalDate.parse(strDate);
			int years = Period.between(parse, LocalDate.now()).getYears();

			if (years < 65) {
				EligDetailsEntity entity = new EligDetailsEntity();
				entity.setPlanName(planName);
				entity.setPlanStatus("Approved");
				entity.setEligStartDate(LocalDate.now());
				entity.setEligEndDate(LocalDate.now().plusYears(2));
				entity.setBenefitAmt(250);
				entity.setDenialReason("NA");
				entity.setUser(user);
				eligRepo.save(entity);
				
				user.setElig(entity);
				userRepo.save(user);
				
				return "Approved";
			} else {
				EligDetailsEntity entity = new EligDetailsEntity();
				entity.setPlanName(planName);
				entity.setPlanStatus("Denied");
				entity.setDenialReason("Income Condition Failed");
				entity.setUser(user);
				eligRepo.save(entity);
				
				user.setElig(entity);
				userRepo.save(user);
				
				return "Denied";
			}
		}
		return "";
	}
	public List<Entries> getAllData() {

		List<EligDetailsEntity> findAll = eligRepo.findAll();
		List<Entries> list = new ArrayList<>();

		
		for (EligDetailsEntity ent : findAll) {
			Entries entry = new Entries();
			BeanUtils.copyProperties(ent, entry);
			list.add(entry);
		}

		return list;
	}
}