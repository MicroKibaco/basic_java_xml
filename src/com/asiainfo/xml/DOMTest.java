package com.asiainfo.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DOMTest {

    public static void main(String[] args) throws Exception {
        // 创建一个DocumentBuilderFactory的对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // 创建一个DocumentBuilder对象
        DocumentBuilder db = dbf.newDocumentBuilder();

        // 通过DocumentBuilder对象的parse方法加载books.xml到文件到当前目录下
        Document document = db.parse("books.xml");

        // 获取所有book节点的集合
        NodeList bookList = document.getElementsByTagName("book");

        System.out.println("一共有" + bookList.getLength() + "本书");
        // 遍历每一个book节点
        for (int i = 0; i < bookList.getLength(); i++) {
            System.out.println("===下面开始遍历第" + (i + 1) + "本书的内容===");

            // 通过 获取一个book节点,nodeList的索引值从0开始
            // 前提: 已经知道book节点有且只有一个属性值
            // 将book节点进行,强制类型转换
            Element book = (Element) bookList.item(i);
            // 遍历book节点所有属性的集合
            NamedNodeMap attrs = book.getAttributes();
          //  System.out.println("第" + (i + 1) + "本书共有" + attrs.getLength() + "个属性");
           // 解析book的子节点
            NodeList childNodes = book.getChildNodes();
            // 遍历childNodes,获取每个节点名和节点值
            System.out.println("第"+(i+1)+"本书共有"+childNodes.getLength()+"子节点");
            for (int k = 0; k < childNodes.getLength(); k++) {
                // 区分text类型的node 和 element 类型的 node
                if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE){
                    // 获取Element类型的节点的节点名
                    System.out.println(childNodes.item(k).getNodeName()+"= "+childNodes.item(k).getFirstChild().getNodeValue());
                    System.out.println(childNodes.item(k).getTextContent());
                }
            }

            /* // 遍历book属性
            for (int j = 0; j < attrs.getLength(); j++) {
                // 通过item(index)方法获取book节点的某一个属性
                Node attr = attrs.item(j);
                // 获取属性名
                System.out.print("属性名: "+attr.getNodeName()+", ");
                // 获取属性值
                System.out.println("属性值: "+attr.getNodeValue()+", ");

            }*/

            String attrValue = book.getAttribute("id");
            System.out.println("id属性的值为" + attrValue);
            System.out.println("===结束遍历第" + (i + 1) + "本书的内容===");

        }
    }
}
