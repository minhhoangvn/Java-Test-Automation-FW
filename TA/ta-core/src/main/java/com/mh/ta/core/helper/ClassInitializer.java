
package com.mh.ta.core.helper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.mh.ta.core.exception.TestContextException;

/**
 * @author minhhoang
 *
 */
public class ClassInitializer {
	public static ClassInitializerInteface initClass() {
		return (pageClass, name) -> {
			Class<?> classInit = null;
			Type type = pageClass.getGenericSuperclass();
			ParameterizedType pType = (ParameterizedType) type;
			for (Type Type : pType.getActualTypeArguments()) {
				Class<?> componentClass = (Class<?>) Type;
				Class<?> parentClass = componentClass.getSuperclass();
				String parrantClassName = parentClass.getSimpleName();
				if (parrantClassName.equals(name))
					classInit = componentClass;
			}
			if (classInit == null)
				throw new TestContextException("Error in Initializer class: " + pageClass.getName());
			return classInit;
		};
	}

	@FunctionalInterface
	public interface ClassInitializerInteface {
		public Class<?> classInitializer(Class<?> cls, String baseClassName);
	}
}
