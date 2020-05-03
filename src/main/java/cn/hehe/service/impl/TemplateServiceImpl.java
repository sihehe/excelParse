package cn.hehe.service.impl;

import cn.hehe.service.ITemplateService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;

/**
 * @program: excelParse1
 * @description:
 * @author: Mr.si
 * @create: 2020-05-01 18:38
 **/
@Service
public class TemplateServiceImpl implements ITemplateService {

    @Resource
    Configuration cfg;

    /**
     *  获取生成模板inputStream
     * @param root
     * @param template
     * @return
     * @throws Exception
     */
    @Override
    public InputStream getHtmlInputStream(Map<String,Object> root, String template) throws Exception {

        Template temp = cfg.getTemplate(template+".ftl");
        StringWriter out = new StringWriter();
        temp.process(root, out);

        StringBuffer buffer = out.getBuffer();

        return  new ByteArrayInputStream(new String(buffer).getBytes());
    }

}
