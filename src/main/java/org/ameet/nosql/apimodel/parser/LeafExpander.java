package org.ameet.nosql.apimodel.parser;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.ameet.nosql.apimodel.RTSModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * take the RTS model and expand it at the leaf level so we can use that for
 * inserting into object leaf
 * 
 * @author achaubal
 *
 */
@Component
public class LeafExpander {

	private static final Logger LOGGER = LoggerFactory.getLogger(LeafExpander.class);

	@Autowired
	private RTSParser parser;

	public void transform(RTSModel rts) throws IllegalArgumentException, IllegalAccessException {
		transform(RTSModel.class, rts, "/");
	}
	
	public void transform(Class<?> klzz, Object o, String parentVal) throws IllegalArgumentException,
			IllegalAccessException {
		String uidAppliedParent = parentVal + getUid(klzz, o);

		for (Field f : klzz.getDeclaredFields()) {
			makeAccessible(f);
			
			String modParent = uidAppliedParent;
			if (isValidField(f)) {
				modParent = modParent + "/" + f.getName();
			}

			if (!f.getType().isPrimitive() && !f.getType().getName().contains("String")) {

				if (f.getType().getName().contains("List")) {
					List<?> mylist = (List<?>) f.get(o);
					LOGGER.debug(">>> size of datum list:" + mylist.size());
					for (Object o1 : mylist) {
						transform(getParamterizedListClass(f), o1, modParent);
					}
				}
				transform(f.getType(), f.get(o), modParent);
			} else {
				if (isPrintableValue(o, f)) {
					System.out.println("name:" + f.getName() + " Type:" + f.getType().getCanonicalName() + " Parent: "
							+ f.getDeclaringClass().getCanonicalName() + " Value=>" + f.get(o) + " PARENTVAL==>"
							+ modParent);
				}
			}
		}
	}
	/**
	 * from a List<> field, get the parameterized type
	 * @param f
	 * @return
	 */
	private Class<?> getParamterizedListClass(Field f) {
		ParameterizedType stringListType = (ParameterizedType) f.getGenericType();
		Class<?> paramClass = (Class<?>) stringListType.getActualTypeArguments()[0];
		LOGGER.debug(">>> Field name:{} of type List, the parameterized type:{}", f.getName(), paramClass);
		return paramClass;
	}
	private boolean isValidField(Field f) {
		if (f.getType().getCanonicalName().contains("WellModel") || f.getDeclaringClass().getCanonicalName().contains("WellModel")) {
			return true;
		} else {
			return false;
		}
	}
	private boolean isPrintableValue(Object o, Field f) {
		if (o == null || f.getName().equalsIgnoreCase("uid")
				|| !f.getDeclaringClass().getCanonicalName().contains("WellModel")) {
			return false;
		} else {
			return true;
		}
	}

	private String getUid(Class<?> klzz, Object o) throws IllegalArgumentException, IllegalAccessException {
		for (Field f : klzz.getDeclaredFields()) {
			makeAccessible(f);
			if (f.getName().equals("uid")) {
				return "@" + (String) f.get(o);

			}
		}
		return "";
	}

	

	private void makeAccessible(Field f) {
		if (!f.isAccessible()) {
			f.setAccessible(true);
		}
	}
}
