package org.ameet.rts.cassandra.apimodel.parser;

import org.ameet.rts.cassandra.Application;
import org.ameet.rts.cassandra.apimodel.RTSModel;
import org.ameet.rts.cassandra.apimodel.RTSModel.Header;
import org.ameet.rts.cassandra.apimodel.RTSModel.Body.WellModel;
import org.ameet.rts.cassandra.apimodel.RTSModel.Body.WellModel.CommonData;
import org.ameet.rts.cassandra.apimodel.RTSModel.Body.WellModel.WellDatum;
import org.ameet.rts.cassandra.apimodel.RTSModel.Body.WellModel.WellLocation;
import org.ameet.rts.cassandra.apimodel.RTSModel.Body.WellModel.WellLocation.Easting;
import org.ameet.rts.cassandra.apimodel.RTSModel.Body.WellModel.WellLocation.Latitude;
import org.ameet.rts.cassandra.apimodel.RTSModel.Body.WellModel.WellLocation.LocalX;
import org.ameet.rts.cassandra.apimodel.RTSModel.Body.WellModel.WellLocation.LocalY;
import org.ameet.rts.cassandra.apimodel.RTSModel.Body.WellModel.WellLocation.Longitude;
import org.ameet.rts.cassandra.apimodel.RTSModel.Body.WellModel.WellLocation.Northing;
import org.ameet.rts.cassandra.apimodel.parser.RTSParser;
import org.ameet.rts.cassandra.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { Application.class })
public class RTSParserTest extends AbstractTestNGSpringContextTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(RTSParserTest.class);

	@Autowired
	private RTSParser parser;

	
	
	@Test
	public void testFilePickup() {
		
		RTSModel rts = parser.parse(Utility.pickupTemplateWellJsonFile());
		Header hdr = rts.getRtsMessage().getHeader();
		LOGGER.info("corr ID:{} params:{}", hdr.getCorrelationId(), hdr.getParameters());
		WellModel w = rts.getRtsMessage().getBody().getWell();
		for (WellDatum wd : w.getWellDatum()) {
			LOGGER.info("name:{} code:{}, elevation:{ uom:{}, Value:{} }", wd.getName(), wd.getCode(), wd
					.getElevation().getUom(), wd.getElevation().getValue());
		}
		LOGGER.info("d time spud:{}", Utility.convertUtc(w.getdTimSpud()));
		LOGGER.info("ground elevation:{ datum:{}, uom:{}, value:{} }", w.getGroundElevation().getDatum(), w
				.getGroundElevation().getUom(), w.getGroundElevation().getValue());
		LOGGER.info("Water depth:{}", w.getWaterDepth().getUom());
		for (WellLocation wl : w.getWellLocation()) {
			Latitude lt = wl.getLatitude();
			Longitude lg = wl.getLongitude();
			Easting es = wl.getEasting();
			Northing nt = wl.getNorthing();
			LocalX x = wl.getLocalX();
			LocalY y = wl.getLocalY();
			LOGGER.info("Latitude:{ uom:{}, Value:{} }", lt.getUom(), lt.getValue());
			LOGGER.info("Longitude:{ uom:{}, Value:{} }", lg.getUom(), lg.getValue());
			LOGGER.info("Easting:{ uom:{}, Value:{} }", es.getUom(), es.getValue());
			LOGGER.info("Northing:{ uom:{}, Value:{} }", nt.getUom(), nt.getValue());
			LOGGER.info("LocalX:{ uom:{}, Value:{} }", x.getUom(), x.getValue());
			LOGGER.info("LocalY:{ uom:{}, Value:{} }", y.getUom(), y.getValue());
		}
		CommonData cd = w.getCommonData();
		LOGGER.info("dTimeLastChange:{} defaultDatum:{ uidRef:{}, value:{} }", Utility.convertUtc(cd
				.getdTimLastChange()), cd.getDefaultDatum().getUidRef(), cd.getDefaultDatum().getValue());
		LOGGER.info("well UID:{}", w.getUid());
	}

}
