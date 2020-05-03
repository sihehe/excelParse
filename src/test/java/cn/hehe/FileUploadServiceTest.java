package cn.hehe;

import cn.hehe.controller.FileUploadController;
import cn.hehe.service.IFileUploadService;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FileUploadController fileUploadController;

    @Test
    public void excelParseTest() throws Exception {
        File file = new File("f://SECEND_TYPE.xlsx");
        String s = fileUploadService.excelParse(file);
        System.out.println(s);
    }

    @Test
    public void aa() throws IOException {
        byte[] forObject = restTemplate.getForObject("http://localhost:8888/fileUpload/gener?price=1234&proName=aaa", byte[].class);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d://aaaaa.jpg"));
        fileOutputStream.write(forObject);
        System.out.println();
    }

    @Test
    public void aaaa() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        JSONObject map = new JSONObject();
        HashMap map = new HashMap();
        String accessToken = "";
//        map.put("access_token",accessToken);
        map.put("scene","sku=X18H7041009&sysId=123");
        map.put("page","pages/detail/fittingDetail");
        map.put("width",280);
        map.put("auto_color",true);
        map.put("is_hyaline",true);
        String param = com.alibaba.fastjson.JSON.toJSONString(map);
        HttpEntity<String> request = new HttpEntity<>(param,headers);
        byte[] bytes = restTemplate.postForObject(
                "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken,
                request, byte[].class);
        String str = new String(bytes);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d://code.jpg"));
        fileOutputStream.write(bytes);
        System.out.println();
    }
    @Test
    public void getCode(){
        try
        {
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=你的access_token");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            String accessToken = this.getAccessToken("wx53aef9660248ffd9", "59e145ea0e0beae092f4a91486d06166");
            JSONObject paramJson = new JSONObject();
            paramJson.put("access_token",accessToken);
            paramJson.put("scene","sku=X18H7041009&sys_id=123");
            paramJson.put("page","pages/detail/fittingDetail");
            paramJson.put("width", 280);
            paramJson.put("auto_color", true);
            paramJson.put("is_hyaline",true);
            /**
             * line_color生效
             * paramJson.put("auto_color", false);
             * JSONObject lineColor = new JSONObject();
             * lineColor.put("r", 0);
             * lineColor.put("g", 0);
             * lineColor.put("b", 0);
             * paramJson.put("line_color", lineColor);
             * */

            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            OutputStream os = new FileOutputStream(new File("d://dCode.png"));
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1)
            {
                os.write(arr, 0, len);
                os.flush();
            }
            os.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private  String getAccessToken(String appid, String secret) {
        StringBuilder getAccessTokenUrl = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=");
        getAccessTokenUrl.append(appid).append("&secret=").append(secret);
        Map result = restTemplate.getForObject(getAccessTokenUrl.toString(), Map.class);
        if (result.containsKey("access_token")) {
            String accessToken = (String) result.get("access_token");
            return accessToken;
//            map.put("accessToken",accessToken);
//            map.put("time",System.currentTimeMillis());
        } else {
            throw new RuntimeException("access_token 获取失败");
        }
    }

}
