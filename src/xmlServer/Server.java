package xmlServer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class Server {
	//DOM XML Parser解析
	public static void main(String[] args)
	{
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		
			try
			{
				int count=0;
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse("student.xml");
			Element root=doc.getDocumentElement();//获取根元素
			NodeList elements=root.getChildNodes();//获取该元素下的子元素列表
				for(int i=0;i<elements.getLength();i++)
				{
				
					Node node=elements.item(i);//元素列表的第i个元素
			
					if(node.getNodeType()==Node.ELEMENT_NODE)//如果node是元素节点
					{
							Element e=(Element)node;
							String name=e.getTagName();
							if(name.equals("student"))
							{
								count++;
						
								Element newElement=doc.createElement("hobby");
								Text text=doc.createTextNode("football");
								
								newElement.appendChild(text);
								e.appendChild(newElement);
								NodeList names1=e.getElementsByTagName("hobby");
								Element nameElement1=(Element)names1.item(0);
								System.out.println("student hobby:"+nameElement1.getFirstChild().getNodeValue());
								
								
								NodeList names=e.getElementsByTagName("name");
								Element nameElement=(Element)names.item(0);
								System.out.println("student name:"+nameElement.getFirstChild().getNodeValue());//获取第一个子元素 . 获取节点属性
				         }
							
					}
				}
			System.out.println("student:"+count);
			}catch(Exception e){
				e.printStackTrace();
			}
	
		
	}

}
