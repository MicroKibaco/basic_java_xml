package com.asiainfo.parser;

import com.asiainfo.entity.Book;
import com.asiainfo.hander.SAXParserHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXTest {
    public static void main(String[] args) throws Exception {
        // 获取SAXParseFactory获取实例factory
        SAXParserFactory factory = SAXParserFactory.newInstance();
       // 通过factory获取SAXParser实例
        SAXParser parser = factory.newSAXParser();
        // 创建SAXParserHandler对象
        SAXParserHandler handler = new SAXParserHandler();
        parser.parse("books.xml",handler);
        System.out.println("共有: "+handler.getBookList().size()+"本书");
        for (Book book : handler.getBookList()) {
            System.out.println(book.getName());
            System.out.println(book.getAuthor());
            System.out.println(book.getYear());
            System.out.println(book.getPrice());
            System.out.println(book.getLanguage());
            System.out.println("---------finish---------");
        }
    }
}
