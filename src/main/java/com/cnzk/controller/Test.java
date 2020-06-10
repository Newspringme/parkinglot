package com.cnzk.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author su
 * @date 2020/6/10-7:00
 */
public class Test
{
	public static void main(String[] args)
	{
		Date data=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(sdf.format(data));
	}
}
