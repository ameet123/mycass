package org.ameet.nosql.verification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * ensure that tables exist
 * 
 * @author achaubal
 *
 */
@Service
public class PreProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(PreProcessor.class);

	@Autowired
	private CassandraOperations cassandraOperations;
	@Value("${my.table}")
	private String table;

	@Value("${cassandra.keyspace}")
	private String keyspace;

	public void checkExistence() {

		String t = cassandraOperations.queryForObject(
				"SELECT columnfamily_name FROM system.schema_columnfamilies WHERE keyspace_name=" + "'" + keyspace
						+ "' and columnfamily_name='" + table + "'", String.class);
		LOGGER.info("table length:{} query result leng:{} table:{} query:{}", table.length(), t.length(), table, t);
		Assert.isTrue(table.equals(t));
	}
}
