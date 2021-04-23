package com.convertor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.xml.handler.JSONXMLHandler;

public class XMLJSONConvertorImpl implements XMLJSONCovnerter{
	
	private static final String DEFAULT_FILE_NAME = "Output.xml";

	/**
	 *
	 */
	@Override
	public void convertJSONtoXML(String jsonFilePath, String xmlFilePath) {
		
		JSONXMLHandler handler = new JSONXMLHandler();
		FileReader jsonReader;
		try {
			File outputFile = new File(xmlFilePath);
			if(outputFile.isDirectory()) {
				xmlFilePath+= "/"+DEFAULT_FILE_NAME;
			}
			jsonReader = new FileReader(jsonFilePath);
			JSONParser parser = new JSONParser();
			parser.parse(jsonReader, handler, true);
			FileWriter xmlWriter = new FileWriter(xmlFilePath);
			xmlWriter.write(handler.getXmlBuffer().toString());
			xmlWriter.close();
			jsonReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
