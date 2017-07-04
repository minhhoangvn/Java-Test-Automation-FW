package com.mh.ta.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.Yaml;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class TestRunningConfigModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(FrameworkSettings.class).toInstance(this.settings());
		bind(FrameworkSettings.DriverConfig.class).annotatedWith(Names.named("driver-config"))
				.toInstance(this.settings().getDriverConfig());
		bind(FrameworkSettings.SUTConfig.class).annotatedWith(Names.named("sut-config"))
				.toInstance(this.settings().getSutConfig());
	}

	private FrameworkSettings settings() {
		FrameworkSettings setting = new FrameworkSettings();
		try {
			Yaml yaml = new Yaml();
			InputStream input = Files.newInputStream(this.configFilePath());
			setting = yaml.loadAs((input), FrameworkSettings.class);
		} catch (IOException e) {
			System.err.println(e);
		}
		return setting;
	}

	private Path configFilePath() throws IOException {
		List<Path> yamlPaths;

		yamlPaths = Files.find(FileSystems.getDefault().getPath(System.getProperty("user.dir")), 10,
				(filePath, attribute) -> this.isAppilicationConfigFile(filePath)).collect(Collectors.toList());
		return yamlPaths.get(0);

	}

	private Boolean isAppilicationConfigFile(Path filePath) {
		Boolean isCorrectFolder = filePath.toString().toLowerCase().contains("resources");
		Boolean isCorrectExtensionType = filePath.toString().toLowerCase().endsWith(".yml")
				|| filePath.toString().toLowerCase().endsWith(".yaml");
		return isCorrectFolder && isCorrectExtensionType;
	}
}
