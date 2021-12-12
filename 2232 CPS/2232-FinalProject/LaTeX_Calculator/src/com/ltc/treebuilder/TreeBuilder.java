package com.ltc.treebuilder;

import com.ltc.tree.ValueNode;
import com.ltc.tree.functions.FunctionNode;
import com.ltc.tree.functions.FunctionNodeMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Pattern;

import static com.ltc.tree.functions.FunctionNodeMapper.*;

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

    public FunctionNode build() {
        Stack<Double> numberStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        StringBuilder latexOperator = new StringBuilder();

        boolean inputtingLatexOperator = false;

        for (int i = 0; i < latex.length(); i++) {
            final char ch = latex.charAt(i);
            final String chStr = Character.toString(ch);

            // Inputting LaTeX operator
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
                    inputtingLatexOperator = false;
                } else {
                    latexOperator.append(ch);
                }
            }

            // Inputting One-character operator
            if (FunctionNodeMapper.isDefinedSign(chStr))
                operatorStack.push(chStr);
            // Inputting Number
            if (Character.isDigit(ch)) {
                final double newNumber = parseNumber(latex, i);
                numberStack.push(newNumber);

                if (isIntegerForDouble(newNumber))
                    i += Integer.toString((int) newNumber).length() - 1;
                else
                    i += Double.toString(newNumber).length() - 1;
            }
        }

        try {
            return makeTree(numberStack, operatorStack);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private FunctionNode makeTree(Stack<Double> numberStack, Stack<String> operatorStack) throws ClassNotFoundException {
        // stack is not suitable here, so we transform stack into array
        ArrayList<Double> numbers = new ArrayList<>(numberStack);
        ArrayList<String> operators = new ArrayList<>(operatorStack);

        FunctionNode root = null;

        // when the precedence of the front one is less than the next one, add the operator of the front node to the
        // left of current node; if the precedence of front is more than the next one, add the operator of the front
        // mode to the top of current node. if the precedence of the front node is equal to the nxt node, add the
        // operator of the front node to the top of current node.
        // The precedence of the unary sign is always larger than the binary operator.
        for (int i = operators.size() - 1, j = numbers.size() - 1; i >= 0; i--) {
            final String currentOperator = operators.get(i);
            final ValueNode currentValueNode = new ValueNode(numbers.get(j--));

            if (root == null) {
                if (isBinarySign(currentOperator)) {
                    root = map2binary(currentOperator, null, currentValueNode);
                } else if (isUnarySign(currentOperator)) {
                    root = map2unary(currentOperator, currentValueNode);
                } else {
                    throw new ClassNotFoundException("Undefined Operators");
                }
                continue;
            }

            final String previousOperator = operators.get(i+1);

            if (isUnarySign(previousOperator)) {
                if (isBinarySign(currentOperator)) {
                    root = map2binary(currentOperator, null, root);
                } else if (isUnarySign(currentOperator)) {
                    root = map2unary(currentOperator, root);
                } else {
                    throw new ClassNotFoundException("Undefined Operators");
                }
                continue;
            }

            if (isUnarySign(currentOperator)) {
                root.left = map2unary(currentOperator, currentValueNode);
                continue;
            }

            // like `+` v.s. `*`
            if (precedence(currentOperator) < precedence(previousOperator)) {
                root.append(currentValueNode);
                root = map2binary(currentOperator, null, root);
            } else { // like `*` v.s. `+` or `+` v.s. `+`
                root.append(map2binary(currentOperator, null, currentValueNode));
            }
        }

        assert root != null;
        root.append(new ValueNode(numbers.get(0)));

        return root;
    }


    public double parseNumber(String sequence, int offset) {
        StringBuilder stringBuilder = new StringBuilder();
        while(offset < sequence.length() && Character.isDigit(sequence.charAt(offset)))
            stringBuilder.append(sequence.charAt(offset++));

        if (offset < sequence.length() && sequence.charAt(offset) == '.') {
            stringBuilder.append(sequence.charAt(offset++));
            while(offset < sequence.length() && Character.isDigit(sequence.charAt(offset)))
                stringBuilder.append(sequence.charAt(offset++));
        }

        return Double.parseDouble(stringBuilder.toString());
    }

    private boolean isIntegerForDouble(double number) {
        double eps = 1e-10;
        return number - Math.floor(number) < eps;
    }
}
