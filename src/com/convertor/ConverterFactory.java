package com.convertor;

public class ConverterFactory {

	public XMLJSONCovnerter getXMLJSONConvertor() {
		return new XMLJSONConvertorImpl();
	}
}
