package com.mh.ta.core.config;

public class ReportConfig {
	private String reportFolder = "TestReports", reportFileName = "AutomationReport", reportTitle = "AutomationReport";
	private boolean convertToPdf = true;

	public String getReportFolder() {
		return reportFolder;
	}

	public void setReportFolder(String reportFolder) {
		this.reportFolder = reportFolder;
	}

	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public boolean isConvertToPdf() {
		return convertToPdf;
	}

	public void setConvertToPdf(boolean convertToPdf) {
		this.convertToPdf = convertToPdf;
	}

}
