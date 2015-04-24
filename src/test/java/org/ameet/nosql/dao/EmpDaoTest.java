package org.ameet.nosql.dao;

import org.ameet.nosql.Application;
import org.ameet.nosql.model.Employee;
import org.ameet.nosql.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes={Application.class})
public class EmpDaoTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private EmpDao dao;
	
	@Autowired
	private EmpRepository repo;
	@Autowired
	private CassandraOperations cassandraOperations;
	
	@Test
	public void testSave() {
		Employee e = new Employee();
		e.setId(1);
		e.setName("Ameet");
		dao.save(e);
		long cnt = cassandraOperations.count("employee_table");
		System.out.println("Records in emp table:"+cnt);
	}
}
