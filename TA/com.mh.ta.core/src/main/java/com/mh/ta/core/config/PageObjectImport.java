package com.mh.ta.core.config;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

import com.mh.ta.core.annotation.InjectPageObject;
import com.mh.ta.core.annotation.PageObject;

public class PageObjectImport implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
		System.err.println("Page Object import ");
		Map<String, Object> attrs = metadata.getAnnotationAttributes(InjectPageObject.class.getName());
		String[] pageObjectPackageName = (String[]) attrs.get("pageObjectPackageName");
		if (pageObjectPackageName.length == 1 && StringUtils.isEmpty(pageObjectPackageName[0])) {
			String packageName = ((StandardAnnotationMetadata) metadata).getIntrospectedClass().getPackage().getName();
			pageObjectPackageName = new String[] { packageName };

		}
		findPageObjectClasses(pageObjectPackageName).forEach(pageObject -> {
			// beanDef.setScope("test");
			pageObject.setLazyInit(true);
			registry.registerBeanDefinition(pageObject.getBeanClassName(), pageObject);
		});
	}

	private Set<BeanDefinition> findPageObjectClasses(String... pageObjectPackageName) {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(PageObject.class));
		Set<BeanDefinition> pageObject = new HashSet<>();
		Stream.of(pageObjectPackageName)
				.forEach(basePackage -> pageObject.addAll(scanner.findCandidateComponents(basePackage)));
		return pageObject;
	}
}