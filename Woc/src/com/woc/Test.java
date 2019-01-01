package com.woc;

import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Test {
	public static void lala(String[] args) throws Exception {
		DocumentBuilderFactory fac=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=fac.newDocumentBuilder();
		Document doc=builder.newDocument();
		Element school=doc.createElement("school");
		Element student=doc.createElement("student");
		Element name=doc.createElement("name");
		Element age=doc.createElement("age");
		name.appendChild(doc.createTextNode("tom"));
		age.appendChild(doc.createTextNode("23"));
		
		student.appendChild(name);
		student.appendChild(age);
		
		school.appendChild(student);
		doc.appendChild(school);
		
		TransformerFactory tff=TransformerFactory.newInstance();
		Transformer former=tff.newTransformer();
		
		former.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		former.setOutputProperty(OutputKeys.INDENT, "yes");
		
		DOMSource source=new DOMSource(doc);
		PrintWriter pw=new PrintWriter("d:/temp.xml");
		StreamResult result=new StreamResult(pw);
		former.transform(source, result);
	}
}
