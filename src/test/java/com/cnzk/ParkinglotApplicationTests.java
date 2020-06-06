package com.cnzk;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootTest
class ParkinglotApplicationTests
{
	@Resource
	private DataSource dataSource;

	@Test
	void contextLoads()
	{
		System.out.println("dataSource.getClass() ====== " + dataSource.getClass());
	}

}
