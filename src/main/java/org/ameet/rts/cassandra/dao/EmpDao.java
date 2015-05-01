package org.ameet.rts.cassandra.dao;

import org.ameet.rts.cassandra.model.Employee;
import org.ameet.rts.cassandra.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpDao {
	
	@Autowired
	private EmpRepository repo;
	public Employee save(Employee emp) {
		return repo.save(emp);
	}
}
