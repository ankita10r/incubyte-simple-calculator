package com.example.incubyte_string_calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IncubyteStringCalculatorApplicationTests {

	@Test
	void contextLoads() {
	}
	private IncubyteStringCalculatorService calc;

	@BeforeEach
	void setup() {
		calc = new IncubyteStringCalculatorService();
	}

	//TC01 Empty String
	@Test
	void tc01_emptyInput_returnsZero() {
		assertEquals(0, calc.add(""));
	}

	//TC02 Single Number
	@Test
	void tc02_singleNumber_returnsNumber() {
		assertEquals(1, calc.add("1"));
	}


}
