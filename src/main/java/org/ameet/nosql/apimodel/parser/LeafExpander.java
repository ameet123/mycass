package org.ameet.nosql.apimodel.parser;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.ameet.nosql.apimodel.RTSModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

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

	/**
	 * a recursive function to take the RTS object and flatten it
	 * 
	 * @param klzz
	 * @param o
	 * @param parentVal
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void transform(Class<?> klzz, Object o, String parentVal) throws IllegalArgumentException,
			IllegalAccessException {
		String uidAppliedParent = applyUid(parentVal, klzz, o);

		for (Field f : klzz.getDeclaredFields()) {
			makeAccessible(f);

			String modParent = chainParentValue(f, uidAppliedParent);

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
					LOGGER.debug("name:{} Type:{} ParentClass:{} Value=>{} PARENT_VAL=>{}", f.getName(), f.getType()
							.getCanonicalName(), f.getDeclaringClass().getCanonicalName(), f.get(o), modParent);
					System.out.println("Key==>" + Strings.padEnd(modParent, 80, ' ') + " Value==>" + f.get(o));
				}
			}
		}
	}

	public String chainParentValue(Field f, String uidAppliedParent) {

		if (isValidField(f)) {
			StringBuilder sb = new StringBuilder(uidAppliedParent);
			sb.append("/");
			sb.append(f.getName());
			return sb.toString();
			// modParent = modParent + "/" + f.getName();
		}
		return uidAppliedParent;
	}

	/**
	 * apply UID if found and append that to the parentvalue
	 * 
	 * @param s
	 * @param klzz
	 * @param o
	 * @return
	 */
	private String applyUid(String s, Class<?> klzz, Object o) {
		StringBuilder sb = new StringBuilder(s);
		sb.append(getUid(klzz, o));
		return sb.toString();
	}

	/**
	 * from a List<> field, get the parameterized type
	 * 
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
		if (f.getType().getCanonicalName().contains("WellModel")
				|| f.getDeclaringClass().getCanonicalName().contains("WellModel")) {
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

	private String getUid(Class<?> klzz, Object o) {
		for (Field f : klzz.getDeclaredFields()) {
			makeAccessible(f);
			if (f.getName().equals("uid")) {
				try {
					return "@" + (String) f.get(o);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
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
