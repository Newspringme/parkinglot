package com.cnzk.utils;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class ChangeBase64
{
	public static String multipartFileToBASE64(MultipartFile mFile) {
//		String base64EncoderImg= null;
//		try
//		{
//		BASE64Encoder bEncoder=new BASE64Encoder();
//		String[] suffixArra=mFile.getOriginalFilename().split("\\.");
//		String preffix="data:image/jpg;base64,".replace("jpg", suffixArra[suffixArra.length - 1]);
//
//		base64EncoderImg = preffix + bEncoder.encode(mFile.getBytes()).replaceAll("[\\s*\t\n\r]", "");
//		} catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//		return base64EncoderImg;
		BASE64Encoder base64Encoder =new BASE64Encoder();
		String base64EncoderImg = null;
		try
		{
			base64EncoderImg = base64Encoder.encode(mFile.getBytes());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return base64EncoderImg;
	}


}
