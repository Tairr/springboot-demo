package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}



	public static void main(String[] args)  {


//		SpringApplication.run(DemoApplication.class, args);

		SpringApplication.run(DemoApplication.class, args);
//		StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
//
//		CountDownLatch latch=ctx.getBean(CountDownLatch.class);
//		template.convertAndSend("chat", "Hello from Redis!");
//
//		try {
//			latch.await();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		System.exit(0);
	}


}
