package org.ameet.nosql.repository;

import org.ameet.nosql.model.ObjectLeaf;
import org.ameet.nosql.model.keys.ObjectLeafKey;
import org.springframework.data.repository.CrudRepository;

public interface ObjectLeafRepository extends CrudRepository<ObjectLeaf, ObjectLeafKey> {

}
