package cn.hehe;

import cn.hehe.service.IFileUploadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * @program: excelparsedemo
 * @description:
 * @author: Mr.si
 * @create: 2020-04-28 13:48
 **/
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class FileUploadServiceTest {

    @Autowired
    private IFileUploadService fileUploadService;

    @Test
    public void excelParseTest() throws Exception {
        File file = new File("f://SECEND_TYPE.xlsx");
        String s = fileUploadService.excelParse(file);
        System.out.println(s);

    }

}
