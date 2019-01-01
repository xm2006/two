package com.woc;

import java.io.File;
import java.util.Scanner;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

	

public class qiP {

	private static final String slash= "/";
	
	public static void add(String[] args) throws Exception {
		System.out.println("����ʽ�����û���Ϣ�����硰����/��/24�ꡱ");
		Scanner userInfo=new Scanner(System.in);
		
		String userString=userInfo.next();
		
		int one=userString.indexOf(slash,0);
		int two=userString.indexOf(slash,one+1);
		
		String  addname=userString.substring(0, one);
		String  addsex=userString.substring(one+1, two);
		String  addage=userString.substring(two+1, userString.length());
	
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();//��ȡ DocumentBuilderFactory ����ʵ��
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document doc=builder.parse("src/File.xml");	//����һ���µ� DOM Document ����
		
		NodeList nodeList=doc.getElementsByTagName("users");//ͨ����ǩ���ֻ�ȡ�ڵ㣬�кܶ�users�����Է���һ��list
		Element users=(Element)nodeList.item(nodeList.getLength()-1);  //��ȡ��һ��Ԫ��,��element������ 
		
            
		Element user = doc.createElement("user"); //���user�ڵ�
		Element name=doc.createElement("name");
		Element sex=doc.createElement("sex");
		Element age=doc.createElement("age");
		
		name.appendChild(doc.createTextNode(addname));
		sex.appendChild(doc.createTextNode(addsex));
		age.appendChild(doc.createTextNode(addage));
		
		users.appendChild(user);
		user.appendChild(name);
		user.appendChild(sex);
		user.appendChild(age);
		
	   TransformerFactory tf = TransformerFactory.newInstance();//��ȡ TransformerFactory ����ʵ��������ʹ�ô�ʵ���������Բ�ͬԴ�� XML������ת�����д����ֽ�����
	   Transformer transformer = tf.newTransformer();//�˳������ʵ���ܹ���Դ��ת��Ϊ�����
	   
	   transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");//ָ�����뷽ʽ
	   transformer.setOutputProperty(OutputKeys.INDENT, "yes");//��Ҫ�ո�
	   
	   DOMSource source = new DOMSource(doc);//���Ҫ������ĵ�����Դ
	   StreamResult result = new StreamResult(new File("src/File.xml"));//���Ҫ�����λ��
	   
	   transformer.transform(source,result);
		System.out.println("====================");
	}
}
