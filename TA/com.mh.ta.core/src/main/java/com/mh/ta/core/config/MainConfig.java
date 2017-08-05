package com.mh.ta.core.config;

public class MainConfig {
	private DataSourceConfig dataSourceConfig;
	private EmailConfig emailConfig;
	private DriverConfig driverConfig;
	private ReportConfig reportConfig;
	private SUTConfig sutConfig;

	public DataSourceConfig getDataSourceConfig() {
		if (dataSourceConfig == null)
			dataSourceConfig = new DataSourceConfig();
		return dataSourceConfig;
	}

	public void setDataSourceConfig(DataSourceConfig dataSourceConfig) {
		this.dataSourceConfig = dataSourceConfig;
	}

	public EmailConfig getEmailConfig() {
		if (emailConfig == null)
			emailConfig = new EmailConfig();
		return emailConfig;
	}

	public void setEmailConfig(EmailConfig emailConfig) {
		this.emailConfig = emailConfig;
	}

	public DriverConfig getDriverConfig() {
		if (driverConfig == null)
			driverConfig = new DriverConfig();
		return driverConfig;
	}

	public void setDriverConfig(DriverConfig driverConfig) {
		this.driverConfig = driverConfig;
	}

	public ReportConfig getReportConfig() {
		if (reportConfig == null)
			reportConfig = new ReportConfig();
		return reportConfig;
	}

	public void setReportConfig(ReportConfig reportConfig) {
		this.reportConfig = reportConfig;
	}

	public SUTConfig getSutConfig() {
		if (sutConfig == null)
			sutConfig = new SUTConfig();
		return sutConfig;
	}

	public void setSutConfig(SUTConfig sutConfig) {
		this.sutConfig = sutConfig;
	}
}
