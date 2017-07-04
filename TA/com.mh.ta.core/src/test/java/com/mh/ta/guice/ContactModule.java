package com.mh.ta.guice;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.Yaml;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.mh.ta.core.config.FrameworkSettings;

public class ContactModule extends AbstractModule {

	@Override
	protected void configure() {
		try {
			bind(Contact.class).toInstance(new Contact("Minh Hoang", "6969", "090-6969696969"));
			bind(FrameworkSettings.class)
					.toInstance(this.settings());
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	private FrameworkSettings settings() throws IOException{
		FrameworkSettings setting = (new Yaml()).loadAs(Files.newInputStream(this.configFile()), FrameworkSettings.class);
		return setting;
	}
	private Path configFile() throws IOException {
		List<Path> yamlPaths;
		yamlPaths = Files.find(FileSystems.getDefault().getPath(System.getProperty("user.dir")), 10,
				(p, a) -> (p.toString().toLowerCase().contains("resources"))
						&& (p.toString().toLowerCase().endsWith(".yml")
								|| p.toString().toLowerCase().endsWith(".yaml")))
				.collect(Collectors.toList());
		return yamlPaths.get(0);

	}
}
