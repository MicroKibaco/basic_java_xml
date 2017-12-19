package com.asiainfo.test;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DOM4JTest {
    public static void main(String[] args) throws Exception {

        DOM4JTest test = new DOM4JTest();
        test.parseXML();
        test.createXML();

    }

    private  void createXML() throws IOException {

        // 1.创建document对象,document对象代表整个xml文档
        Document document = DocumentHelper.createDocument();

        // 2.创建根节点
        Element rss = document.addElement("rss");

        // 3.向rss节点添加version属性
        rss.addAttribute("version","2.0");

        // 4.生成子节点及节点内容
        Element channel = rss.addElement("channel");

        Element title = channel.addElement("title");
        title.setText("国内最新新闻");

        // 5.设置生成xml的格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("GBK");

        // 6.生成xml文件
        File file = new File("rssnews.xml");
        XMLWriter writer = new XMLWriter(new FileOutputStream(file),format);
        writer.write(document);
        writer.close();

    }

    private  void parseXML() throws DocumentException {
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
