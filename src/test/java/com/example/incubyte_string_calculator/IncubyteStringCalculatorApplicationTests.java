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

	//TC03 Two Numbers
	@Test
	void tc03_twoNumbers_commaSeparated() {
		assertEquals(3, calc.add("1,2"));
	}

	//TC04 Multiple Numbers
	@Test
	void tc04_multipleNumbers_returnSum() {
		assertEquals(10, calc.add("1,2,3,4"));
	}

	//TC05 Newline as Separator along with a comma
	@Test
	void tc05_newline_and_comma() {
		assertEquals(6, calc.add("1\n2,3"));
	}

	//TC06 Invalid Format
	@Test
	void tc06_invalidTrailingDelimiter_throws() {
		assertThrows(IllegalArgumentException.class, () -> calc.add("1,\n"));
	}


	/// TC07 - Custom single-character delimiter
	@Test
	void tc07_customSingleDelimiter() {
		assertEquals(3, calc.add("//;\n1;2"));
	}

	// TC08 - Custom symbol delimiter
	@Test
	void tc08_customSymbolDelimiter() {
		assertEquals(5, calc.add("//#\n2#3"));

	}

	// TC09 - Multi-character delimiter
	@Test
	void tc09_multiCharacterDelimiter() {
		assertEquals(6, calc.add("//[***]\n1***2***3"));
	}

	// TC10 - Multiple delimiters
	@Test
	void tc10_multipleDelimiters() {
		assertEquals(6, calc.add("//[*][%]\n1*2%3"));

	}

	// TC11 - Multiple long delimiters
	@Test
	void tc11_multipleLongDelimiters() {
		assertEquals(6, calc.add("//[**][%%]\n1**2%%3"));

	}

	//TC12 Single Negative Number
	@Test
	void tc12_singleNegative_throws() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> calc.add("1,-2"));
		assertTrue(ex.getMessage().contains("-2"));
	}


	//TC13 Multiple Negatives
	@Test
	void tc13_multipleNegatives_reportAll() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> calc.add("1,-2,-4,3"));
		assertTrue(ex.getMessage().contains("-2,-4"));
	}

	//TC14 Ignore Numbers > 1000
	@Test
	void tc14_ignoreNumbersGreaterThan1000() {
		assertEquals(2, calc.add("2,1001"));
	}

	//TC15 Include 1000
	@Test
	void tc15_includeThousand() {
		assertEquals(1002, calc.add("1000,2"));
	}

	// TC16 Whitespace Handling
	@Test
	void tc16_whitespaceHandling() {
		assertEquals(3, calc.add(" 1 , 2 "));
	}

	// TC17 Null Input
	@Test
	void tc17_nullInput_returnsZero() {
		assertEquals(0, calc.add(null));
	}

	// TC18 - Large input list (1000 times "1" => sum 1000)
	@Test
	void tc18_largeInputList() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 1000; i++) {
			if (i > 0) sb.append(",");
			sb.append("1");
		}
		assertEquals(1000, calc.add(sb.toString()));
	}
}
