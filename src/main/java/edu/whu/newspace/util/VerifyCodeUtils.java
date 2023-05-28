package edu.whu.newspace.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Newspace
 * @version 1.0
 * @description 验证码生成工具类
 * @date   2023/5/25 15:38
 */
@Slf4j
public class VerifyCodeUtils {
    // 加载字体并缓存
    private static volatile Font cacheFont = null;
    //默认宽度
    private static final Integer imgWidth = 150;
    //默认高度
    private static final Integer imgHeight = 50;
    //随机字符串和数字
    private static final String STRING_NUMBER_VERIFY_CODE = "123456789ABCDEFGHJKLNPQRSTUVXYZ";
    //随机字符串
    private static final String STRING_VERIFY_CODE = "ABCDEFGHIJKLMNPOQRSTUVWXYZ";
    //随机数
    private static Random random = new Random();
    //默认验证码长度
    private static int DEFAULT_VERIFY_CODE_LENGTH = 4;
    //验证码最大长度
    private static int MAX_VERIFY_CODE_LENGTH = 6;
    //默认干扰线系数
    private static float DEFAULT_INTERFERE_RATE = 0.1f;
    //默认噪声系数
    private static float DEFAULT_NOISE_RATE = 0.3f;

    /**
     * 返回base64图片验证码
     * @param code 验证码
     * @param imgWidth 宽度
     * @param imgHeight 高度
     * @return
     */
    public static String generateCaptchaImageVerifyCode(String code, Integer imgWidth, Integer imgHeight){
        if (imgWidth == null){
            imgWidth = VerifyCodeUtils.imgWidth;
        }
        if (imgHeight == null){
            imgHeight = VerifyCodeUtils.imgHeight;
        }
        return generateCaptchaImage(imgWidth, imgHeight, code,DEFAULT_INTERFERE_RATE , DEFAULT_NOISE_RATE);
    }

    /**
     * 返回base64图片验证码
     * @param code 验证码
     * @param imgWidth 宽度
     * @param imgHeight 高度
     * @param interfereRate 干扰性系数0.0-1.0
     * @param noiseRate 噪声系数 0.0-1.0
     * @return
     */
    public static String generateCaptchaImage(String code, int imgWidth, int imgHeight,float interfereRate, float noiseRate){
        return generateCaptchaImage(imgWidth, imgHeight, code, interfereRate, noiseRate);
    }

    /**
     * 生成验证码图片
     * @param imgWidth 宽度
     * @param imgHeight 高度
     * @param code 验证码
     * @param interfereRate 干扰系数 0.1-1
     * @param noiseRate 噪声系数 0.1-1
     */
    private static String generateCaptchaImage(int imgWidth, int imgHeight, String code, float interfereRate, float noiseRate){
        String base64Img = null;
        try {
            int length = code.length();
            BufferedImage image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
            Random rand = new Random();
            Graphics2D graphics2D = image.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 设置边框色
            graphics2D.setColor(Color.GRAY);
            graphics2D.fillRect(0, 0, imgWidth, imgHeight);
            // 设置背景色
            Color c = new Color(235, 235, 235);
            graphics2D.setColor(c);
            graphics2D.fillRect(0, 1, imgWidth, imgHeight - 2);
            //绘制干扰
            interfere(image, graphics2D, imgWidth, imgHeight, interfereRate, noiseRate);
            //使图片扭曲
            shear(graphics2D, imgWidth, imgHeight, c);
            //字体颜色
            graphics2D.setColor(getRandColor(30, 100));
            //字体大小
            int fontSize = imgHeight >= 50 ?  imgHeight - 8 : imgHeight >= 30 ? imgHeight - 6 : imgHeight - 4;
            //字体 Fixedsys/Algerian
            Font font = new Font("Fixedsys", Font.ITALIC, fontSize);
            graphics2D.setFont(font);
            char[] chars = code.toCharArray();
            for (int i = 0; i < length; i++) {
                //设置旋转
                AffineTransform affine = new AffineTransform();
                //控制在-0.3-0.3
                double theta = ((Math.PI / 4 * rand.nextDouble())*0.3) * (rand.nextBoolean() ? 1 : -1);
                affine.setToRotation(theta, (imgWidth / length) * i + fontSize / 2, imgHeight / 2);
                graphics2D.setTransform(affine);
                int x = ((imgWidth - 10) / length) * i + 4;
                //文字居中
                FontMetrics fm = graphics2D.getFontMetrics();
                int stringAscent = fm.getAscent();
                int stringDescent = fm.getDescent ();
                int y = imgHeight / 2 + (stringAscent - stringDescent) / 2;
                graphics2D.drawChars(chars, i, 1, x, y);
            }
            graphics2D.dispose();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            Base64.Encoder encoder = Base64.getEncoder();
            base64Img = encoder.encodeToString(outputStream.toByteArray());
            base64Img = base64Img.replaceAll("\n", "").replaceAll("\r", "");
        }catch (Exception e){
            log.error("生成图片验证码异常：", e);
        }
        return base64Img;
    }


