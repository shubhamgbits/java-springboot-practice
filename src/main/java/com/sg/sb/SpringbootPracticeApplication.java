package com.sg.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

@SpringBootApplication
@EnableCaching
public class SpringbootPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPracticeApplication.class, args);
	}

}
