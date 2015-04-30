package org.ameet.nosql.dao;

import org.ameet.nosql.Application;
import org.ameet.nosql.apimodel.RTSModel;
import org.ameet.nosql.apimodel.parser.LeafExpander;
import org.ameet.nosql.apimodel.parser.RTSParser;
import org.ameet.nosql.dml.RTSIngestion;
import org.ameet.nosql.util.Utility;
import org.ameet.nosql.verification.PreProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes={Application.class})
public class ObjectLeafDaoTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private RTSDao dao;
	@Autowired
	private RTSParser parser;
	@Autowired
	private PreProcessor pre;
	@Autowired
	private LeafExpander expander;
	@Autowired
	private RTSIngestion ingestion;

	@Test
	public void testUsingIngestion() {
		RTSModel rts = parser.parse(Utility.pickupTemplateWellJsonFile());
		ingestion.insertWellRelated(rts);		
	}
}