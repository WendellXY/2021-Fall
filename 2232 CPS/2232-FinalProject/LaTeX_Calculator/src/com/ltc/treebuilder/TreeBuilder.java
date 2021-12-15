package com.ltc.treebuilder;

import com.ltc.tree.ValueNode;
import com.ltc.tree.functions.FunctionNode;
import com.ltc.tree.functions.FunctionNodeMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Pattern;

import static com.ltc.tree.functions.FunctionNodeMapper.*;

/**
 * The Tree Builder layer is the most critical of all layers and is the core component of this LaTeX Calculator. At this
 * layer, the program reads the LaTeX expressions and stores them in the symbolic and numeric stacks, respectively,
 * after which it calls another method for building the LaTeX expression tree to complete and return the tree.
 */
public record TreeBuilder(String latex) {
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

    /**
     * Unlike the error function, the warning function would not stop the program, instead it is just print out the
     * message when the condition matches.
     * @param condition if the condition is `true`, the program would print out the string `str`; else it would do
     *                  nothing
     * @param str the debug string when the warning condition matches
     */
    public static void warning(boolean condition, String str) {
        if (condition)
            System.out.println("Warning: " + str);
    }

    /**
     * The error function is used to check whether the program runs properly, when the condition matched or it is true,
     * the program would stop with the status code 1 and print out the result.
     * @param condition If the condition is `true`, the program would print out the string `str` and stop the program
     *                  with the status code 1; else it would do nothing.
     * @param str the debug string when the error happened.
     */
    public static void error(boolean condition, String str) {
        if (condition) {
            System.out.println("Error: " + str);
            System.exit(1);
        }
    }

    /**
     * This function is the main function of the whole Tree Builder class, it is used to parse the LaTeX expression i
     * nto the number stack and the operator stack and then call the makeTree method to build the LaTeX expression and
     * return the root of the tree to the caller.
     * @return the root of the build LaTeX expression tree
     */
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

        System.out.println(numberStack);
        System.out.println(operatorStack);

        try {
            return makeTree(numberStack, operatorStack);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method is used to build the LaTeX expression tree by the number stack and the operator stack
     * @param numberStack the number stack of the LaTeX expression, the element order should be last number of the latex
     *                    expression on the top, and the first on the bottom
     * @param operatorStack the operator stack of the LaTeX expression, the element order should be the latex operator
     *                      of the latex expression on the top, and the first LaTeX operator on the bottom
     * @return the LaTeX expression tree based on the number stack and the operator stack
     * @throws ClassNotFoundException when the LaTeX operator is undefined
     */
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
            // when the root is null or when be in the first iteration of the for-loop
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

            final String previousOperator = operators.get(i + 1);

            if (isUnarySign(previousOperator)) {
                if (isBinarySign(currentOperator)) {
                    root = map2binary(currentOperator, null, root);
                } else if (isUnarySign(currentOperator)) {
                    root = map2unary(currentOperator, root);
                } else {
                    throw new ClassNotFoundException("Undefined Operators");
                }
                // because j would automatically minus 1, however, we did not use the currentValueNode, which means the
                // value of j should keep the same as previous, so here we increase j by 1.
                j++;
                continue;
            }

            if (isUnarySign(currentOperator)) {
                root.append(map2unary(currentOperator, currentValueNode));
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
        // make sure root is not null
        assert root != null;
        // the last number of the number may not be appended into the expression tree when the last operator is not
        // a unary sign or is a binary sign.
        if (!isUnarySign(operators.get(0)))
            root.append(new ValueNode(numbers.get(0)));

        return root;
    }


    /**
     * This function is used to parse the number from the String sequence by the offset. When parse a number, it would
     * stop and return it.
     * @param sequence the String sequence that contains a number
     * @param offset the start point of parsing the number
     * @return the first number of the sequence start at index offset
     */
    public double parseNumber(String sequence, int offset) {
        StringBuilder stringBuilder = new StringBuilder();
        while (offset < sequence.length() && Character.isDigit(sequence.charAt(offset)))
            stringBuilder.append(sequence.charAt(offset++));

        if (offset < sequence.length() && sequence.charAt(offset) == '.') {
            stringBuilder.append(sequence.charAt(offset++));
            while (offset < sequence.length() && Character.isDigit(sequence.charAt(offset)))
                stringBuilder.append(sequence.charAt(offset++));
        }

        return Double.parseDouble(stringBuilder.toString());
    }

    /**
     * Check whether the double could be transformed into an integer
     * @param number the double number to check whether it is an integer or not
     * @return `true`, if the number is actually an integer, otherwise, return `false`.
     */
    private boolean isIntegerForDouble(double number) {
        double eps = 1e-10;
        return number - Math.floor(number) < eps;
    }
}
