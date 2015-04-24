package org.ameet.nosql.dao;

import org.ameet.nosql.model.Employee;
import org.ameet.nosql.repository.EmpRepository;
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
