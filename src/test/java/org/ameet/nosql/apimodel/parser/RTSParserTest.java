package org.ameet.nosql.apimodel.parser;

import org.ameet.nosql.Application;
import org.ameet.nosql.apimodel.RTSModel;
import org.ameet.nosql.apimodel.RTSModel.CommonData;
import org.ameet.nosql.apimodel.RTSModel.Easting;
import org.ameet.nosql.apimodel.RTSModel.Header;
import org.ameet.nosql.apimodel.RTSModel.Latitude;
import org.ameet.nosql.apimodel.RTSModel.LocalX;
import org.ameet.nosql.apimodel.RTSModel.LocalY;
import org.ameet.nosql.apimodel.RTSModel.Longitude;
import org.ameet.nosql.apimodel.RTSModel.Northing;
import org.ameet.nosql.apimodel.RTSModel.WellDatum;
import org.ameet.nosql.apimodel.RTSModel.WellLocation;
import org.ameet.nosql.apimodel.RTSModel.WellModel;
import org.ameet.nosql.util.Utility;
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
		RTSModel rts = parser.parse();
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
