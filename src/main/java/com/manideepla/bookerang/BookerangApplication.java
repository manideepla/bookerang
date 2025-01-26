package com.manideepla.bookerang;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookerangApplication {

	private static final Logger log = LoggerFactory.getLogger(BookerangApplication.class);

	public static void main(String[] args) {
		log.info("Starting BookerangApplication");
		SpringApplication.run(BookerangApplication.class, args);
	}

}
