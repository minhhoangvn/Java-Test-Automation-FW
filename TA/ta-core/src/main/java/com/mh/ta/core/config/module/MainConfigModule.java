
package com.mh.ta.core.config.module;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import com.google.inject.AbstractModule;
import com.mh.ta.core.config.LoggerFactory;
import com.mh.ta.core.config.annotation.ApplicationConfig;
import com.mh.ta.core.config.settings.MainConfig;
import com.mh.ta.core.exception.TestContextException;
import com.mh.ta.core.helper.FileUtility;
import com.mh.ta.core.helper.MainConfigUtility;

/**
 * @author minhhoang
 *
 */
class MainConfigModule extends AbstractModule {
	private String configFileName = "application.yaml";
	private MainConfig mainConfig;
	private Logger log = LoggerFactory.instance().createClassLogger(getClass());

	public MainConfigModule(String configFileName) {
		this.configFileName = configFileName;
		initMainConfig();
	}

	@Override
	protected void configure() {
		bind(MainConfig.class).annotatedWith(ApplicationConfig.class).toInstance(mainConfig);
	}

	private void initMainConfig() {
		try {
			Yaml yaml = new Yaml();
			InputStream input = Files.newInputStream(FileUtility.findFileOrFolderPath(this.configFileName, false));
			this.mainConfig = yaml.loadAs((input), MainConfig.class);
		} catch (IOException | RuntimeException e) {
			String sampleConfigTemplate = System.getProperty("user.dir") + File.separator + "ApplicationTemplate.yaml";
			MainConfigUtility configUtils = new MainConfigUtility();
			configUtils.createApplicationConfigTemplate(sampleConfigTemplate);
			log.error("Error in load configuration file.", e);
			throw new TestContextException(
					"Can not load configuration file or can not find application configuration file, please use "
							+ sampleConfigTemplate + " for create configuration file");
		}
	}
}
