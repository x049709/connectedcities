package com.coolmix.userservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coolmix.userservice.utils.CityListChecker;

@Configuration
public class CityFileLoader {

	@Bean
	public CommandLineRunner dataLoader() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
		         if (args.length == 0) {
		             System.out.println("No files to load...");		       	 
		         } else {

		        	 CityListChecker cityListChecker = CityListChecker.getInstance();
		        	 cityListChecker.loadFile(args[0]);
		         }
			}
		};
	}

}
