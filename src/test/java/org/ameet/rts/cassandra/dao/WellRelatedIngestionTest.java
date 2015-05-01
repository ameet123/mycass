package org.ameet.rts.cassandra.dao;

import org.ameet.rts.cassandra.Application;
import org.ameet.rts.cassandra.apimodel.RTSModel;
import org.ameet.rts.cassandra.apimodel.parser.LeafExpander;
import org.ameet.rts.cassandra.apimodel.parser.RTSParser;
import org.ameet.rts.cassandra.dao.RTSDao;
import org.ameet.rts.cassandra.dml.RTSIngestion;
import org.ameet.rts.cassandra.util.Utility;
import org.ameet.rts.cassandra.verification.PreProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes={Application.class})
public class WellRelatedIngestionTest extends AbstractTestNGSpringContextTests {
	
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