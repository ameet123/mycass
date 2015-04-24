package org.ameet.nosql.repository;

import org.ameet.nosql.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmpRepository extends CrudRepository<Employee, Integer> {

}
