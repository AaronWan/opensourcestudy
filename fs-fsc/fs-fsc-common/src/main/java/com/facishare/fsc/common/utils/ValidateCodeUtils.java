package com.facishare.fsc.common.utils;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.jhlabs.image.RippleFilter;
import com.jhlabs.image.ShadowFilter;
import com.jhlabs.image.WaterFilter;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.CubicCurve2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Aaron on 16/5/3.
 */
public class ValidateCodeUtils {
    private static DefaultKaptcha kaptcha;
    private static Properties properties;

    private static Color[] fontColors = new Color[]{Color.RED, Color.blue, Color.BLACK, new Color(96, 43, 147), new Color(147, 5, 115)};
    private static final String[] vCChars=new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N",
    "O","P","Q","R","S","T","U","V","W","X","Y","Z","1","2","3","4","5","6","7","8","9","0"
    };
    static {
        kaptcha = new DefaultKaptcha();
        properties = new Properties();
        kaptcha.setConfig(new Config(properties));
    }
    public static String createCode(int n){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<n;i++){
            sb.append(vCChars[random.nextInt(vCChars.length)]);
        }
        return sb.toString();
    }

    public static byte[] createCodeBytes(int fontMaxSize, int fontMinSize, int bezierCount, int lineCount, String contents) {
        BufferedImage image = createCodeBufferedImage(fontMaxSize, fontMinSize, bezierCount, lineCount, contents);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
            Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("JPG");
            ImageWriter writer = iter.next();
            ImageWriteParam iwp = writer.getDefaultWriteParam();
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            iwp.setCompressionQuality(1f);
            writer.setOutput(ios);
            writer.write(null, new IIOImage(image, null, null), iwp);
            writer.dispose();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("生成图形验证码异常", e);
        }
    }

    private static int width = 131;
    private static int height = 56;
    static Random random = new Random();

    public static BufferedImage createCodeBufferedImage(int fontMaxSize, int fontMinSize, int bezierCount, int lineCount, String contents) {
//        画字符串
        int length = contents.length();
        int fontSize = width / length < fontMinSize ? width / length : width / length > fontMaxSize ? fontMaxSize : width / length;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        FontRenderContext frc = g.getFontRenderContext();
        g.setBackground(Color.WHITE);
        int x = 0, y = 0;
        g.fillRect(x, y, width, height);
        Font font = new Font(Font.SERIF, Font.BOLD, fontSize);
        FontMetrics fm = g.getFontMetrics(font);
        float wordHeight = fm.getHeight();
        int gap = 10;
        y = (int) (height - wordHeight) / 2 + 10;
        int[] charWidths = new int[contents.length()];
        int widthNeeded = 0;

        int startPosX;
        for (startPosX = 0; startPosX < contents.length(); ++startPosX) {
            char[] i = new char[]{contents.charAt(startPosX)};
            GlyphVector charToDraw = font.createGlyphVector(frc, i);
            charWidths[startPosX] = (int) charToDraw.getVisualBounds().getWidth();
            if (startPosX > 0) {
                widthNeeded += gap;
            }

            widthNeeded += charWidths[startPosX];
        }

        int start = (width - widthNeeded) / 2;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            g.setColor(fontColors[random.nextInt(fontColors.length)]);
            g.setFont(font);
            sb.append(contents.charAt(i));
            int angler = random.nextInt(10);
            g.rotate(angler * Math.PI / 180);
            g.drawString(contents.charAt(i) + "", start, height - y);
            g.rotate(-angler * Math.PI / 180);
            start = start + charWidths[i] + gap;
        }

        ShadowFilter shadowFilter = new ShadowFilter();
        shadowFilter.setRadius(10.0F);
        shadowFilter.setDistance(5.0F);
        shadowFilter.setOpacity(1.0F);
        Random rand = new Random();
        RippleFilter rippleFilter = new RippleFilter();
        rippleFilter.setWaveType(0);
        rippleFilter.setXAmplitude(7.6F);
        rippleFilter.setYAmplitude(rand.nextFloat() + 1.0F);
        rippleFilter.setXWavelength((float)(rand.nextInt(7) + 8));
        rippleFilter.setYWavelength((float)(rand.nextInt(3) + 2));
        rippleFilter.setEdgeAction(1);
        BufferedImage effectImage = shadowFilter.filter(bufferedImage, (BufferedImage) null);
//        effectImage = rippleFilter.filter(effectImage, (BufferedImage) null);
        g.drawImage(effectImage, 0, 0, null, (ImageObserver) null);
        drawBackground(bufferedImage, lineCount, bezierCount);
        g.dispose();
        return bufferedImage;
    }

    /**
     * 添加背景,例如:增加噪点,添加曲线和直线等
     *
     * @param bufferedImage
     */
    private static void drawBackground(BufferedImage bufferedImage, int lineCount, int bezierCount) {
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        for (int i = 0; i < 1000; i++) {
            //获取画笔
            WritableRaster raster = bufferedImage.getRaster();
            raster.setPixel(random.nextInt(width), random.nextInt(height), new int[]{random.nextInt(0xff), random.nextInt(0xff), random.nextInt(0xff)});
        }
        g.setColor(Color.darkGray);
        for (int i = 0; i < lineCount; i++) {
            g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }
        for (int i = 0; i < bezierCount; i++) {
            g.setColor(new Color(random.nextInt(0xff), random.nextInt(0xff), random.nextInt(0xff)));
            CubicCurve2D cubic = new CubicCurve2D.Float(10, (float) random.nextInt(height), (float) random.nextInt(width), (float) random.nextInt(height), (float) random.nextInt(width), (float) random.nextInt(height), width - 10, (float) random.nextInt(height));
            g.draw(cubic);
        }
    }

}
