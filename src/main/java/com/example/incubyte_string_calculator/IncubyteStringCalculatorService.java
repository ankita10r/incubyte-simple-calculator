package com.example.incubyte_string_calculator;

import org.springframework.stereotype.Component;

@Component
public class IncubyteStringCalculatorService {

    //TC01 Empty String
    public int add(String input) {
        if (input == null || input.isEmpty()) return 0;
        // further cases not implemented yet
        return 0;
    }

}
