package cn.hehe.service;

import java.io.InputStream;
import java.util.Map;

/**
 * @program: excelParse1
 * @description:
 * @author: Mr.si
 * @create: 2020-05-01 18:38
 **/
public interface ITemplateService {

    //我返回的输入流在后面好输入到cssbox
    InputStream getHtmlInputStream(Map<String, Object> root, String template) throws Exception;
}
