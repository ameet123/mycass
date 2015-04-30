package org.ameet.nosql.model;

import java.util.List;

/**
 * a data structure storage class to present all the various database model
 * entities from a single RTSModel object parsed from a json file
 * Based on this, the DML clients can get what they need and perform the inserts
 * @author achaubal
 *
 */
public class ParsedOutput {

	private Well well;
	private List<ObjectLeaf> objectLeafs;

	
	public List<ObjectLeaf> getObjectLeafs() {
		return objectLeafs;
	}
	public void setObjectLeafs(List<ObjectLeaf> objectLeafs) {
		this.objectLeafs = objectLeafs;
	}
	public Well getWell() {
		return well;
	}
	public void setWell(Well well) {
		this.well = well;
	}
	
	
}
