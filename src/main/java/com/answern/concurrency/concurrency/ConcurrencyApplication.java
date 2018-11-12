package com.answern.concurrency.concurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
//@EnableProxClient
public class ConcurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}


}
