package com.bdb.opaloshare.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.IOException;

@Service
public class Utils {

	Logger logger = LoggerFactory.getLogger(getClass());

	public Map<String, String> ObjectToMap(Object params) {

		ObjectMapper mapper = new ObjectMapper();
		String paramStr = null;
		Map<String, String> parametros = null;

		try {
			
			paramStr = mapper.writeValueAsString(params);
			ObjectReader reader = new ObjectMapper().readerFor(Map.class);
			parametros = reader.readValue(paramStr);

			return parametros;

		} catch (JsonProcessingException e) {
			logger.error(Constants.ERR_MSG + e.getMessage());
		} catch (IOException e) {
			logger.error(Constants.ERR_MSG + e.getMessage());
		}

		return parametros;

	}
	
	public Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
				
		return cal.getTime();
	}
	
	
}
