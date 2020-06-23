package com.cnzk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling//开启定时器总开关
@SpringBootApplication
public class ParkinglotApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ParkinglotApplication.class, args);
	}

}
