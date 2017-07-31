package com.mh.ta.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.Yaml;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class SettingModule extends AbstractModule {
	private final List<String> configExtensionType = Arrays.asList("yml", "yaml");
	private final String settingFileName;

	public SettingModule(String settingPath) {
		this.settingFileName = settingPath;
	}

	@Override
	protected void configure() {
		bind(FrameworkSettings.class).toInstance(this.settings());
		bind(FrameworkSettings.DriverConfig.class).annotatedWith(Names.named("driver-config"))
				.toInstance(this.settings().getDriverConfig());
		bind(FrameworkSettings.SUTConfig.class).annotatedWith(Names.named("sut-config"))
				.toInstance(this.settings().getSutConfig());
		bind(FrameworkSettings.DatabaseConfig.class).annotatedWith(Names.named("database-config"))
				.toInstance(this.settings().getDatabaseConfig());
	}

	private FrameworkSettings settings() {		
		try {
			Yaml yaml = new Yaml();
			InputStream input = Files.newInputStream(this.configFilePath());
			FrameworkSettings setting = yaml.loadAs((input), FrameworkSettings.class);
			return setting;
		} catch (IOException e) {
			throw new RuntimeException("Can not load config file");
		}		
	}

	private Path configFilePath() {
		List<Path> yamlPaths;
		try{
		yamlPaths = Files
				.find(FileSystems.getDefault().getPath(System.getProperty("user.dir")).getParent(), 20,
						(filePath, attribute) -> this.isAppilicationConfigFile(filePath,
								this.settingFileName))
				.collect(Collectors.toList());		
		}
		catch (IOException e){
			throw new RuntimeException(e);
		}
		if (yamlPaths.size()==0) throw new RuntimeException("Can not find application config");
		return yamlPaths.get(0);
	}

	private Boolean isAppilicationConfigFile(Path filePath, String fileName) {
		Boolean isCorrectFileName = filePath.getFileName().toString().contains(fileName);
		Boolean isCorrectExtensionType = filePath.toString().toLowerCase().endsWith(configExtensionType.get(0))
				|| filePath.toString().toLowerCase().endsWith(configExtensionType.get(1));
		return isCorrectFileName && isCorrectExtensionType;
	}

}
