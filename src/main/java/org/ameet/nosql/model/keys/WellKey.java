package org.ameet.nosql.model.keys;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

/**
 * primary key for the well object
 * @author achaubal
 *
 */
@PrimaryKeyClass
public class WellKey implements Serializable {

	private static final long serialVersionUID = 2825394575431876616L;

	@PrimaryKeyColumn(name = "rowkey", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String rowKey;

	@PrimaryKeyColumn(name = "wellid", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private UUID wellid;

	public String getRowKey() {
		return rowKey;
	}

	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}

	public UUID getWellid() {
		return wellid;
	}

	public void setWellid(UUID wellid) {
		this.wellid = wellid;
	}
	@Override
	  public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((rowKey == null) ? 0 : rowKey.hashCode());
	    result = prime * result + ((wellid == null) ? 0 : wellid.hashCode());
	    return result;
	  }

	  @Override
	  public boolean equals(Object obj) {
	    if (this == obj)
	      return true;
	    if (obj == null)
	      return false;
	    if (getClass() != obj.getClass())
	      return false;
	    WellKey other = (WellKey) obj;
	    if (rowKey == null) {
	      if (other.rowKey != null)
	        return false;
	    } else if (!wellid.equals(other.wellid))
	      return false;
	    if (wellid == null) {
	      if (other.wellid != null)
	        return false;
	    } else if (!wellid.equals(other.wellid))
	      return false;
	    return true;
	  }
}