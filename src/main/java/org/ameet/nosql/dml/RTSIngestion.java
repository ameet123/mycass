package org.ameet.nosql.dml;

import java.util.Iterator;

import org.ameet.nosql.apimodel.RTSModel;
import org.ameet.nosql.apimodel.parser.LeafExpander;
import org.ameet.nosql.apimodel.parser.RTSParser;
import org.ameet.nosql.dao.RTSDao;
import org.ameet.nosql.model.ObjectLeaf;
import org.ameet.nosql.model.ParsedOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Iterators;

/**
 * a convenience class to perform actual cassandra 
 * @author achaubal
 *
 */
@Component
public class RTSIngestion {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RTSIngestion.class);
	
	@Autowired
	private RTSDao dao;

	@Autowired
	private LeafExpander expander;
	@Autowired
	private RTSParser parser;
	
	
	public void insertWellRelated(RTSModel rts) {
		Stopwatch st = Stopwatch.createStarted();
		ParsedOutput po = parser.toWellEntities(rts);
		
		
		LOGGER.info(">>> Created well related and leaf objects in : {}", st);
		
		st.reset().start();
		Iterable<ObjectLeaf> saved = dao.saveObjectLeaf(po.getObjectLeafs());
		LOGGER.info(">>> Records saved in :{}", st);
		Iterator<ObjectLeaf> i = saved.iterator();
		int cnt = Iterators.size(i);
		LOGGER.info(">>> Incoming records:{} Obejct Leaf records saved # {}, time to save:{}", po.getObjectLeafs().size(), cnt, st);
		st.reset().start();
		
		// save well
		dao.save(po.getWell());
		LOGGER.info(">>> saved well object to db in {}", st);
	}
}
