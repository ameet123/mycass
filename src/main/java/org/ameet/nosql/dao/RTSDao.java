package org.ameet.nosql.dao;

import java.util.Date;
import java.util.UUID;

import org.ameet.nosql.apimodel.RTSModel;
import org.ameet.nosql.exception.Metadata1100;
import org.ameet.nosql.exception.RTSException;
import org.ameet.nosql.model.ObjectLeaf;
import org.ameet.nosql.model.Well;
import org.ameet.nosql.model.keys.WellKey;
import org.ameet.nosql.repository.ObjectLeafRepository;
import org.ameet.nosql.repository.WellRepository;
import org.ameet.nosql.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

@Component
public class RTSDao {
	private final String WELL_KEY = "well";

	@Autowired
	private WellRepository repo;
	@Autowired
	private ObjectLeafRepository objectLeafRepo;

	public Well save(Well w) {
		validateWell(w);
		return repo.save(w);
	}

	public ObjectLeaf saveObjectLeaf(ObjectLeaf ol) {
		validateObjectLeaf(ol);
		return objectLeafRepo.save(ol);
	}

	public Well findOne(WellKey pk) {
		validateWell(pk);
		return repo.findOne(pk);
	}

	/**
	 * based on the model generate the well object and stuff it into cassandra
	 * 
	 * @param rts
	 */
	public void ingestWell(RTSModel rts) {
		Well w = new Well();
		String wellName = rts.getRtsMessage().getBody().getWell().getName();
		String wellId = rts.getRtsMessage().getBody().getWell().getUid();
		String numApi = rts.getRtsMessage().getBody().getWell().getNumAPI();
		String numGovt = rts.getRtsMessage().getBody().getWell().getNumGovt();
		Date dTimeLastChange = Utility.convertUtcToDate(rts.getRtsMessage().getBody().getWell().getCommonData()
				.getdTimLastChange());
		UUID welluuid = UUID.fromString(wellId);
		WellKey wk = new WellKey();
		wk.setRowKey(WELL_KEY);
		wk.setWellid(welluuid);
		// set the entity
		w.setPk(wk);
		w.setNumApi(numApi);
		w.setNumGovt(numGovt);
		w.setWellName(wellName);
		w.setLastChange(dTimeLastChange);
		// now do the insert into cassandra
		save(w);
	}

	private void validateWell(Well w) {
		validateWell(w.getPk());
	}

	private void validateWell(WellKey pk) {
		if (pk == null || Strings.isNullOrEmpty(pk.getRowKey()) || pk.getWellid() == null) {
			throw new RTSException(Metadata1100.ILLEGAL_WELL_PK);
		}
	}

	private void validateObjectLeaf(ObjectLeaf ol) {
		if (ol.getPk() == null || Strings.isNullOrEmpty(ol.getPk().getLeaf())
				|| Strings.isNullOrEmpty(ol.getPk().getSize()) || ol.getPk().getObjectId() == null) {
			throw new RTSException(Metadata1100.ILLEGAL_OBJECT_LEAF_PK);
		}
	}
}
