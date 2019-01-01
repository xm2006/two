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
		System.out.println("按格式输入用户信息，例如“李明/男/24岁”");
		Scanner userInfo=new Scanner(System.in);
		
		String userString=userInfo.next();
		
		int one=userString.indexOf(slash,0);
		int two=userString.indexOf(slash,one+1);
		
		String  addname=userString.substring(0, one);
		String  addsex=userString.substring(one+1, two);
		String  addage=userString.substring(two+1, userString.length());
	
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();//获取 DocumentBuilderFactory 的新实例
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document doc=builder.parse("src/File.xml");	//返回一个新的 DOM Document 对象
		
		NodeList nodeList=doc.getElementsByTagName("users");//通过标签名字获取节点，有很多users，所以返回一个list
		Element users=(Element)nodeList.item(nodeList.getLength()-1);  //获取第一个元素,是element的子类 
		
            
		Element user = doc.createElement("user"); //添加user节点
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
		
	   TransformerFactory tf = TransformerFactory.newInstance();//获取 TransformerFactory 的新实例，可以使用此实例处理来自不同源的 XML，并将转换输出写入各种接收器
	   Transformer transformer = tf.newTransformer();//此抽象类的实例能够将源树转换为结果树
	   
	   transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");//指定编码方式
	   transformer.setOutputProperty(OutputKeys.INDENT, "yes");//需要空格
	   
	   DOMSource source = new DOMSource(doc);//获得要保存的文档对象源
	   StreamResult result = new StreamResult(new File("src/File.xml"));//获得要保存的位置
	   
	   transformer.transform(source,result);
		System.out.println("====================");
	}
}
