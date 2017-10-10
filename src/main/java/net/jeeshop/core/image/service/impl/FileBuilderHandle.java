package net.jeeshop.core.image.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.coobird.thumbnailator.Thumbnails.Builder;
import net.jeeshop.core.image.factory.FileBuilderFactory;
import net.jeeshop.core.image.service.BaseBuilderService;

public class FileBuilderHandle implements BaseBuilderService{
	
	private Builder<File> fileBuilders;
	
	public FileBuilderHandle(Builder<File> fileBuilders){
		this.fileBuilders = fileBuilders;
	}
	@Override
	public BufferedImage getBufferedImage(){
		try {
			return fileBuilders.asBufferedImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public OutputStream getOutputStream(String newFilePath){
		try {
			OutputStream out = new FileOutputStream(newFilePath);
			fileBuilders.toOutputStream(out);
			out.close();
			return out;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public ByteArrayOutputStream getByteArrayOutputStream(){
		//转字节流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			fileBuilders.toOutputStream(out);
			return out;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public byte[] getByte(){
		//转字节流
		ByteArrayOutputStream out = getByteArrayOutputStream();
		return out.toByteArray();
	}
	@Override
	public void createFile(String outFilepath){
		try {
			fileBuilders.toFile(outFilepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FileBuilderFactory f = new FileBuilderFactory("D:\\11.jpg");
		f.scalePic(0.5f);
		BaseBuilderService service = new FileBuilderHandle(f.getInstance());
		service.createFile("D:\\11_1.png");
	}
	
}
