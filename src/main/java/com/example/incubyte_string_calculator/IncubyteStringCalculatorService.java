package com.example.incubyte_string_calculator;

import org.springframework.stereotype.Component;

@Component
public class IncubyteStringCalculatorService {

    public int add(String input) {

        // TC01 - Empty String
        if (input == null || input.isEmpty()) return 0;

         //TC06 Invalid Format Checks
            // TC06-A Invalid Format Checks (Detect invalid delimiter usage before processing)
        if (input.startsWith(",") || input.startsWith("\n") ||
                input.endsWith(",") || input.endsWith("\n")) {
            throw new IllegalArgumentException("Invalid input: leading or trailing delimiter");
            }
            // TC06-B Invalid Format Checks (Check for any consecutive delimiters: ,\n  \n,  ,,  \n\n)
            if (input.matches(".*(,\\n|\\n,|,,|\\n\\n).*")) {
            throw new IllegalArgumentException("Invalid input: consecutive delimiters");
            }


        // TC02 - Single Number
        if (!input.contains(",") && !input.contains("\n")) {
            return Integer.parseInt(input.trim());
        }

        // TC03–TC05 - Multiple Numbers (comma + newline separators)
        String[] parts = input.split("[,\n]");
        int sum = 0;

        for (String p : parts) {
            p = p.trim();

            // Extra defensive check — blank parts should not appear after valid input
            if (p.isEmpty()) {
                throw new IllegalArgumentException("Invalid input: empty token");
            }

            sum += Integer.parseInt(p);
        }

        return sum;
    }
}
