package com.quinnox.qsmr.util;

import java.util.StringTokenizer;

public class QSMRUtil {
	
	
	public static String[] parseValue(String value){
		System.out.println("parseValue------------start" + value);
		String splitLine[] =null;
		System.out.println("value :::::::::: " + value);
		splitLine  = value.split("\\\\r\\\\n");
		System.out.println("splitLine :::::::::: " + splitLine);
		for (String element : splitLine) {
		    System.out.println("---------" + element);
		}
		System.out.println("parseValue------------end" + splitLine.length);
		return splitLine;
	}
	/*public static String replaceComma(String value){
		System.out.println("value ---------------" +value);
		String commaSeparatedValue = "";
		if(!(value.isEmpty())&&(value!=null)){
				commaSeparatedValue = value.replaceAll(",", "#");
				System.out.println("commaSeparatedValue +++++++++++++ " +commaSeparatedValue);
			}
			return commaSeparatedValue;
	}*/
	public static String replaceCommaWithHash(String value){
		System.out.println("value ---------------" +value);
		String commaSeparatedValue = "";
		if(!(value.isEmpty())&&(value!=null)){
				commaSeparatedValue = value.replaceAll(",", "#");
				System.out.println("commaSeparatedValue +++++++++++++ " +commaSeparatedValue);
			}
			return commaSeparatedValue;
	}
	
	public static String replaceHashWithComma(String value){
		System.out.println("value ---------------" +value);
		String commaSeparatedValue = "";
		if(!(value.isEmpty())&&(value!=null)){
				commaSeparatedValue = value.replaceAll("#", ",");
				System.out.println("commaSeparatedValue ********************** " +commaSeparatedValue);
			}
			return commaSeparatedValue;
	}

}
