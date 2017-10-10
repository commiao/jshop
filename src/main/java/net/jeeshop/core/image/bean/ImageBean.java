package net.jeeshop.core.image.bean;

import java.awt.image.BufferedImage;
import java.io.InputStream;

/** 
 * CreateDate:2017年5月10日下午4:18:50 
 * @Description: 图片属性  
 * @author:jingmiao
 * @version V1.0   
 */
public class ImageBean {
	/**
	 * width:原图片宽 
	 */
	private int width;
	/**
	 * height:原图片高. 
	 */
	private int height;
	/**
	 * radio:缩放比例. 
	 */
	private double radio;
	/**
	 * inputStream:字节流. 
	 */
	private InputStream inputStream;
	/**
	 * newWidth:新图片宽. 
	 */
	private int newWidth;
	/**
	 * newHeight:新图片高. 
	 */
	private int newHeight;
	/**
	 * bufferedImage:图片对象. 
	 */
	private BufferedImage bufferedImage;
	
	
	
	
	public ImageBean(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public ImageBean(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	/**
	 * 获取 width:原图片宽
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * 设置 width:原图片宽 
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * 获取 height:原图片高.
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * 设置 height:原图片高. 
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * 获取 radio:缩放比例.
	 * @return the radio
	 */
	public double getRadio() {
		return radio;
	}
	/**
	 * 设置 radio:缩放比例. 
	 * @param radio the radio to set
	 */
	public void setRadio(double radio) {
		this.radio = radio;
	}
	/**
	 * 获取 inputStream:流.
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}
	/**
	 * 设置 inputStream:流. 
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	/**
	 * 获取 newWidth:新图片宽.
	 * @return the newWidth
	 */
	public int getNewWidth() {
		return newWidth;
	}
	/**
	 * 设置 newWidth:新图片宽. 
	 * @param newWidth the newWidth to set
	 */
	public void setNewWidth(int newWidth) {
		this.newWidth = newWidth;
	}
	/**
	 * 获取 newHeight:新图片高.
	 * @return the newHeight
	 */
	public int getNewHeight() {
		return newHeight;
	}
	/**
	 * 设置 newHeight:新图片高. 
	 * @param newHeight the newHeight to set
	 */
	public void setNewHeight(int newHeight) {
		this.newHeight = newHeight;
	}
	/**
	 * 获取 bufferedImage:图片对象.
	 * @return the bufferedImage
	 */
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	/**
	 * 设置 bufferedImage:图片对象. 
	 * @param bufferedImage the bufferedImage to set
	 */
	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	
}
