package com.asiainfo.parser;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JDOMTest {

    public static void main(String[] args) throws Exception {
        // 对books进行JDOM解析
        // 准备工作
        // 1.创建一个 SAXBuilder 对象
        SAXBuilder saxBuilder = new SAXBuilder();
        // 2.创建输入流,将xml文件加载到输入流里面
        InputStreamReader isr = new InputStreamReader(new FileInputStream("books.xml"),
                "UTF-8");
        // 3.通过saxBuilder的build方法,将输入流加载到saxBuilder
        Document document = saxBuilder.build(isr);
        // 4.document
        Element rootElement = document.getRootElement();
        // 5.获取根节点后的子节点
        List<Element> bookList = rootElement.getChildren();
        // 6.继续解析
        for (Element book : bookList) {
            System.out.println("======开始解析第"+(bookList.indexOf(book)+1)+"书======");
            // 解析book的属性
            List<Attribute> attrList = book.getAttributes();
            // System.out.println("id属性值: "+ book.getAttributeValue("id"));
            // 遍历attrList,遍历不清楚属性值得情况下
            for (Attribute attr : attrList) {
                // 获取属性名
                String attrName = attr.getName();
                // 获取属性值
                String attrValue = attr.getValue();
                System.out.println("属性名: "+attrName +" ,属性值: "+attrValue);
            }
            System.out.println("======结束解析第"+(bookList.indexOf(book)+1)+"书======");
            List<Element> bookChildren = book.getChildren();
            for (Element bookChild : bookChildren) {
                System.out.println("节点名: "+bookChild.getName()+", 节点值: "+bookChild.getValue());
            }
        }
    }
}
