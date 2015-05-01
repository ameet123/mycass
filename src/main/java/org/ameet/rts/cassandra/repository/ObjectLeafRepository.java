package org.ameet.rts.cassandra.repository;

import org.ameet.rts.cassandra.model.ObjectLeaf;
import org.ameet.rts.cassandra.model.keys.ObjectLeafKey;
import org.springframework.data.repository.CrudRepository;

public interface ObjectLeafRepository extends CrudRepository<ObjectLeaf, ObjectLeafKey> {

}
