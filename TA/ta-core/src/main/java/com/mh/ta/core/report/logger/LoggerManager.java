
package com.mh.ta.core.report.logger;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.RollingFileManager;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

/**
 * @author minhhoang
 *
 */
public final class LoggerManager {
	final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
	final Configuration config = ctx.getConfiguration();
	private String logFolderPath;
	private PatternLayout layout;
	private SizeBasedTriggeringPolicy policy;
	private DefaultRolloverStrategy strategy;
	private RollingFileAppender appender;
	private static LoggerManager instance;

	private LoggerManager() {
		initLogFolder();
		configLog4j();
	}

	public static synchronized LoggerManager instance() {
		if (instance == null) {
			instance = new LoggerManager();
		}
		return instance;
	}

	public void configLog4j() {
		initLogConfig();
		createFileAppender(strategy, layout, policy);
		startLogConfig();
	}

	private void initLogFolder() {
		Path rootPath = FileSystems.getDefault().getPath(System.getProperty("user.dir")).getParent();
		String dir = rootPath.toAbsolutePath().toString();
		File logFolder = new File(dir + File.separator + "Log");
		logFolder.mkdirs();
		logFolderPath = logFolder.getAbsolutePath();
	}

	private void initLogConfig() {
		layout = PatternLayout.newBuilder().withConfiguration(config)
				.withPattern("%d{ISO8601} [%t] [%c{10}] [%p] : %m%n").build();
		policy = SizeBasedTriggeringPolicy.createPolicy("10MB");
		strategy = DefaultRolloverStrategy.createStrategy("100", "0", null, null, null, false, config);
		RollingFileManager fileManager = RollingFileManager.getFileManager(logFolderPath + File.separator + "TALog.log",
				logFolderPath + File.separator + "TALog-%d{MM-dd-yyyy}-%i.log", false, false, policy, strategy, null,
				layout, 128, false, false, config);
		policy.initialize(fileManager);
	}

	private void createFileAppender(DefaultRolloverStrategy strategy, PatternLayout layout,
			SizeBasedTriggeringPolicy policy) {
		appender = RollingFileAppender.newBuilder().setConfiguration(config)
				.withFileName(logFolderPath + File.separator + "TALog.log")
				.withFilePattern(logFolderPath + File.separator + "TALog-%d{MM-dd-yyyy}-%i.log").withAppend(true)
				.withName("File").withBufferedIo(true).withBufferSize(512).withImmediateFlush(true).withPolicy(policy)
				.withStrategy(strategy).withLayout(layout).withFilter(null).withIgnoreExceptions(false)
				.withAdvertise(false).withAdvertiseUri("false").build();
	}

	private void startLogConfig() {
		appender.start();
		config.addAppender(appender);
		AppenderRef ref = AppenderRef.createAppenderRef("File", Level.ALL, null);
		AppenderRef[] refs = new AppenderRef[] { ref };
		LoggerConfig loggerConfig = LoggerConfig.createLogger(true, Level.ALL, LogManager.ROOT_LOGGER_NAME, "true",
				refs, null, config, null);
		loggerConfig.addAppender(appender, Level.ALL, null);
		config.addLogger(LogManager.ROOT_LOGGER_NAME, loggerConfig);
		ctx.updateLoggers();
	}
}
