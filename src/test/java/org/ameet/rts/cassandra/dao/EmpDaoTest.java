package org.ameet.rts.cassandra.dao;

import org.ameet.rts.cassandra.Application;
import org.ameet.rts.cassandra.dao.EmpDao;
import org.ameet.rts.cassandra.model.Employee;
import org.ameet.rts.cassandra.repository.EmpRepository;
import org.ameet.rts.cassandra.verification.PreProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes={Application.class})
public class EmpDaoTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private EmpDao dao;
	@Value("${my.table}")
	private String table;
	
	@Autowired
	private EmpRepository repo;
	@Autowired
	private CassandraOperations cassandraOperations;
	@Autowired
	private PreProcessor pre;
	
//	@Test
	public void testSave() {
		Employee e = new Employee();
		e.setId(1);
		e.setName("Ameet");
		dao.save(e);
		
	}
//	@Test
	public void testCountOperation() {
		long cnt = cassandraOperations.count("employee_table");
		System.out.println("Records in emp table:"+cnt);
	}
//	@Test
	public void testInsertEntityOperation() {
		cassandraOperations.insert(new Employee(2, "pope"));
		long cnt = cassandraOperations.count("employee_table");
		System.out.println("Records in emp table:"+cnt);
	}
	@Test
	public void testSelect() {
		pre.checkExistence(table);
	}
}