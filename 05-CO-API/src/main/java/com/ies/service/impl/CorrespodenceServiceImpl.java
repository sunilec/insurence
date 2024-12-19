package com.ies.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ies.entity.CorrespondenceEntity;
import com.ies.entity.EligDetailsEntity;
import com.ies.entity.User;
import com.ies.repo.CorrespondenceEntityRepo;
import com.ies.repo.EligDetailsEntityRepo;
import com.ies.repo.UserRepo;
import com.ies.service.CorrespondenceService;
import com.ies.utils.EmailUtils;
import com.ies.utils.PdfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorrespodenceServiceImpl implements CorrespondenceService {

	@Autowired
	private CorrespondenceEntityRepo corrRepo;

	@Autowired
	private EligDetailsEntityRepo eligDetailsEntityRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PdfUtils pdfUtils;

	@Override
	public String generateNotice(Integer caseNo) {

		User user = userRepo.findById(caseNo).get();
		EligDetailsEntity elig = user.getElig();

		CorrespondenceEntity entity = new CorrespondenceEntity();

		return "success";
	}

	@Override
	public boolean exportPdf(HttpServletResponse response, Integer caseNo) throws Exception {

		User user = userRepo.findById(caseNo).get();
		EligDetailsEntity elig = user.getElig();

		File f = new File("Citizen.pdf");
		pdfUtils.generate(response, elig, f);

		FileInputStream fl = new FileInputStream(f);
		byte[] arr = new byte[(int) f.length()];
		fl.read(arr);

		System.out.print(Arrays.toString(arr));

		CorrespondenceEntity entity = new CorrespondenceEntity();
		entity.setUrl("url");
		entity.setNoticeStatus("Pending");
		entity.setEligFk(elig);
		entity.setCreatedDate(user.getCreateDate());
		entity.setPrintDate(LocalDate.now());
		entity.setUser(user);
		corrRepo.save(entity);

		fl.close();
		f.delete();
		return true;
	}

	@Override
	public void processPendingTriggers() {
		// Get all pending records from co_notices table
		// Get table we need repository interface
		List<CorrespondenceEntity> records = corrRepo.findByNoticeStatus("P");

		for(CorrespondenceEntity entity : records) {
			processEachRecord(entity);
		}

	}



	private void processEachRecord(CorrespondenceEntity correspondence) {
		// This method is responsible to process Each Record based on the entity

		CorrespondenceEntity entity = null;

	//	Long caseNumber = correspondence.getCaseNumber();

		// Based on the case number get Eligibility data
		EligDetailsEntity eligDetails = eligDetailsEntityRepo.findByCaseNumber(correspondence.getCaseNumber());
		// Get citizen data based on case No.

		String planStatus = eligDetails.getPlanStatus();

		File pdfFile = null;

		if ("AP".equals(planStatus)) {
			pdfFile = generateApprovedNotice(eligDetails);
		}else if ("DN".equals(planStatus)) {
			pdfFile = generateDeniedNotice(eligDetails);
		}
		String fileUrl = storePdfInS3(pdfFile);

		boolean isUpdated = updateProcessedRecord(correspondence, fileUrl);

		if (isUpdated) {
			emailUtils.sendEmail("","","", pdfFile);
		}
	}

	private boolean updateProcessedRecord(CorrespondenceEntity correspondence, String fileUrl) {
		// Set status as Completed
		// Set Notice S3 Object URL
		// Update the record in db

		correspondence.setNoticeStatus("C");
		correspondence.setNoticeUrl(fileUrl);
		corrRepo.save(correspondence);

		return true;
	}

	private String storePdfInS3(File pdfFile) {
		// Logic to store in S3 Bucket

		return null;
	}

	private File generateDeniedNotice(EligDetailsEntity eligDetails) {
		// Generate PDF file
		return null;
	}

	private File generateApprovedNotice(EligDetailsEntity eligDetails) {
		// Generate PDF fILE
		return null;
	}


}
