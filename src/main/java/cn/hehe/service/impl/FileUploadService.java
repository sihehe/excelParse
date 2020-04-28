package cn.hehe.service.impl;

import cn.hehe.service.IFileUploadService;
import cn.hehe.utils.ExcelParseUtils;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @program: excelparsedemo
 * @description:
 * @author: Mr.si
 * @create: 2020-04-28 13:54
 **/
@Service
public class FileUploadService implements IFileUploadService {

    public String excelParse(File file) throws Exception {
        JSONArray jsonArray = ExcelParseUtils.readExcel(file);
        return jsonArray.toString();
    }
}
