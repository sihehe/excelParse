package cn.hehe.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @program: excelparsedemo
 * @description:
 * @author: Mr.si
 * @create: 2020-04-28 13:50
 **/

public interface IFileUploadService {

    String excelParse(File file) throws Exception;
}
