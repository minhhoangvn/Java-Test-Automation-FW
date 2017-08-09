package com.mh.ta.core.helper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.mh.ta.core.exception.InstantiationPageObjectException;

public class ClassInitializer {
	public static ClassInitializerInteface initClass() {
		ClassInitializerInteface function = (pageClass, name) -> {
			Class<?> classInit = null;
			Type type = pageClass.getGenericSuperclass();
			ParameterizedType pType = (ParameterizedType) type;
			for (Type Type : pType.getActualTypeArguments()) {
				Class<?> componentClass = (Class<?>) Type;
				if (componentClass.getSuperclass().getSimpleName().equals(name))
					classInit = componentClass;
			}
			if (classInit == null)
				throw new InstantiationPageObjectException("Error in Initializer class: " + pageClass.getName());
			return classInit;
		};
		return function;
	}

	@FunctionalInterface
	public interface ClassInitializerInteface {
		public Class<?> classInitializer(Class<?> cls, String baseClassName);
	}
}
