package org.ameet.rts.cassandra.model;

import java.util.UUID;

import org.ameet.rts.cassandra.model.keys.ObjectLeafKey;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value="object_leaf")
public class ObjectLeaf {
	
	@PrimaryKey
	private ObjectLeafKey pk;
	
	@Column(value="leaf_klass")
	private String leafKlass;

	@Column(value="object_klass")
	private String objectKlass;
	
	@Column(value="value")
	private String value;
	
	@Column(value="wellboreid")
	private UUID wellboreId;

	@Column(value="wellid")
	private UUID wellId;

	public ObjectLeafKey getPk() {
		return pk;
	}

	public void setPk(ObjectLeafKey pk) {
		this.pk = pk;
	}

	public String getLeafKlass() {
		return leafKlass;
	}

	public void setLeafKlass(String leafKlass) {
		this.leafKlass = leafKlass;
	}

	public String getObjectKlass() {
		return objectKlass;
	}

	public void setObjectKlass(String objectKlass) {
		this.objectKlass = objectKlass;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public UUID getWellboreId() {
		return wellboreId;
	}

	public void setWellboreId(UUID wellboreId) {
		this.wellboreId = wellboreId;
	}

	public UUID getWellId() {
		return wellId;
	}

	public void setWellId(UUID wellId) {
		this.wellId = wellId;
	}	
}