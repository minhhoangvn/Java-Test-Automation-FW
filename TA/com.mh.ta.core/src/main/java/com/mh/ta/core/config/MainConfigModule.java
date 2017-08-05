package com.mh.ta.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.yaml.snakeyaml.Yaml;

import com.google.inject.AbstractModule;
import com.mh.ta.core.annotation.ApplicationConfig;
import com.mh.ta.core.helper.FileUtility;

public class MainConfigModule extends AbstractModule {
	private String configFileName = "application.yaml";
	private MainConfig mainConfig;

	public MainConfigModule() {
		initMainConfig();
	}

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
		} catch (IOException e) {
			throw new RuntimeException("Can not load config file");
		}
	}
}
