package net.jeeshop.core.image.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public interface BaseBuilderService {
	
	public BufferedImage getBufferedImage();
	
	public OutputStream getOutputStream(String newFilePath);
	
	public ByteArrayOutputStream getByteArrayOutputStream();
	
	public byte[] getByte();
	
	public void createFile(String outFilepath);
	
}
