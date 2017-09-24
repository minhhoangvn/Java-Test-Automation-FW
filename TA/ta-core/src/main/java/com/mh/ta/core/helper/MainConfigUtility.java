
package com.mh.ta.core.helper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import com.google.common.collect.ImmutableMap;
import com.mh.ta.core.config.LoggerFactory;
import com.mh.ta.core.config.settings.DataSourceConfig;
import com.mh.ta.core.config.settings.DriverConfig;
import com.mh.ta.core.config.settings.EmailConfig;
import com.mh.ta.core.config.settings.MainConfig;
import com.mh.ta.core.config.settings.SUTConfig;
import com.mh.ta.core.exception.TestContextException;

/**
 * @author minhhoang
 *
 */
public class MainConfigUtility {
	Logger log = LoggerFactory.instance().createClassLogger(getClass());

	public void createApplicationConfigTemplate(String path) {
		Yaml yaml = initYaml();
		try {
			FileWriter writer = new FileWriter(path);
			writeInformation(writer);
			writer.write(yaml.dumpAsMap(createMainConfigTemplate()));
			writer.close();
		} catch (IOException e) {
			log.info(yaml.dumpAsMap(createMainConfigTemplate()));
			log.error("Has error in create application configuration template.", e);
			throw new TestContextException(
					"Can not create application configuration template. Open TALog.log for getting application template ");
		}
	}

	private Yaml initYaml() {
		Constructor constructor = new Constructor(MainConfigUtility.class);
		Representer representer = new Representer();
		representer.addClassTag(MainConfigUtility.class, Tag.MAP);
		DumperOptions options = new DumperOptions();
		options.setCanonical(false);
		options.setExplicitStart(false);
		options.setExplicitEnd(false);
		options.setPrettyFlow(true);
		options.setDefaultFlowStyle(FlowStyle.BLOCK);

		return new Yaml(constructor, representer, options);
	}

	private MainConfig createMainConfigTemplate() {
		MainConfig config = new MainConfig();
		config.setDriverConfig(createDriverConfigTemplate());
		config.setSutConfig(createSutConfigTemplate());
		config.setDataSourceConfig(createDataSourceConfigTemplate());
		config.setEmailConfig(createEmailConfigTemplate());
		return config;
	}

	private EmailConfig createEmailConfigTemplate() {
		EmailConfig config = new EmailConfig();
		config.setHost("email host put here");
		config.setAttachedReport(true);
		config.setSendTo(Arrays.asList("email1@domain.com", "email2@domain.com"));
		config.setSubject("Subject put here");
		config.setEmailUser("email user put here");
		config.setEmailPass("email pass put here");
		config.setFromTo("fromto@mail.com");
		config.setEnable(false);
		return config;
	}

	private DataSourceConfig createDataSourceConfigTemplate() {
		DataSourceConfig config = new DataSourceConfig();
		config.setDatabaseDriver("database driver string put here");
		config.setDatabaseConnectionString("database connection string put here");
		config.setDatabaseUserName("database username put here");
		config.setDatabasePassword("database password put here");
		return config;
	}

	private SUTConfig createSutConfigTemplate() {
		SUTConfig config = new SUTConfig();
		config.setEnableRecord(false);
		config.setBaseUri("base uri put here");
		config.setAccounts(Arrays.asList(ImmutableMap.<String, String>builder().put("account1", "account1").build(),
				ImmutableMap.<String, String>builder().put("account2", "account2").build()));
		return config;
	}

	private DriverConfig createDriverConfigTemplate() {
		DriverConfig config = new DriverConfig();
		config.setDriversFolderName("drivers");
		config.setMaximize(false);
		config.setHidden(false);
		config.setImplicitWait(5);
		config.setPageloadTimeout(60);
		config.setDrivercommandTimeout(60);
		config.setHighLighElement(true);
		config.setBackGroundColor("red");
		config.setBorderColor("yellow");
		config.setTimeOutHighlight(500);
		return config;
	}

	private void writeInformation(FileWriter writer) throws IOException {
		writer.write("# Added configuration value, and remove string with !!" + getClass().getName()
				+ " for loaded application config file.");
		writer.write(System.getProperty("line.separator"));
		writer.write("# Copy file to src/main/resource for loading configuration file");
		writer.write(System.getProperty("line.separator"));
	}
}
