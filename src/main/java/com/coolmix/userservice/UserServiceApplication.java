package com.coolmix.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
         ConfigurableApplicationContext ctx = SpringApplication.run(UserServiceApplication.class, args);
         if (args.length == 0) {
             System.out.println("Shutting down...");
             System.out.println("Run as java -cities.txt UserServiceApplication");
            ctx.close();       	 
         }
	}
	
}


