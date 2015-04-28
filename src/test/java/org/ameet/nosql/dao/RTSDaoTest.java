package org.ameet.nosql.dao;

import java.util.Date;
import java.util.UUID;

import org.ameet.nosql.Application;
import org.ameet.nosql.model.Employee;
import org.ameet.nosql.model.Well;
import org.ameet.nosql.model.keys.WellKey;
import org.ameet.nosql.verification.PreProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes={Application.class})
public class RTSDaoTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private RTSDao dao;
	@Autowired
	private CassandraOperations cassandraOperations;
	@Autowired
	private PreProcessor pre;
	
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
//	@Test
	public void testInsertEntityOperation() {
		cassandraOperations.insert(new Employee(2, "pope"));
		long cnt = cassandraOperations.count("employee_table");
		System.out.println("Records in emp table:"+cnt);
	}
//	@Test
	public void testSelect() {
		pre.checkExistence();
	}
}