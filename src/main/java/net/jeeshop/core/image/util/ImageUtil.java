package net.jeeshop.core.image.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;

import net.jeeshop.core.image.bean.ImageBean;

public class ImageUtil {
	
	public static ImageBean getInstance(InputStream inputStream, BufferedInputStream in, int width){
		ImageBean bean = null;
		bean = new ImageBean(inputStream);
		
		//字节流转图片对象
		BufferedImage image;
		try {
			image = ImageIO.read(in);
			bean.setBufferedImage(image);
			bean.setHeight(image.getHeight());
			bean.setWidth(image.getWidth());
			
			// 获得缩放的比例
			double ratio = getRatio(bean.getWidth(), bean.getHeight(), width);
			bean.setRadio(ratio);
			
			// 计算新的图面宽度和高度
			int newWidth = (int) (image.getWidth() * ratio);
			int newHeight = (int) (image.getHeight() * ratio);
			bean.setNewHeight(newHeight);
			bean.setNewWidth(newWidth);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	/** 
	 * @Description::(获取将要获取的图片的缩放比例). <br/> 
	 * @author jingmiao
	 * @param width 原宽
	 * @param heigth 原高
	 * @param newWidth 新定义宽
	 * @return
	 */ 
	private static double getRatio(int width, int heigth , int newWidth){
		double ratio = 0.0;
		if (heigth > 100 || width > 100) {
			if (heigth > width) {
				ratio = BigDecimalUtils.div(new BigDecimal(newWidth), new BigDecimal(heigth)).doubleValue();
			} else {
				ratio = BigDecimalUtils.div(new BigDecimal(newWidth), new BigDecimal(width)).doubleValue();
			}
		}
		return ratio;
	}
	
	public static InputStream getInputStream(byte[] data){
		return new ByteArrayInputStream(data);
	}
	
	public static File getFile(String filePath){
		File file = new File(filePath);
		return file;
	}
	
	public static Image getImage(File file){
		Image image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public static Image getImage(String filePath){
		File file = getFile(filePath);
		Image  image = getImage(file);
		return image;
	}
	
	public static Image getImage(ImageInputStream stream){
		Image image = null;
		try {
			image = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public static BufferedImage getBufferedImage(File file){
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage getBufferedImage(String filePath){
		File file = getFile(filePath);
		BufferedImage bufferedImage = getBufferedImage(file);
		return bufferedImage;
	}

	public static BufferedImage getBufferedImage(InputStream input){
		try {
			BufferedImage bufferedImage = ImageIO.read(input);
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void byte2image(byte[] data, String path) {
		if (data.length < 3 || path.equals(""))
			return;
		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(path));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
