package org.ameet.nosql.apimodel.parser;

import org.ameet.nosql.Application;
import org.ameet.nosql.apimodel.RTSModel;
import org.ameet.nosql.ingestion.RTSIngestionUtil;
import org.ameet.nosql.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { Application.class })
public class RTSIngestionTest extends AbstractTestNGSpringContextTests {


	@Autowired
	private RTSParser parser;
	@Autowired
	private RTSIngestionUtil ingestion;
	
	@Test
	public void testWellIngestion() {
		
		RTSModel rts = parser.parse(Utility.pickupTemplateWellJsonFile());
		ingestion.ingestWell(rts);
	}

}
