package com.project.portal.common;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class PdfView extends AbstractView {
	
	
	@Autowired
	DataSource datasource;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, 
											HttpServletRequest request,
											HttpServletResponse response) throws Exception {
		
		String url = "jdbc:oracle:thin:@portal_high?TNS_ADMIN=/Users/tessa/Wallet_portal";
		String id = "ADMIN";
		String pw = "finalProject@1234";
		
		Connection conn = DriverManager.getConnection(url, id, pw);
		String reportFile = (String) model.get("filename");
		@SuppressWarnings("unchecked")
		HashMap<String, Object> map = (HashMap<String, Object>) model.get("param");
		InputStream jasperStream = getClass().getResourceAsStream(reportFile);
		response.setContentType("application/pdf");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	
	}
	
	
	
}
