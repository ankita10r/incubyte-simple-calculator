package com.example.incubyte_string_calculator;

import org.springframework.stereotype.Component;

@Component
public class IncubyteStringCalculatorService {


    public int add(String input) {
        //TC01 Empty String
        if (input == null || input.isEmpty()) return 0;
        //TC02 Single Number
        if (!input.contains(",") && !input.contains("\n")) {
            return Integer.parseInt(input.trim());
        }
        return 0;
    }




}
