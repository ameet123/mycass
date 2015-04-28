package org.ameet.nosql.dao;

import org.ameet.nosql.model.Well;
import org.ameet.nosql.repository.WellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RTSDao {
	
	@Autowired
	private WellRepository repo;
	public Well save(Well w) {
		return repo.save(w);
	}
}
