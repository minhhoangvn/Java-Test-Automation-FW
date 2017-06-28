package com.mh.ta.annotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.mh.ta.core.annotation.InjectPageObject;
import com.mh.ta.core.config.PageObjectImport;
import com.mh.ta.core.config.TestRunningConfig;

@SpringBootApplication
@InjectPageObject
@Import({ TestRunningConfig.class, PageObjectImport.class })
public class AppConfig {

}
