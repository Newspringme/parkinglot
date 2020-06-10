package com.cnzk.service;

import org.springframework.web.multipart.MultipartFile;

public interface ChackphotoService
{
	String file(MultipartFile file);
	String finduser(String carnum);
	void caraddenter(String carnum,String starttime);

}
