package org.ameet.nosql.apimodel.parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import org.ameet.nosql.apimodel.RTSModel;
import org.ameet.nosql.exception.ParseCode1000;
import org.ameet.nosql.exception.RTSException;
import org.ameet.nosql.model.ObjectLeaf;
import org.ameet.nosql.model.ParsedOutput;
import org.ameet.nosql.model.Well;
import org.ameet.nosql.model.keys.ObjectLeafKey;
import org.ameet.nosql.model.keys.WellKey;
import org.ameet.nosql.services.GsonCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.io.Files;

/**
 * parse rts model file into an object from JSON 
 * 
 * @author achaubal
 *
 */
@Component
public class RTSParser {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RTSParser.class);
	
	@Autowired
	private GsonCreator gson;
	@Autowired
	private LeafExpander flattener;
	
	private static final String WELL_KEY = "well";
	
	public RTSModel parse(File rtsFile) {
		String s = null;
		try {
			s = Files.toString(rtsFile, Charset.defaultCharset());
		} catch (IOException e) {
			throw new RTSException(ParseCode1000.FILE_TO_STRING_ERROR);
		}
		// now try to parse it into model
		RTSModel rts = gson.get().fromJson(s, RTSModel.class);
		LOGGER.info("rts model parsed:{}", rts.getRtsMessage().hashCode());
		return rts;
	}
	public ParsedOutput toWellEntities(File f) {
		return toWellEntities(parse(f));
	}
	/**
	 * parse the {@link RTSModel} and generate {@link ParsedOutput} class
	 * which will give all the database entities for saving via DAO
	 * @param rts
	 * @return
	 */
	public ParsedOutput toWellEntities(RTSModel rts) {
		ParsedOutput po = new ParsedOutput();
		// well object
		Well w = new Well();
		WellKey wk = new WellKey();
		wk.setRowKey(WELL_KEY);
		wk.setWellid(rts.getWellId());
		w.setPk(wk);
		w.setLastChange(rts.getTimLastChange());
		w.setNumApi(rts.getRtsMessage().getBody().getWell().getNumAPI());
		w.setNumGovt(rts.getRtsMessage().getBody().getWell().getName());
		w.setWellName(rts.getRtsMessage().getBody().getWell().getName());
		// save
		po.setWell(w);
		
		Map<String, Object> kvMap = flattener.flatten(rts);
		List<ObjectLeaf> leaves = new ArrayList<ObjectLeaf>();
		for (Entry<String, Object> e : kvMap.entrySet()) {
			UUID wellid = UUID.fromString(rts.getRtsMessage().getBody().getWell().getUid());
			ObjectLeafKey pk = new ObjectLeafKey();
			pk.setLeaf(e.getKey());
			pk.setObjectId(wellid);
			pk.setSize("l");
			ObjectLeaf ol = new ObjectLeaf();
			ol.setPk(pk);
			ol.setObjectKlass(rts.getRtsMessage().getHeader().getObjectKlass());
			ol.setValue(e.getValue().toString());
			ol.setLeafKlass(e.getValue().getClass().toString());
			ol.setWellId(wellid);
			leaves.add(ol);
		}
		po.setObjectLeafs(leaves);
		
		// Done
		return po;
	}
}
