package com.ltc.treebuilder;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.regex.Pattern;

import static com.ltc.tree.functions.FunctionNodeMapper.isDefinedSign;

public class TreeBuilder {
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

    public final String latex;

    public TreeBuilder(String latex) {
        this.latex = latex;
    }

    public static void warning(boolean condition, String str) {
        if (condition)
            System.out.println("Warning: " + str);
    }

    public static void error(boolean condition, String str) {
        if (condition) {
            System.out.println("Error: " + str);
            System.exit(1);
        }
    }

    public void build() {
        Stack<Double> numberStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        StringBuilder latexOperator = new StringBuilder();

        boolean inputtingLatexOperator = false;

        for (int i = 0; i < latex.length(); i++) {
            final char ch = latex.charAt(i);
            final String chStr = Character.toString(ch);

            if (ch == '\\') {
                inputtingLatexOperator = true;
                continue;
            }

            if (inputtingLatexOperator) {
                warning(ch == ' ', "The result might contain some errors.");
                if (ch == ' ' || ch == '{') {
                    error(!isDefinedSign(latexOperator.toString()), "Undefined LaTeX operator.");
                    operatorStack.push(latexOperator.toString());
                    latexOperator = new StringBuilder();
                } else {
                    latexOperator.append(ch);
                }
            }
        }
    }

    private double parseNumber(String sequence, int offset){
        StringBuilder stringBuilder = new StringBuilder();
        while(offset < sequence.length() && Character.isDigit(sequence.charAt(offset))){
            stringBuilder.append(sequence.charAt(offset));
            offset++;
        }

        if (sequence.charAt(offset) == '.') {
            stringBuilder.append(sequence.charAt(offset));
            while(offset < sequence.length() && Character.isDigit(sequence.charAt(offset))){
                stringBuilder.append(sequence.charAt(offset));
                offset++;
            }
        }

        return Double.parseDouble(stringBuilder.toString());
    }
}
