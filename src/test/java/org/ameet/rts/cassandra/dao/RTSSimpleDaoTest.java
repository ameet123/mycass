package org.ameet.rts.cassandra.dao;

import java.util.Date;
import java.util.UUID;

import org.ameet.rts.cassandra.Application;
import org.ameet.rts.cassandra.dao.RTSDao;
import org.ameet.rts.cassandra.model.Well;
import org.ameet.rts.cassandra.model.keys.WellKey;
import org.ameet.rts.cassandra.verification.PreProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes={Application.class})
public class RTSSimpleDaoTest extends AbstractTestNGSpringContextTests {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RTSSimpleDaoTest.class);
	
	@Autowired
	private RTSDao dao;
	@Autowired
	private CassandraOperations cassandraOperations;
	@Autowired
	private PreProcessor pre;
	
	private final String WELL_ID_TEST = "676a12e0-b24e-5d51-eb13-d4824a4d9c58";
	@Test
	public void testSave() {
		Well w = new Well();
		WellKey wk = new WellKey();
		wk.setRowKey("well");
		wk.setWellid(UUID.fromString("676a12e0-b24e-5d51-eb13-d4824a4d9c58"));
		w.setPk(wk);
		w.setLastChange(new Date());
		w.setNumApi("Ameet's API");
		w.setNumGovt("US Govt");
		w.setWellName("Transocean well");
		
		dao.save(w);
		
	}
	@Test
	public void testCountOperation() {
		long cnt = cassandraOperations.count("well");
		System.out.println("Records in well table:"+cnt);
	}
	@Test
	public void testTableExistence() {
		pre.checkWellExistence();
	}
	@Test
	public void testPkFetch() {
		WellKey pk = new WellKey();
		pk.setRowKey("well");
		pk.setWellid(UUID.fromString(WELL_ID_TEST));
		Well w = dao.findOne(pk);
		LOGGER.info("Well fetched from db:{}", w.getWellName());
	}
}