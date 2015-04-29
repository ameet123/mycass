package org.ameet.nosql.apimodel.parser;

import java.lang.reflect.InvocationTargetException;

import org.ameet.nosql.Application;
import org.ameet.nosql.apimodel.RTSModel;
import org.ameet.nosql.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes={Application.class})
public class BeanPropertyTester extends AbstractTestNGSpringContextTests {
	@Autowired
	private RTSParser parser;
	@Autowired
	private LeafExpander expander;

	@Test
	public void testBeanDescribe() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		RTSModel rts = parser.parse(Utility.pickupTemplateWellJsonFile());
		
		expander.transform(rts);
		
		
	}

}
