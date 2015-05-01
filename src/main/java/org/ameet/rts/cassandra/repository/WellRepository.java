package org.ameet.rts.cassandra.repository;

import org.ameet.rts.cassandra.model.Well;
import org.ameet.rts.cassandra.model.keys.WellKey;
import org.springframework.data.repository.CrudRepository;

public interface WellRepository extends CrudRepository<Well, WellKey> {

}
