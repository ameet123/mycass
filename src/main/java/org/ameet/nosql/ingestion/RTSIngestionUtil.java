package org.ameet.nosql.ingestion;

import java.util.Date;
import java.util.UUID;

import org.ameet.nosql.apimodel.RTSModel;
import org.ameet.nosql.dao.RTSDao;
import org.ameet.nosql.model.Well;
import org.ameet.nosql.model.keys.WellKey;
import org.ameet.nosql.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * an utility class to store the object model into cassandra
 * 
 * @author achaubal
 *
 */
@Component
public class RTSIngestionUtil {

	private final String WELL_KEY = "well";
	
	@Autowired
	private RTSDao dao;
	
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
		dao.save(w);
	}
}