package org.ameet.nosql.model.keys;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

/**
 * primary key for the  object_leaf
 * @author achaubal
 *
 */
@PrimaryKeyClass
public class ObjectLeafKey implements Serializable {

	private static final long serialVersionUID = 2825394575431876616L;

	@PrimaryKeyColumn(name = "objectid", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private UUID objectId;

	@PrimaryKeyColumn(name = "size", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private String size;
	
	@PrimaryKeyColumn(name = "leaf", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
	private String leaf;

	@Override
	  public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((objectId == null) ? 0 : objectId.hashCode());
	    result = prime * result + ((size == null) ? 0 : size.hashCode());
	    result = prime * result + ((leaf == null) ? 0 : size.hashCode());
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
	    ObjectLeafKey other = (ObjectLeafKey) obj;
	    if (objectId == null) {
	      if (other.objectId != null)
	        return false;
	    } else if (!size.equals(other.size))
	      return false;
	    
	    if (leaf == null) {
		      if (other.leaf != null)
		        return false;
		    } else if (!leaf.equals(other.leaf))
		      return false;
	    
	    if (size == null) {
	      if (other.size != null)
	        return false;
	    } else if (!size.equals(other.size))
	      return false;
	    return true;
	  }

	public UUID getObjectId() {
		return objectId;
	}

	public void setObjectId(UUID objectId) {
		this.objectId = objectId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}