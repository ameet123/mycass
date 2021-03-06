package org.ameet.rts.cassandra.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value="employee_table")
public class Employee {
	
	public Employee(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public Employee() {
		
	}
	
	@PrimaryKey
	private int id;
	
	@Column(value="emp_name")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
