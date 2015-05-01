package org.ameet.rts.cassandra.repository;

import org.ameet.rts.cassandra.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmpRepository extends CrudRepository<Employee, Integer> {

}
