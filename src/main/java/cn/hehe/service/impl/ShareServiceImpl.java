package cn.hehe.service.impl;

import cn.hehe.service.IShareService;
import cz.vutbr.web.css.MediaSpec;
import org.fit.cssbox.css.CSSNorm;
import org.fit.cssbox.css.DOMAnalyzer;
import org.fit.cssbox.io.DOMSource;
import org.fit.cssbox.io.DefaultDOMSource;
import org.fit.cssbox.io.DocumentSource;
import org.fit.cssbox.layout.BrowserCanvas;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

/**
 * @program: excelParse1
 * @description:
 * @author: Mr.si
 * @create: 2020-05-01 18:53
 **/
@Service
public class ShareServiceImpl implements IShareService {

    private String mediaType = "screen";

    //方法的out是生成图片后的输出流
    @Override
    public void htmlToPicture(DocumentSource docSource, OutputStream out, Dimension windowSize) throws IOException, SAXException {

        DOMSource parser = new DefaultDOMSource(docSource);
        Document doc = parser.parse();

        MediaSpec media = new MediaSpec(mediaType);
        media.setDimensions((float) windowSize.width, (float) windowSize.height);
        media.setDeviceDimensions((float) windowSize.width, (float) windowSize.height);
        DOMAnalyzer da = new DOMAnalyzer(doc);
        da.setMediaSpec(media);
        da.attributesToStyles();

        da.addStyleSheet((URL) null, CSSNorm.stdStyleSheet(), DOMAnalyzer.Origin.AGENT);
        da.addStyleSheet((URL) null, CSSNorm.userStyleSheet(), DOMAnalyzer.Origin.AGENT);
        da.addStyleSheet((URL) null, CSSNorm.formsStyleSheet(), DOMAnalyzer.Origin.AGENT);
        da.getStyleSheets();
        BrowserCanvas contentCanvas = new BrowserCanvas(da.getRoot(), da, docSource.getURL());
        contentCanvas.setAutoMediaUpdate(false);
        contentCanvas.getConfig().setClipViewport(false);
        contentCanvas.getConfig().setLoadImages(true);
        contentCanvas.getConfig().setLoadBackgroundImages(true);

        contentCanvas.createLayout(windowSize);
        //生成图片的类型可以是svg
        ImageIO.write(contentCanvas.getImage(), "png", out);
        docSource.close();
    }
}