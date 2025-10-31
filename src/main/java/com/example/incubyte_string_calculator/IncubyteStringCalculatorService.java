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
        //TC03 Two Numbers
        //TC04 Multiple Numbers
        //TC05 Newline as Separator along with a comma
        String[] parts = input.split("[,\n]+"); // Splits on comma and newline regex
        int sum = 0;

        for (String p : parts) {
            p = p.trim();
            if (!p.isEmpty()) {
                sum += Integer.parseInt(p);
            }
        }

        return sum;
    }




}
