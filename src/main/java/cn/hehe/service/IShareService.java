package cn.hehe.service;

import org.fit.cssbox.io.DocumentSource;
import org.xml.sax.SAXException;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @program: excelParse1
 * @description:
 * @author: Mr.si
 * @create: 2020-05-01 18:52
 **/
public interface IShareService {
    void htmlToPicture(DocumentSource docSource, OutputStream out, Dimension windowSize) throws IOException, SAXException;
}
