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
import com.mh.ta.core.annotation.Settings;

public class SettingModule extends AbstractModule {
	private final List<String> configExtensionType = Arrays.asList("yml", "yaml");

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

		yamlPaths = Files
				.find(FileSystems.getDefault().getPath(System.getProperty("user.dir")).getParent(), 20,
						(filePath, attribute) -> this.isAppilicationConfigFile(filePath,
								this.getAnnotationSettingValue().get(0), this.getAnnotationSettingValue().get(1)))
				.collect(Collectors.toList());
		return yamlPaths.get(0);

	}

	private Boolean isAppilicationConfigFile(Path filePath, String fileName, String folderName) {
		Boolean isCorrectFolder = filePath.toString().toLowerCase().contains(folderName) || folderName.length() == 0;
		Boolean isCorrectFileName = filePath.getFileName().toString().contains(fileName);
		Boolean isCorrectExtensionType = filePath.toString().toLowerCase().endsWith(configExtensionType.get(0))
				|| filePath.toString().toLowerCase().endsWith(configExtensionType.get(1));
		return isCorrectFolder && isCorrectFileName && isCorrectExtensionType;
	}

	private List<String> getAnnotationSettingValue() {
		Class<FrameworkSettings> cls = FrameworkSettings.class;
		Settings settingAnnotation = cls.getAnnotation(Settings.class);
		return Arrays.asList(settingAnnotation.configFileName(), settingAnnotation.configFolderName());
	}
}
