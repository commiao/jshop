package net.jeeshop.core.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;
import net.jeeshop.core.image.util.ImageUtil;

public class ThumbnailatorUtil {
	
//	String old_file_path = "F:\\image\\IMG_20131229_114806.png";
//	String file_type = "jpg";
//	String new_file_path = "F:\\image\\output\\IMG_20131229_114806";
	
	protected Builder<File> fileBuilders = null;
	private Builder<BufferedImage> imagesBuilders = null;
	BufferedImage image;
	
	protected void instanceBufferedImage(){
		image = null;
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
	public void zoomPic(int width,int height){
		int imageWidth = image.getWidth();
		int imageHeitht = image.getHeight();
		if ((float) height / width != (float) imageWidth / imageHeitht) {
			if (imageWidth > imageHeitht) {
				fileBuilders.height(height);
			} else {
				fileBuilders.width(width);
			}
			
			try {
				cutPic(width, height);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			fileBuilders.size(width, height);
		}
	}
	
	/**
	 * 裁剪图片
	 * @param width 400
	 * @param height 300
	 */
	public void cutPic(int width, int height){
		try {
			imagesBuilders = Thumbnails.of(fileBuilders.asBufferedImage()).sourceRegion(Positions.CENTER, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保持图片比例，压缩图片尺寸
	 * @param scale (例：0.5f --> 50%  生成缩略图的大小是原来的50%)
	 */
	public void scalePic(double scale){
		fileBuilders.scale(scale);
	}
	
	/**
	 * 图片尺寸不变，压缩图片质量
	 * @param quality 0.0~1.0，1为最高质量 (例：0.8f --> 80%的质量压缩的缩略图)
	 */
	public void compressPic(float quality){
		fileBuilders.outputQuality(quality);
	}
	
	/**
	 * 旋转图片
	 * @param angle 180 90(顺时针旋转90°) -90 -180
	 */
	public void rotatePic(double angle){
		fileBuilders.rotate(angle);
	}
	
	/**
	 * 修改图片格式
	 * @param format "png"
	 */
	public void changeType(String format){
		fileBuilders.outputFormat(format);
	}
	
	/**
	 * 添加图片水印
	 */
	public void addPic(String filePath){
		Position position = Positions.CENTER;// 位置
		BufferedImage image = ImageUtil.getBufferedImage(filePath); // 水印图片
		float opacity = 0.25f; // 透明度
		fileBuilders.watermark(position, image, opacity);
	}
	
	/**
	 * 添加文字水印
	 * @param word
	 */
	public void addWord(String word){
		Position position = Positions.BOTTOM_RIGHT;// 位置 右下角
		BufferedImage bi = GraphicsUtil.makeWordToPic(word);
		float opacity = 1.0f;// 透明度 (例：0.5f--> 50%的透明度)
		fileBuilders.watermark(position, bi, opacity);
	}
	
	public BufferedImage getBufferedImage(){
		try {
			if(imagesBuilders!=null){
				return imagesBuilders.asBufferedImage();
			}
			if(fileBuilders!=null){
				return fileBuilders.asBufferedImage();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public OutputStream getOutputStream(String newFilePath){
		try {
			OutputStream out = new FileOutputStream(newFilePath);
			if(imagesBuilders!=null){
				imagesBuilders.toOutputStream(out);
			}
			if(fileBuilders!=null){
				fileBuilders.toOutputStream(out);
			}
			out.close();
			return out;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ByteArrayOutputStream getByteArrayOutputStream(){
		//转字节流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			if(imagesBuilders!=null){
				imagesBuilders.toOutputStream(out);
			}
			if(fileBuilders!=null){
				fileBuilders.toOutputStream(out);
			}
			return out;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public byte[] getByte(){
		//转字节流
		ByteArrayOutputStream out = getByteArrayOutputStream();
		return out.toByteArray();
	}
	
	public void createFile(String outFilepath){
		try {
			if(imagesBuilders!=null){
				imagesBuilders.toFile(outFilepath);
			}
			if(fileBuilders!=null){
				fileBuilders.toFile(outFilepath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 对文件夹下所有图片生成缩略图
	 */
	public static void cutPic7(String filePath, int width, int height, String fileType){
		try {
			Thumbnails.of(new File(filePath).listFiles())         
					.size(width, height)         
					.outputFormat(fileType)         
					.toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
