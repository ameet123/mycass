package org.ameet.nosql.repository;

import org.ameet.nosql.model.Well;
import org.ameet.nosql.model.keys.WellKey;
import org.springframework.data.repository.CrudRepository;

public interface WellRepository extends CrudRepository<Well, WellKey> {

}
