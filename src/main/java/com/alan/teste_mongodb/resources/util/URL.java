package com.alan.teste_mongodb.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {
	
	public static String decodeParam(String text) {
		try {
			
			return URLDecoder.decode(text, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static Date converterData(String textoData, Date defaultData) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(textoData);
		} catch (ParseException e) {
			return defaultData;
		}
		
	}

}
