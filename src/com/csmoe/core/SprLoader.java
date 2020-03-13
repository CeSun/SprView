package com.csmoe.core;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import com.csmoe.core.struct.DSprite;
import com.csmoe.core.util.TypeUtil;


public class SprLoader {
	private DSprite pin = null;
	private short numi;
	private byte[] data;
	private byte[] color;
	private int frametype;
	private BufferedImage[][] imageBuffer = new BufferedImage[0][0];
	// buffer
	private byte[] buffer = new byte[4];
	private byte[] shortbuffer = new byte[2];
	private  float[] interval;
	public int getWidth() {
		return pin.width;
	}
	public int getHeight() {
		return pin.height;
	}
	public BufferedImage[][] getImageBuf() {
		return this.imageBuffer;
	}
	
	public int getFrameType() {
		return this.frametype;
	}
	public SprLoader(String filename ) throws Exception {
		// File f = new File(filename);
		
		pin = new DSprite();
		FileInputStream fis = new FileInputStream(filename);
		fis.read(buffer, 0, 4);
		pin.ident = TypeUtil.byteArrayToInt(buffer);
		fis.read(buffer, 0, 4);
		pin.version = TypeUtil.byteArrayToInt(buffer);
		fis.read(buffer, 0, 4);
		pin.type = TypeUtil.byteArrayToInt(buffer);
		fis.read(buffer, 0, 4);
		pin.texFormat = TypeUtil.byteArrayToInt(buffer);
		fis.read(buffer, 0, 4);
		pin.boundingradius = TypeUtil.byteArrayToFloat(buffer);
		fis.read(buffer, 0, 4);
		pin.width = TypeUtil.byteArrayToInt(buffer);
		fis.read(buffer, 0, 4);
		pin.height = TypeUtil.byteArrayToInt(buffer);
		fis.read(buffer, 0, 4);
		pin.numframes = TypeUtil.byteArrayToInt(buffer);
		fis.read(buffer, 0, 4);
		pin.beamlength = TypeUtil.byteArrayToFloat(buffer);
		fis.read(buffer, 0, 4);
		pin.synctype = TypeUtil.byteArrayToInt(buffer);

		fis.read(shortbuffer, 0, 2);
		numi = TypeUtil.byteArrayToShort(buffer);
		
		if( pin.ident != DSprite.IDSPRITEHEADER ) {
			System.out.println("文件类型不正确");
			throw new Exception("文件类型不正确");
		}
		if( pin.version != DSprite.SPRITE_VERSION ) {
			System.out.println("文件版本不是 Version:" + DSprite.SPRITE_VERSION);
			throw new Exception("文件版本不是 Version:" + DSprite.SPRITE_VERSION);
		}
		if( numi != 256 ) {
			System.out.println("颜色位数有问题");
			throw new Exception("颜色位数有问题");
		}
		color = new byte[numi * 3];
		fis.read(color, 0, numi * 3);
		imageBuffer = new BufferedImage[pin.numframes][0];
		interval = new float[pin.numframes];
		for (int i = 0; i < pin.numframes;i++ ) {
			fis.read(buffer, 0, 4);
			frametype = TypeUtil.byteArrayToInt(buffer);
			
			switch (frametype) {
			case 0:
				int w,h,x,y;
				TypeUtil.flag = true;
				fis.read(buffer, 0, 4);
				x = TypeUtil.byteArrayToInt(buffer);
				fis.read(buffer, 0, 4);
				y = TypeUtil.byteArrayToInt(buffer);
				fis.read(buffer, 0, 4);
				w = TypeUtil.byteArrayToInt(buffer);
				fis.read(buffer, 0, 4);
				h = TypeUtil.byteArrayToInt(buffer);
				
				imageBuffer[i] = new BufferedImage[1];
				imageBuffer[i][0] = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				int[] colorBuffer = new int[w * h];
				for (int j = 0 ; j < w * h; j ++ ) {
					int color  = fis.read();
						TypeUtil.flag = false;
						byte[] rgb = new byte[]{
							(byte)(0),
							this.color[color*3],
							this.color[color*3+1],
							this.color[color*3+2]
						}; 
						colorBuffer[j] = TypeUtil.byteArrayToInt(rgb);
						TypeUtil.flag = true;
						imageBuffer[i][0].setRGB(j % w, j / w,colorBuffer[j]);
				}
 				break;
			case 1:
			case 2:
				System.out.println("Group暂未支持");
				throw new Exception("Group暂未支持");
			}
		}
		
		
		
		
		
		fis.close();
	}
}

