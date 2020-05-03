package cn.hehe.utils;

/**
 * @program: excelParse1
 * @description:
 * @author: Mr.si
 * @create: 2020-05-03 14:41
 **/
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author Miracle Luna
 * @version 1.0
 * @date 2019/7/3 18:55
 */
public class Base64Converter {

    final static Base64.Encoder encoder = Base64.getEncoder();
    final static Base64.Decoder decoder = Base64.getDecoder();

    /**
     * 给字符串加密
     * @param text
     * @return
     */
    public static String encode(String text) {
        byte[] textByte = new byte[0];
        try {
            textByte = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encodedText = encoder.encodeToString(textByte);
        return encodedText;
    }

    /**
     * 将加密后的字符串进行解密
     * @param encodedText
     * @return
     */
    public static String decode(String encodedText) {
        String text = null;
        try {
            text = new String(decoder.decode(encodedText), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("sku=X18H7041009&userId=o-fVW40aMo1_lPg33jaY39N3-f5Y".length());
        // 加密
        String encode = Base64Converter.encode("sku=X18H7041009&userId=o-fVW40aMo1_lPg33jaY39N3-f5Y");
        System.out.println(encode);
        System.out.println(encode.length());
        // 解密
        System.out.println("\n====  [解密后] 用户名/密码  =====");
        System.out.println(Base64Converter.decode(encode));
    }
}