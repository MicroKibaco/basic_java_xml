package com.asiainfo.test;

import com.asiainfo.entity.Book;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JDOMTest {
    private static ArrayList<Book> booksList = new ArrayList<>();

    private void createXML() throws IOException {
        // 1.生成根节点
        Element rss = new Element("rss");
        // 2.为节点添加属性
        rss.setAttribute("version","2.0");
        // 3.生成一个document对象
        Document document = new Document(rss);

        Element channel = new Element("channel");
        rss.addContent(channel);

        Element title = new Element("title");
        title.setText("国内最新新闻");
        channel.addContent(title);

        Format format  =Format.getCompactFormat();
        format.setIndent("\n");
        format.setEncoding("GBK");


        // 4.创建outputter对象
        XMLOutputter outputter = new XMLOutputter(format);

        // 5.利用outputter将document对象转化为xml文档
         outputter.output(document,new FileOutputStream(new File("rssnews.xml")));
    }

    public static void main(String[] args) throws Exception {
        JDOMTest test = new JDOMTest();
     //   test.parseXML();
        test.createXML();

    }

    private  void parseXML() throws JDOMException, IOException {
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
            System.out.println(" 开始解析第" + (bookList.indexOf(book) + 1) + "书======");
            Book bookEntry = new Book();
            // 解析book的属性
            List<Attribute> attrList = book.getAttributes();
            // System.out.println("id属性值: "+ book.getAttributeValue("id"));
            // 遍历attrList,遍历不清楚属性值得情况下
            for (Attribute attr : attrList) {
                // 获取属性名
                String attrName = attr.getName();
                // 获取属性值
                String attrValue = attr.getValue();
                System.out.println("属性名: " + attrName + " ,属性值: " + attrValue);
                if (attrName.equals("id")) {
                    bookEntry.setId(attrValue);
                }
            }

            List<Element> bookChildren = book.getChildren();
            for (Element bookChild : bookChildren) {
                System.out.println("节点名: " + bookChild.getName() + ", 节点值: " + bookChild.getValue());
                if (bookChild.getName().equals("name")) {

                    bookEntry.setName(bookChild.getValue());

                } else if (bookChild.getName().equals("author")) {

                    bookEntry.setAuthor(bookChild.getValue());

                } else if (bookChild.getName().equals("year")) {

                    bookEntry.setYear(bookChild.getValue());

                } else if (bookChild.getName().equals("price")) {

                    bookEntry.setPrice(bookChild.getValue());

                } else if (bookChild.getName().equals("language")) {

                    bookEntry.setLanguage(bookChild.getValue());

                }
            }
            System.out.println("======结束解析第" + (bookList.indexOf(book) + 1) + "书======");

            booksList.add(bookEntry);
            bookEntry = null;
        }
    }
}
