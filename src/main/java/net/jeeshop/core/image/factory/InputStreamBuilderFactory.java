package net.jeeshop.core.image.factory;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import net.jeeshop.core.image.GraphicsUtil;
import net.jeeshop.core.image.util.ImageUtil;

public class InputStreamBuilderFactory{
	private Builder<? extends InputStream> streamBuilders = null;
	private InputStream inputStream;
	
	public InputStreamBuilderFactory(InputStream inputStream) {
		this.inputStream = inputStream;
		getInstance();
	}

	public Builder<? extends InputStream> getInstance(){
		if(streamBuilders == null){
			streamBuilders = Thumbnails.of(inputStream);
		}
		return streamBuilders;
	}
	
	/**
	 * 压缩至指定图片尺寸（例如：横400高300），保持图片不变形，多余部分裁剪掉
	 * 
	 * 这种情况复杂些，既不能用size()方法（因为横高比不一定是4/3，这样压缩后的图片横为400或高为300），也不能用forceSize()方法。
	 * 首先判断横高比，确定是按照横400压缩还是高300压缩，压缩后按中心400*300的区域进行裁剪，这样得到的图片便是400*300的裁剪后缩略图。
	 * 使用size()或forceSize()方法时，如果图片比指定的尺寸要小（比如size(400, 300)，而图片为40*30），则会拉伸到指定尺寸。
	 * @param width 400
	 * @param height 300
	 */
	public void changePic(int width,int height){
		BufferedImage image = ImageUtil.getBufferedImage(inputStream);
		int imageWidth = image.getWidth();
		int imageHeitht = image.getHeight();
		if ((float) height / width != (float) imageWidth / imageHeitht) {
			if (imageWidth > imageHeitht) {
				streamBuilders.height(height);
			} else {
				streamBuilders.width(width);
			}
			
			try {
//				cutPic(width, height);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			streamBuilders.size(width, height);
		}
	}
	
	/**
	 * 保持图片比例，压缩图片尺寸
	 * @param scale (例：0.5f --> 50%  生成缩略图的大小是原来的50%)
	 */
	public void scalePic(double scale){
		streamBuilders.scale(scale);
	}
	
	/**
	 * 图片尺寸不变，压缩图片质量
	 * @param quality 0.0~1.0，1为最高质量 (例：0.8f --> 80%的质量压缩的缩略图)
	 */
	public void compressPic(float quality){
		streamBuilders.outputQuality(quality);
	}
	
	/**
	 * 旋转图片
	 * @param angle 180 90(顺时针旋转90°) -90 -180
	 */
	public void rotatePic(double angle){
		streamBuilders.rotate(angle);
	}
	
	/**
	 * 修改图片格式
	 * @param format "png"
	 */
	public void changeType(String format){
		streamBuilders.outputFormat(format);
	}
	
	/**
	 * 添加图片水印
	 */
	public void addPic(){
		Position position = Positions.CENTER;// 位置
		BufferedImage image = ImageUtil.getBufferedImage(inputStream); // 水印图片
		float opacity = 0.25f; // 透明度
		streamBuilders.watermark(position, image, opacity);
	}
	
	/**
	 * 添加文字水印
	 * @param word
	 */
	public void addWord(String word){
		Position position = Positions.BOTTOM_RIGHT;// 位置 右下角
		BufferedImage bi = GraphicsUtil.makeWordToPic(word);
		float opacity = 1.0f;// 透明度 (例：0.5f--> 50%的透明度)
		streamBuilders.watermark(position, bi, opacity);
	}
	
}
