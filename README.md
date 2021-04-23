# json-xml-converter

This project is used to convert JSON to XML

It uses the json-simple.jar opensource Jar to read the input json and the jar is available under the lib direcotry.

Use the below steps to run the project

1) Download and install ant version 1.9.5 from the below path

	https://ant.apache.org/bindownload.cgi

2) Set the JAVA_HOME to Jdk 1.7

3) Update environment variables to include ant in path

4) Clone the repository in your local workspace

5) Run the below command to compile the code

	ant compile
	
6) Run the below commane to create the jar file
	
	ant jar
	
7) Run the below command to run the program

	java -jar build\jar\JSON_XML_Converter.jar (Source Json path) (Output path/file)

	Example:- 
	java -jar build\jar\JSON_XML_Converter.jar C:\input\example.json C:\output\convertedXML.xml
