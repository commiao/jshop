package net.jeeshop.core.image.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.coobird.thumbnailator.Thumbnails.Builder;
import net.jeeshop.core.image.factory.InputStreamBuilderFactory;
import net.jeeshop.core.image.service.BaseBuilderService;

public class InputStreamBuilderHandle implements BaseBuilderService{
	
	private Builder<? extends InputStream> streamBuilders;
	
	public InputStreamBuilderHandle(Builder<? extends InputStream> streamBuilders){
		this.streamBuilders = streamBuilders;
	}
	
	@Override
	public BufferedImage getBufferedImage() {
		try {
			return streamBuilders.asBufferedImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public OutputStream getOutputStream(String newFilePath) {
		try {
			OutputStream out = new FileOutputStream(newFilePath);
			streamBuilders.toOutputStream(out);
			return out;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ByteArrayOutputStream getByteArrayOutputStream() {
		//转字节流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			streamBuilders.toOutputStream(out);
			return out;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public byte[] getByte() {
		//转字节流
		ByteArrayOutputStream out = getByteArrayOutputStream();
		return out.toByteArray();
	}

	@Override
	public void createFile(String outFilepath) {
		try {
			streamBuilders.toFile(outFilepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		FileInputStream ins = new FileInputStream(new File("D:\\11.jpg"));
		InputStreamBuilderFactory inf = new InputStreamBuilderFactory(ins);
		inf.scalePic(1f);
		inf.rotatePic(90);
		inf.changeType("png");
		InputStreamBuilderHandle isbh = new InputStreamBuilderHandle(inf.getInstance());
		isbh.createFile("D:\\11_2");
	}

}
