package com.csmoe.core.util;

import java.nio.ByteBuffer;

public class TypeUtil {
	public static boolean flag = true;
	public static int byteArrayToInt(byte[] b){

		for(int i = 0 ; i < b.length/2 && flag ; i ++) {
			int j = b.length - i - 1;
			b[i] = (byte) (b[i] ^ b[j]);
			b[j] = (byte) (b[i] ^ b[j]);
			b[i] = (byte) (b[i] ^ b[j]);
		}
		ByteBuffer buf=ByteBuffer.allocateDirect(b.length);
		buf.put(b);
		buf.rewind();
		return buf.getInt();
		
	}
	
	
	public static short byteArrayToShort(byte[] b){

		for(int i = 0 ; i < b.length/2 && flag ; i ++) {
			int j = b.length - i - 1;
			b[i] = (byte) (b[i] ^ b[j]);
			b[j] = (byte) (b[i] ^ b[j]);
			b[i] = (byte) (b[i] ^ b[j]);
		}
		ByteBuffer buf=ByteBuffer.allocateDirect(b.length);
		buf.put(b);
		buf.rewind();
		return buf.getShort();
	}
	
	public static float byteArrayToFloat(byte[] b){
		
		for(int i = 0 ; i < b.length/2 && flag ; i ++) {
			int j = b.length - i - 1;
			b[i] = (byte) (b[i] ^ b[j]);
			b[j] = (byte) (b[i] ^ b[j]);
			b[i] = (byte) (b[i] ^ b[j]);
		}
		
		ByteBuffer buf=ByteBuffer.allocateDirect(b.length);
		buf.put(b);
		buf.rewind();
		return buf.getFloat();
	}
	
	
	
}
