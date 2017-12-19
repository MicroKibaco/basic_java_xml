package com.asiainfo.hander;

import com.asiainfo.entity.Book;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXParserHandler extends DefaultHandler {
    int bookIndex = 0;
    private String value = null;
    private Book mBook = null;
    private ArrayList<Book> mBookList = new ArrayList<>();

    public List<Book> getBookList() {
        return mBookList;
    }

    /**
     * 用来遍历xml文件的开始标签
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 创建book对象
        mBook = new Book();

        // 调用DefaultHandle类的 startElement 方法
        super.startElement(uri, localName, qName, attributes);
        // 开始解析book元素的属性
        if (qName.equals("book")) {
            bookIndex++;
            // 已知book元素下属性的名称,根据属性名称获取属性值
            String value = attributes.getValue("id");
            System.out.println("book的属性值是: " + value);
            // 不知道book元素下,属性的名称以及个数,如何获取属性名和属性值
            int num = attributes.getLength();
            for (int i = 0; i < num; i++) {
                System.out.print("book元素的第" + (i + 1) + "个属性名是: " + attributes.getQName(i));
                System.out.println("-----属性值是: " + attributes.getValue(i));
                if (attributes.getQName(i).equals("id")) {
                    mBook.setId(attributes.getValue(i));
                }
            }
        } else if (!qName.equals("book") && !qName.equals("bookstores")) {
            System.out.print("节点名是: " + qName);
        }

        // qName.equals("name")时候,向book对象setName
    }

    /**
     * 用来遍历xml文件的结束标签
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // 是否针对一本书已经遍历结束
        super.endElement(uri, localName, qName);
        if (qName.equals("book")) {
            mBookList.add(mBook);
            mBook = null;
            System.out.println("==================结束遍历一本书的内容==================");
        } else if ("name".equals(qName)) {
            mBook.setName(value);
        } else if ("author".equals(qName)) {
            mBook.setAuthor(value);
        } else if ("language".equals(qName)) {
            mBook.setLanguage(value);
        } else if ("year".equals(qName)) {
            mBook.setYear(value);
        } else if ("price".equals(qName)) {
            mBook.setPrice(value);
        }
    }

    /**
     * 用来标识解析开始
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("SAX解析开始");
    }

    /**
     * 用来标识解析结束
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("SAX解析结束");
    }

    /**
     *
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        value = new String(ch, start, length);
        if (!value.trim().equals("")) {
            System.out.println(value);
        }
    }
}
