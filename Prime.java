package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.MappedInterceptor;

@RestController
@SpringBootApplication
public class Prime {

	@GetMapping("primes")
	Map<Long, List<Long>> findPrimeFactors(@RequestParam Long number) {
		return LongStream.range(1, number+1)
				.map(n -> ThreadLocalRandom.current().nextLong(100))
				.boxed()
				.distinct()
				.collect(Collectors.toMap(n -> n, n -> findPrimes(n)));
	}

	private List<Long> findPrimes(Long number) {
		final List<Long> primeFactors = new ArrayList<>();
		for (long i = 2; i <= number / i; i++) {
			while (number % i == 0) {
				primeFactors.add(i);
				number /= i;
			}
		}
		if (number > 1) {
			primeFactors.add(number);
		}
		return primeFactors;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Prime.class, args);
	}

	@Bean
	@Autowired
	public MappedInterceptor mappedInterceptor(HandlerInterceptor executionTimeInterceptor) {
		return new MappedInterceptor(new String[] { "/**" }, executionTimeInterceptor);
	}
}