    /**
     * 绘制干扰线和噪点
     * @param image 图像
     * @param graphics2D 二维图
     * @param imgWidth 宽度
     * @param imgHeight 高度
     * @param interfereRate 干扰线率 0.1-1
     * @param noiseRate 噪声率 0.1-1
     */
    private static void interfere(BufferedImage image,Graphics2D graphics2D, int imgWidth, int imgHeight, float interfereRate, float noiseRate){
        Random random = new Random();
        graphics2D.setColor(getRandColor(160, 200));
        int line = (int) (interfereRate * 100);
        for (int i = 0; i < line; i++) {
            int x = random.nextInt(imgWidth - 1);
            int y = random.nextInt(imgHeight - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            graphics2D.drawLine(x, y, x + xl + 40, y + yl + 20);
        }
        int area = (int) (noiseRate * imgWidth * imgHeight)/10;
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(imgWidth);
            int y = random.nextInt(imgHeight);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }
    }

    /**
     * 获取随机颜色
     * @param fc
     * @param bc
     * @return
     */
    private static Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 获取rgb
     * @return
     */
    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    /**
     * 获取随机rgb颜色
     * @return
     */
    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    /**
     * 使图片扭曲
     * @param g
     * @param w1
     * @param h1
     * @param color
     */
    private static void shear(Graphics g, int w1, int h1, Color color) {
        distortionX(g, w1, h1, color);
        distortionY(g, w1, h1, color);
    }

    /**
     * 使图片x轴扭曲
     * @param g
     * @param w1
     * @param h1
     * @param color
     */
    private static void distortionX(Graphics g, int w1, int h1, Color color) {
        int period = random.nextInt(4);
        boolean borderGap = true;
        int frames = 2;
        int phase = random.nextInt(4);
        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }
    }

    /**
     * 使图片y轴扭曲
     * @param g
     * @param w1
     * @param h1
     * @param color
     */
    private static void distortionY(Graphics g, int w1, int h1, Color color) {
        int period = random.nextInt(20) + 10;
        boolean borderGap = true;
        int frames = 20;
        int phase = 8;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }
    }

    /**
     * 随机获得四位验证码
     * @return  四位验证码
     */
    public static String codeFen(){
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        /*String类 中 toCharArray()方法 将此字符串转换为一个新的字符数组。*/
        char[] VerificationCodeArray = str.toCharArray();
        Random random = new Random();
        int count = 0;/*计数器*/
        StringBuilder stringBuilder = new StringBuilder();
        while(true) {
            /*随机生成一个数组长度的随机数*/
            int index = random.nextInt(VerificationCodeArray.length);
            /*将随机生成的数所对应的元素赋给字符c*/
            char c = VerificationCodeArray[index];
        /*StringBuilder类中 indexOf()方法 从指定的索引处开始，
        返回第一次出现的指定子字符串在该字符串中的索引。如果不存在这样的 k 值，则返回 -1。
        "加上""自动转化为字符串"*/
            if (stringBuilder.indexOf(c + "") == -1) {
                /*StringBuilder类中 append(char c)方法 将 char 参数的字符串表示形式追加到此序列。*/
                stringBuilder.append(c);
                /*添加一次计数器自增1*/
                count++;
            }
            /*当count等于4时结束，随机生成四位数的验证码*/
            if (count == 4) {
                break;
            }
        }
        /*返回字符串*/
        return stringBuilder.toString();
    }
}

