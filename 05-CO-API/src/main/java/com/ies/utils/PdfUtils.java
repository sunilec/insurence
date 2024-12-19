package com.ies.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletResponse;

import com.ies.entity.EligDetailsEntity;
import org.springframework.stereotype.Component;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


@Component
public class PdfUtils {

	@SuppressWarnings("static-access")
	public void generate(HttpServletResponse response, EligDetailsEntity elig, File f) throws Exception {

		Document document = new Document(PageSize.A4);
		PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
		pdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();
		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		// Creating paragraph
		Paragraph paragraph = new Paragraph("Citizen Details", fontTiltle);

		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		// Adding the created paragraph in document
		document.add(paragraph);

		PdfPTable table = new PdfPTable(7);
		table.setSpacingBefore(6);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Elig Id", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("PlanName", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("PlanStatus", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("DenialReason", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("EligStartDate", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("EligEndDate", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("benefitAmount", font));
		table.addCell(cell);

		table.addCell(String.valueOf(elig.getEdId()));
		table.addCell(elig.getPlanName());
		table.addCell(elig.getPlanStatus());
		table.addCell(elig.getDenialReason());

		if (null != elig.getEligStartDate()) {
			table.addCell(elig.getEligStartDate() + "");
		} else {
			table.addCell("N/A");
		}

		if (null != elig.getEligEndDate()) {
			table.addCell(elig.getEligEndDate() + "");
		} else {
			table.addCell("N/A");
		}

		if (0 != elig.getBenefitAmt()) {
			table.addCell(elig.getBenefitAmt() + "");
		} else {
			table.addCell("N/A");
		}
		document.add(table);
		document.close();
	}
}