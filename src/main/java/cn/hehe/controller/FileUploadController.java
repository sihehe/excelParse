package cn.hehe.controller;

import cn.hehe.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @program: excelparsedemo
 * @description:
 * @author: Mr.si
 * @create: 2020-04-28 13:54
 **/
@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {

    @Autowired
    private IFileUploadService fileUploadService;

    @RequestMapping("/excelParse")
    public String excelParse(HttpServletRequest httpServletRequest) throws Exception {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) httpServletRequest);
        MultipartFile multipartFile = params.getFile("file");
        String OriginalFilename = multipartFile.getOriginalFilename();
        int i = OriginalFilename.lastIndexOf(".");
        String suffix = OriginalFilename.substring(i + 1);
        File file = new File("d:/a." + suffix);
        multipartFile.transferTo(file);
        String json = fileUploadService.excelParse(file);
        return json;
    }
}
