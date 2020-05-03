package cn.hehe;

import cn.hehe.service.IShareService;
import cn.hehe.service.ITemplateService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @program: excelParse1
 * @description:
 * @author: Mr.si
 * @create: 2020-05-01 18:38
 **/
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class TemplateServiceTest {

    @Autowired
    private ITemplateService templateService;

    @Autowired
    private IShareService shareService;

    @Test
    public void testGener() throws Exception {
        //map 里存的模板里面可以用的变量
      /*  HashMap<String, Object> map = new HashMap<>(2);
        //背景图
        map.put("background", "f://a.jpg");
        map.put("price", "199");
        InputStream htmlStream = templateService.getHtmlInputStream(map, "activity_details_singer");
        FileOutputStream fileOutputStream1 = new FileOutputStream(new File("f://test.html"));
        IOUtils.copy(htmlStream,fileOutputStream1);
        StreamAndUrlDocumentSource streamAndUrlDocumentSource = new StreamAndUrlDocumentSource(htmlStream);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("f://l.png"));
        Dimension dimension = new Dimension(10000,10000);
        shareService.htmlToPicture(streamAndUrlDocumentSource,fileOutputStream,dimension);*/
    }

}
