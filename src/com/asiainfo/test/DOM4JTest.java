package com.asiainfo.test;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class DOM4JTest {
    public static void main(String[] args) throws Exception {
        // 解析books.xml
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        // 通过Reader对象的read方法加载books.xml文件
        Document document = reader.read(new File("books.xml"));
        // 通过document对象获取bookStores
        Element bookStores = document.getRootElement();
        // 通过Element的对象的elementIterator方法遍历迭代器
        Iterator it = bookStores.elementIterator();
        // 遍历迭代器,获取根节点的信息(书籍)
        while (it.hasNext()) {
            System.out.println("=========开始遍历某一本书=========");
            Element book = (Element) it.next();
            // 获取book的属性名和属性值
            List<Attribute> bookAttrs = book.attributes();
            System.out.println("节点名: " + book.getName() + "---节点值: " + book.getStringValue());

            for (Attribute attr : bookAttrs) {
                System.out.println("节点名: " + attr.getName() + "节点值: " + attr.getValue());
            }
            System.out.println("=========结束遍历某一本书=========");
        }
    }
}
