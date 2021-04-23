package com.xml.handler;

import java.io.IOException;
import java.util.Stack;

import org.json.simple.parser.ContentHandler;
import org.json.simple.parser.ParseException;

public class JSONXMLHandler implements ContentHandler {
	
	StringBuffer xmlBuffer = new StringBuffer("");
	Stack<String> nameStack = new Stack<String>();
	Stack<String> typeStack = new Stack<String>();

	@Override
	public boolean endArray() throws ParseException, IOException {
		xmlBuffer.append("</array>");
		typeStack.pop();
		return true;
	}

	@Override
	public void endJSON() throws ParseException, IOException {		
	}

	@Override
	public boolean endObject() throws ParseException, IOException {
		xmlBuffer.append("</object>");
		nameStack.pop();
		typeStack.pop();
		return true;
	}

	@Override
	public boolean endObjectEntry() throws ParseException, IOException {
		typeStack.pop();
		return true;
	}

	public boolean primitive(Object value) throws ParseException, IOException {
		if (typeStack.peek().equals("objectEntry")) {
			if (value instanceof String) {
				xmlBuffer.append("<string name=\"").append(nameStack.peek()).append("\">").append(value)
						.append("</string>");
			} else if (value instanceof Boolean) {
				xmlBuffer.append("<boolean name=\"").append(nameStack.peek()).append("\">").append(value)
						.append("</boolean>");
			} else if (value == null) {
				xmlBuffer.append("<null name=\"").append(nameStack.peek()).append("\"/>");
			} else if (value instanceof Long || value instanceof Double) {
				xmlBuffer.append("<number name=\"" + nameStack.peek()).append("\">").append(value).append("</number>");
			}
		} else {
			if (value instanceof String) {
				xmlBuffer.append("<string>").append(value).append("</string>");
			} else if (value instanceof Boolean) {
				xmlBuffer.append("<boolean>").append(value).append("</boolean>");
			} else if (value == null) {
				xmlBuffer.append("<null />");
			} else if (value instanceof Long || value instanceof Double) {
				xmlBuffer.append("<number>").append(value).append("</number>");
			}
		}
		return true;
	}

	@Override
	public boolean startArray() throws ParseException, IOException {
		if (typeStack.isEmpty()) {
			xmlBuffer = new StringBuffer("<array>");
		} else if (!typeStack.isEmpty() && typeStack.peek().equals("array")) {
			xmlBuffer.append("<array>");
		} else {
			xmlBuffer.append("<array name=\"").append(nameStack.peek()).append("\">");
		}
		typeStack.push("array");
		return true;
	}

	@Override
	public void startJSON() throws ParseException, IOException {
		xmlBuffer.append("<object>");
	}

	@Override
	public boolean startObject() throws ParseException, IOException {
		if (!nameStack.isEmpty() && !typeStack.isEmpty() && !typeStack.peek().equals("array")) {
			xmlBuffer.append("<object name=\"").append(nameStack.peek()).append("\">");
		} else if (!typeStack.isEmpty() && typeStack.peek().equals("array")) {
			xmlBuffer.append("<object>");
		}
		typeStack.push("object");

		return true;
	}

	@Override
	public boolean startObjectEntry(String key) throws ParseException, IOException {
		nameStack.push(key);
		typeStack.push("objectEntry");
		return true;
	}
	
	public StringBuffer getXmlBuffer() {
		return xmlBuffer;
	}
}
