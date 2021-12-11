package com.ltc.strreader;

import com.ltc.tree.functions.FunctionNodeMapper;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class StrReader {
    /**
     * @param str the string to check if it is numeric
     * @return true if the str is numeric else false
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;

        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;
        }

        return pattern.matcher(bigStr).matches();
    }

    SimpleStack stack = new SimpleStack();

    public StrReader() { }

    /**
     * Read the string until it read the first usable sign or number
     *
     * @param str String to read
     * @return the string without the first usable sign or number (and also the all character before it)
     */
    public String read(String str) {
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '\\') {
                buffer = new StringBuilder();
                continue;
            }

            if (FunctionNodeMapper.isBinarySign(buffer.toString())) {
                stack.push(buffer.toString());

                return str.substring(i);
            }
        }

        return str;
    }
}
