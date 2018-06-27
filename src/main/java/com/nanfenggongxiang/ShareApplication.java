package com.nanfenggongxiang;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableSwagger2Doc   这个注解代表开启api页面   注上之后去  /swagger.html查看api页面
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.nanfenggongxiang")
public class ShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareApplication.class, args);
	}
}
