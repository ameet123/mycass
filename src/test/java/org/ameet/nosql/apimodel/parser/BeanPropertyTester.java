package org.ameet.nosql.apimodel.parser;

import java.util.Map;
import java.util.Map.Entry;

import org.ameet.nosql.Application;
import org.ameet.nosql.apimodel.RTSModel;
import org.ameet.nosql.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.google.common.base.Strings;

@ContextConfiguration(classes = { Application.class })
public class BeanPropertyTester extends AbstractTestNGSpringContextTests {
	@Autowired
	private RTSParser parser;
	@Autowired
	private LeafExpander expander;

	@Test
	public void testBeanDescribe() {
		RTSModel rts = parser.parse(Utility.pickupTemplateWellJsonFile());
		Map<String, String> kvMap = expander.flatten(rts);

		for (Entry<String, String> e : kvMap.entrySet()) {
			System.out.println("Key==>" + Strings.padEnd(e.getKey(), 80, ' ') + " Value==>" + e.getValue());
		}
	}
}