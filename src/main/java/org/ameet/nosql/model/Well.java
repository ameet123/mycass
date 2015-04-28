package org.ameet.nosql.model;

import java.util.Date;

import org.ameet.nosql.model.keys.WellKey;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value="well")
public class Well {
	
	@PrimaryKey
	private WellKey pk;
	
	@Column(value="well_name")
	private String wellName;

	@Column(value="num_govt")
	private String numGovt;
	
	@Column(value="num_api")
	private String numApi;
	
	@Column(value="dtimlastchange")
	private Date lastChange;

	public WellKey getPk() {
		return pk;
	}

	public void setPk(WellKey pk) {
		this.pk = pk;
	}

	public String getWellName() {
		return wellName;
	}

	public void setWellName(String wellName) {
		this.wellName = wellName;
	}

	public String getNumGovt() {
		return numGovt;
	}

	public void setNumGovt(String numGovt) {
		this.numGovt = numGovt;
	}

	public String getNumApi() {
		return numApi;
	}

	public void setNumApi(String numApi) {
		this.numApi = numApi;
	}

	public Date getLastChange() {
		return lastChange;
	}

	public void setLastChange(Date lastChange) {
		this.lastChange = lastChange;
	}
	
}