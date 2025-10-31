package com.example.incubyte_string_calculator;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Component
public class IncubyteStringCalculatorService {

    public int add(String input) {

        List<Integer> negatives = new ArrayList<>();

        // TC01 - Empty String
        if (input == null || input.isEmpty()) return 0;

        boolean customDelimiter = false;
        String numbersPart = input;
        String splitRegex = "[,\\n]";  // Default delimiters: comma and newline

        // TC07–TC11: Custom Delimiter Parsing
        if (input.startsWith("//")) {
            customDelimiter = true;
            int idx = input.indexOf('\n');
            if (idx == -1)
                throw new IllegalArgumentException("Invalid input: missing newline after delimiter header");

            String header = input.substring(2, idx);
            numbersPart = input.substring(idx + 1);

            // TC09 Multi-Character / TC10 Multiple Delimiters / TC11 Multiple Long Delimiters
            Matcher matcher = Pattern.compile("\\[(.*?)]").matcher(header);
            StringBuilder regex = new StringBuilder();

            if (matcher.find()) {
                // TC09 Multi-Character Delimiter
                regex.append(Pattern.quote(matcher.group(1)));

                // TC10 Multiple Delimiters/ TC11 Multiple Long Delimiters
                while (matcher.find()) {
                    regex.append("|").append(Pattern.quote(matcher.group(1)));
                }
                splitRegex = regex.toString();
            } else {
                splitRegex = Pattern.quote(header); // TC07 Custom Delimiter / TC08 Custom Symbol Delimiter
            }
        }


        // TC06 - Invalid Format Checks [Only for default delimiters (not custom ones)]
        if (!customDelimiter) {
            if (numbersPart.startsWith(",") || numbersPart.startsWith("\n") ||
                    numbersPart.endsWith(",") || numbersPart.endsWith("\n")) {
                throw new IllegalArgumentException("Invalid input: leading or trailing delimiter");
            }

            if (numbersPart.matches(".*(,\\n|\\n,|,,|\\n\\n).*")) {
                throw new IllegalArgumentException("Invalid input: consecutive delimiters");
            }
        }

        //Common to all TCs
        String[] parts = numbersPart.split(splitRegex);
        int sum = 0;

        for (String p : parts) {
            p = p.trim();
            if (p.isEmpty()) {
                throw new IllegalArgumentException("Invalid input: empty token");
            }
            //TC12 Single Negative Number
            if (Integer.parseInt(p) < 0) negatives.add(Integer.parseInt(p));

            sum += Integer.parseInt(p);
        }
        // TC13 Multiple Negatives
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed " + negatives.stream().map(String::valueOf).collect(Collectors.joining(",")));
        }

        return sum;
    }
}
