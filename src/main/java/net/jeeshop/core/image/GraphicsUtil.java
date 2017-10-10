package net.jeeshop.core.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.jeeshop.core.image.bean.ImageBean;
import net.jeeshop.core.image.util.ImageUtil;
import net.jeeshop.core.util.QiniuUtil;

public class GraphicsUtil {
	
	public static BufferedImage makeWordToPic(String word){
		BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(0, 0, 10, 10);
		char[] data = word.toCharArray();
		g.drawChars(data, 0, data.length, 5, 32);
		return bi;
	}

	public static byte[] cutPic(InputStream inputStream, int newWidth, String fileType) throws Exception {
		
		//读取图片
		BufferedInputStream in = new BufferedInputStream(inputStream);
		
		ImageBean bean = ImageUtil.getInstance(inputStream, in, newWidth);
		
		//构建图片流
		BufferedImage tag = new BufferedImage(bean.getNewWidth(), bean.getNewHeight(), BufferedImage.TYPE_INT_RGB);
		//绘制改变尺寸后的图
		tag.getGraphics().drawImage(bean.getBufferedImage(), 0, 0, newWidth, bean.getNewHeight(), null);  
		
		//转字节流
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		ImageIO.write(tag, fileType.trim().toUpperCase(),out);
		
		return out.toByteArray();
	}
	
	public static void cutPic(InputStream inputStream, int newWidth, String fileType, String filePath) throws Exception {
		
		//读取图片
		BufferedInputStream in = new BufferedInputStream(inputStream);
		
		ImageBean bean = ImageUtil.getInstance(inputStream, in, newWidth);
		
		//构建图片流
		BufferedImage tag = new BufferedImage(bean.getNewWidth(), bean.getNewHeight(), BufferedImage.TYPE_INT_RGB);
		//绘制改变尺寸后的图
		tag.getGraphics().drawImage(bean.getBufferedImage(), 0, 0, newWidth, bean.getNewHeight(), null);  
		
		//输出流
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filePath));
		ImageIO.write(tag, fileType.trim().toUpperCase(),out);
		in.close();
		out.close();

	}
	
	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream(new File("D://timg.jpg"));
//		String path = "D:/copy.png";
//		cutPic1(is, 50, "png", path);
		byte[] data = cutPic(is, 200, "png");
//		byte2image(data, path);
		
		// 上传到七牛后保存的文件名
		String key = "attached/image/20170511/1_1.jpg";
		
		new QiniuUtil().uploadByByte(data, key, false);
	}
	
}
