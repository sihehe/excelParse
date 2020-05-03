package cn.hehe.controller;

import cn.hehe.service.IFileUploadService;
import cn.hehe.service.IShareService;
import cn.hehe.service.ITemplateService;
import cn.hehe.utils.DrawingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

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

    @Autowired
    private ITemplateService templateService;

    @Autowired
    private IShareService shareService;

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

    @PostMapping("/a")
    public String a(@RequestBody List<Integer> list){
        System.out.println(list);
        return "success";
    }


    @GetMapping("/gener")
    public void gener(String price,String proName) throws Exception {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        String originalImg = "https://oss.qipaistyle.com/other/2.jpg";
        String yifu = "https://oss.qipaistyle.com/clothesImage/511Y61510-19/511Y61510-19.png";
        String qrCodeImg = "https://oss.qipaistyle.com/other/code.jpg";
        String shareDesc = "1999.99";
//        String price = "2999.99";
//        String proName = "长袖衬衫/黑标/品牌/主题";
        byte[] bytes = DrawingUtils.generateImg(originalImg, yifu, qrCodeImg, proName, price);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
    }
}
