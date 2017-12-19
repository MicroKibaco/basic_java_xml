#### 初次邂逅xml
表现: 以".xml"为文件扩展名的文件
存储: 树形结构
```xml
<bookstores>
<book id="1">
    <name>冰与火之歌</name>
    <author>乔治马丁</author>
    <year>2014</year>
    <price>89</price>
</book>
<book id="2">
     <name>安徒生图画</name>
    <year>2004</year>
    <price>77</price>
    <language>English</language>
</book>
</bookstores>
```
#### 在java中如何获取xml文件内容
- 解析xml文件
在java程序中读取xml文件的过程,也称为解析xml文件
- 解析目的
获取节点名,节点值,属性名,属性值
- 四种解析
DOM,SAX,DOM4J,JDOM
- 解析目标
解析xml文件后,java程序能够得到xml文件的所有数据
思考:
如何在java中保留xml数据的结构?
- DOM解析的步骤
准备工作:
- 创建一个DocumentBuilderFactory的对象

#### 在java程序中如何生成xml文件
```java



public class DOMTest {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(parser);
        NodeList bookList = document.getElementsByTagName("book");
        for (int i = 0; i < bookList.getLength(); i++) {
            Element book = (Element) bookList.item(i);
            NamedNodeMap attrs = book.getAttributes();
            NodeList childNodes = book.getChildNodes();
            for (int k = 0; k < childNodes.getLength(); k++) {
                if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE){
                    System.out.println(childNodes.item(k).getTextContent());
                }
            }

        }
    }
}


```