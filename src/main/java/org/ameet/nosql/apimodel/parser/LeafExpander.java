package org.ameet.nosql.apimodel.parser;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ameet.nosql.apimodel.RTSModel;
import org.ameet.nosql.exception.ParseCode1000;
import org.ameet.nosql.exception.RTSException;
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
	private static final String DELIMITER = "/";
	private static final String UID_DELIM = "@";
	private static final String UID_KEY = "uid";
	private static final String ROOT_NODE = "WellModel";
	
	@Autowired
	private RTSParser parser;

	public Map<String, Object> flatten(RTSModel rts) {
		Map<String, Object> kvMap = new HashMap<String, Object>();
		transform(RTSModel.class, rts, DELIMITER, kvMap);		
		return kvMap;
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
	public void transform(Class<?> klzz, Object o, String parentVal, Map<String, Object> kvMap) {
		String uidAppliedParent = applyUid(parentVal, klzz, o);

		for (Field f : klzz.getDeclaredFields()) {
			makeAccessible(f);
			String modParent = chainParentValue(f, uidAppliedParent);
			Object fieldValue = getFieldValue(f, o);

			if (!f.getType().isPrimitive() && !f.getType().getName().contains("String")) {
				if (f.getType().getName().contains("List")) {
					List<?> mylist = (List<?>) fieldValue;
					LOGGER.debug(">>> size of datum list:" + mylist.size());
					for (Object o1 : mylist) {
						transform(getParamterizedListClass(f), o1, modParent, kvMap);
					}
				}
				transform(f.getType(), fieldValue, modParent, kvMap);
			} else {
				if (isPrintableValue(o, f)) {
					LOGGER.debug(">>> name:{} Type:{} ParentClass:{} Value=>{} PARENT_VAL=>{}", f.getName(), f
							.getType().getCanonicalName(), f.getDeclaringClass().getCanonicalName(), fieldValue,
							modParent);
					// add to final kv map
					kvMap.put(modParent, fieldValue);
				}
			}
		}
	}

	private Object getFieldValue(Field f, Object o) {
		if (o == null) {
			return null;
		}
		try {
			return f.get(o);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RTSException(ParseCode1000.JSON_MODEL_FIELD_VALUE_GET).set("field", f.getName());
		}
	}

	/**
	 * if this is a valid field then append the field name
	 * 
	 * @param f
	 * @param uidAppliedParent
	 * @return
	 */
	private String chainParentValue(Field f, String uidAppliedParent) {
		if (isValidField(f)) {
			StringBuilder sb = new StringBuilder(uidAppliedParent);
			sb.append(DELIMITER);
			sb.append(f.getName());
			return sb.toString();
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
		if (f.getType().getCanonicalName().contains(ROOT_NODE)
				|| f.getDeclaringClass().getCanonicalName().contains(ROOT_NODE)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isPrintableValue(Object o, Field f) {
		if (o == null || f.getName().equalsIgnoreCase(UID_KEY)
				|| !f.getDeclaringClass().getCanonicalName().contains(ROOT_NODE)) {
			return false;
		} else {
			return true;
		}
	}

	private String getUid(Class<?> klzz, Object o) {
		for (Field f : klzz.getDeclaredFields()) {
			makeAccessible(f);
			if (f.getName().equals(UID_KEY)) {
				try {
					return new StringBuilder(UID_DELIM).append( (String) f.get(o) ).toString();
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
