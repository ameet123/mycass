package org.ameet.nosql.apimodel.parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.ameet.nosql.apimodel.RTSModel;
import org.ameet.nosql.exception.ParseCode1000;
import org.ameet.nosql.exception.RTSException;
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
}
