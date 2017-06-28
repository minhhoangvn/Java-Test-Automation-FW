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

public class PageObjectDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
		Map<String, Object> attrs = metadata.getAnnotationAttributes(InjectPageObject.class.getName());
		System.err.println(attrs);
		String[] basePackages = (String[]) attrs.get("basePackages");
		if (basePackages.length == 1 && StringUtils.isEmpty(basePackages[0])) {
			String basePackage = ((StandardAnnotationMetadata) metadata).getIntrospectedClass().getPackage().getName();
			System.err.println(basePackage);
			basePackages = new String[] { basePackage };
			System.err.println(basePackages);
		}

		findPageObjectClasses(basePackages).forEach(beanDef -> {
			// beanDef.setScope("test");
			beanDef.setLazyInit(true);
			registry.registerBeanDefinition(beanDef.getBeanClassName(), beanDef);
		});
	}

	private Set<BeanDefinition> findPageObjectClasses(String... basePackages) {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(PageObject.class));

		Set<BeanDefinition> beanDefs = new HashSet<>();

		Stream.of(basePackages).forEach(basePackage -> beanDefs.addAll(scanner.findCandidateComponents(basePackage)));
		System.err.println(beanDefs);
		return beanDefs;
	}
}