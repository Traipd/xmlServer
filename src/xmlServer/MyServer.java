package xmlServer;

import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class MyServer {
	public static void main(String[] args)
	{
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		
			try
			{
			
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse("Myxml.xml");
			Element root=doc.getDocumentElement();//获取根元素
			NodeList elements=root.getChildNodes();//获取该元素下的子元素列表
			
				for(int i=0;i<elements.getLength();i++)//遍历子元素列表
				{
				
					Node node=elements.item(i);//元素列表的第i个元素
			
					if(node.getNodeType()==Node.ELEMENT_NODE)//如果node是元素节点
					{
							Element e=(Element)node;
							String classE=e.getTagName();
							if(classE.equals("class"))
							{
								NodeList namelist=e.getElementsByTagName("name");//获取指定名字的标签
								Element nameElement1=(Element)namelist.item(0);
								String name=nameElement1.getFirstChild().getNodeValue();//获取标签内容
								
								NodeList methodlist=e.getElementsByTagName("method");//获取指定名字的标签
								Element methodElement1=(Element)methodlist.item(0);
								String method=methodElement1.getFirstChild().getNodeValue();//获取标签内容
								
								Class c=Class.forName("xmlServer."+name);//获取指定类
								Object o=c.newInstance();//构造一个类对象
								
								Method m=c.getDeclaredMethod(method, null);//从类获取指定方法
								m.invoke(o, null);//方法调用
               		         }
							
					}
				}
			
			}catch(Exception e){
				e.printStackTrace();
			}
	
	}

}
