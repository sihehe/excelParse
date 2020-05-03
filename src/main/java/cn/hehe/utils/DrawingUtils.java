package cn.hehe.utils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

/**
 * @program: qplife
 * @description:
 * @author: Mr.si
 * @create: 2020-05-01 18:14
 **/
public class DrawingUtils {
    private static float jPEGcompression = 0.75f;// 图片清晰比率

    /**
     * @Description : 将二维码图片和文字生成到一张图片上
     * @Param : originalImg 原图
     * @Param : qrCodeImg 二维码地址
     * @Param : shareDesc 图片文字
     * @return : java.lang.String
     * @Author : houzhenghai
     * @Date : 2018/8/15
     */
    public static byte[] generatePromotionImag(String originalImg, String yifu, String qrCodeImg, String proName, String promotionsPrice, String price) throws Exception {
        // 加载原图图片
        BufferedImage imageLocal = ImageIO.read(new URL(originalImg));
        BufferedImage yifuLocal = ImageIO.read(new URL(yifu));
        // 加载用户的二维码
        BufferedImage imageCode = ImageIO.read(new URL(qrCodeImg));
        // 以原图片为模板
        Graphics2D g = getGraphics2D( imageLocal, yifuLocal, imageCode);
        // 设置商品名称
        g.setFont(new Font("微软雅黑", Font.PLAIN + Font.BOLD, 35));
        g.setColor(Color.black);
        // 计算文字长度，计算居中的x点坐标
        g.drawString(proName, 50, imageLocal.getHeight() - 240);
        // 设置优惠价格
        g.setFont(new Font("微软雅黑", Font.PLAIN, 32));
        g.setColor(Color.red);
        // 设置价格
        g.drawString(promotionsPrice, 80, imageLocal.getHeight() - 178);
        g.setFont(new Font("微软雅黑", Font.PLAIN, 28));
        g.setColor(Color.GRAY);
        g.drawString(price, 155, imageLocal.getHeight() - 120);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        saveAsJPEG(imageLocal, out);
        out.close();
        return out.toByteArray();
//        FileOutputStream fileOutputStream = new FileOutputStream(new File("f://a.jpg"));
//        out.writeTo(fileOutputStream);
//        return urlImgDownInputStream(FileUtils.parse(out));
    }


    public static byte[] generateImg(String originalImg, String yifu, String qrCodeImg, String proName, String price) throws Exception {
        // 加载原图图片
        BufferedImage imageLocal = ImageIO.read(new URL(originalImg));
        BufferedImage yifuLocal = ImageIO.read(new URL(yifu));
        // 加载用户的二维码
        BufferedImage imageCode = ImageIO.read(new URL(qrCodeImg));
        // 以原图片为模板
        Graphics2D g = getGraphics2D(imageLocal, yifuLocal, imageCode);
        // 设置商品名称
        g.setFont(new Font("微软雅黑", Font.PLAIN + Font.BOLD, 35));
        g.setColor(Color.black);
        // 计算文字长度，计算居中的x点坐标
        g.drawString(proName, 50, imageLocal.getHeight() - 180);
        // 设置优惠价格
        g.setFont(new Font("微软雅黑", Font.PLAIN, 32));
        g.setColor(Color.red);
        // 设置价格
        g.drawString(price, 185, imageLocal.getHeight() - 118);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        saveAsJPEG(imageLocal, out);
        out.close();
        return out.toByteArray();
    }

    private static Graphics2D getGraphics2D( BufferedImage imageLocal, BufferedImage yifuLocal, BufferedImage imageCode) {
        Graphics2D g = imageLocal.createGraphics();
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g.setComposite(ac);
        g.setBackground(Color.WHITE);
        // 在模板上添加用户二维码(地址,左边距,上边距,图片宽度,图片高度,未知)
        g.drawImage(imageCode, 520, imageLocal.getHeight() - 240, 200, 200, null);
        g.drawImage(yifuLocal, 85, imageLocal.getHeight() - 860, 580, 580, null);
        return g;
    }

    /**
     * 以JPEG编码保存图片
     *
     * @param imageToSave
     *            要处理的图像图片
     * @param fos
     *            文件输出流
     * @throws IOException
     */
    private static void saveAsJPEG(BufferedImage imageToSave, ByteArrayOutputStream fos) throws IOException {
        ImageWriter imageWriter = ImageIO.getImageWritersBySuffix("jpg").next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
        imageWriter.setOutput(ios);
        if (jPEGcompression >= 0 && jPEGcompression <= 1f) {
            // new Compression
            JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
            jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
            jpegParams.setCompressionQuality(jPEGcompression);

        }
        // new Write and clean up
        ImageIO.setUseCache(false);
        imageWriter.write(new IIOImage(imageToSave, null, null));
        ios.close();
        imageWriter.dispose();
    }


    /**
     * test
     *
     * @param args
     * @throws
     */
    public static void main(String[] args) {
        long starttime = System.currentTimeMillis();
        System.out.println("开始：" + starttime);
        try {
            String originalImg = "https://oss.qipaistyle.com/other/2.jpg";
//            String yifu = "https://oss.qipaistyle.com/mainImage/718A7003041/1.jpg";
            String yifu = "https://oss.qipaistyle.com/clothesImage/511Y61510-19/511Y61510-19.png";
            String qrCodeImg = "https://oss.qipaistyle.com/other/code.jpg";
            String shareDesc = "1999.99";
            String price = "2999.99";
            String proName = "长袖衬衫/黑标/品牌/主题";
            byte[] bytes = generateImg(originalImg, yifu, qrCodeImg, proName, price);
            FileOutputStream fileOutputStream = new FileOutputStream(new File("f://a.png"));
            fileOutputStream.write(bytes);
//            System.out.println("生成完毕,url=" + img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("结束：" + (System.currentTimeMillis() - starttime) / 1000);
    }

}
