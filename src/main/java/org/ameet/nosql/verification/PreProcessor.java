package org.ameet.nosql.verification;

import org.ameet.nosql.exception.Metadata1100;
import org.ameet.nosql.exception.RTSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

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
	@Value("${rts.schema.table.well}")
	private String wellTable;

	@Value("${cassandra.keyspace}")
	private String keyspace;

	public void checkExistence(String table) {
		if (Strings.isNullOrEmpty(table)) {
			throw new RTSException(Metadata1100.ILLEGAL_TABLE_NAME);
		}
		String t = cassandraOperations.queryForObject(
				"SELECT columnfamily_name FROM system.schema_columnfamilies WHERE keyspace_name=" + "'" + keyspace
						+ "' and columnfamily_name='" + table + "'", String.class);
		LOGGER.info("table length:{} query result leng:{} table:{} query:{}", table.length(), t.length(), table, t);
		if (table.equalsIgnoreCase(t)) {
			throw new RTSException(Metadata1100.TABLE_NOT_EXIST).set("table", table);
		}
	}
	public void checkWellExistence() {
		checkExistence(wellTable);
	}
}
