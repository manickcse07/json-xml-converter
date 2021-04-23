package com.main;

import com.convertor.ConverterFactory;

public class SampleJSONXMLConversion {

	public static void main(String[] args) {
		
		if(args.length < 2) {
			System.out.println("Missing arguments. Please pass the source JSON file path and Output XML file path");
			System.exit(0);
		}else if(args.length > 2) {
			System.out.println("Incorrect number of arguments. Please pass the source JSON file path and Output XML file path");
			System.exit(0);
		}

		ConverterFactory converterFactory = new ConverterFactory();
		converterFactory.getXMLJSONConvertor().convertJSONtoXML(args[0], args[1]);
		System.out.println("JSON to XML conversion is successful");
	}

}
