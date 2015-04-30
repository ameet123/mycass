package org.ameet.nosql.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.ameet.nosql.Application;
import org.ameet.nosql.apimodel.RTSModel;
import org.ameet.nosql.apimodel.parser.LeafExpander;
import org.ameet.nosql.apimodel.parser.RTSParser;
import org.ameet.nosql.model.ObjectLeaf;
import org.ameet.nosql.model.keys.ObjectLeafKey;
import org.ameet.nosql.util.Utility;
import org.ameet.nosql.verification.PreProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.google.common.collect.Iterators;

@ContextConfiguration(classes={Application.class})
public class ObjectLeafDaoTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private RTSDao dao;
	@Autowired
	private RTSParser parser;
	@Autowired
	private PreProcessor pre;
	@Autowired
	private LeafExpander expander;
	

	@Test
	public void testSave() {
		RTSModel rts = parser.parse(Utility.pickupTemplateWellJsonFile());
		Map<String, Object> kvMap = expander.flatten(rts);
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
			ol.setWellId(wellid);
			leaves.add(ol);
		}
		System.out.println("Adding object leaf records # "+leaves.size());
		Iterable<ObjectLeaf> saved = dao.saveObjectLeaf(leaves);
		Iterator<ObjectLeaf> i = saved.iterator();
		int cnt = Iterators.size(i);
		System.out.println("Obejct Leaf records saved # "+cnt);
		
	}
}