package com.ltc.tree.functions;

import com.ltc.tree.ValueNode;

public class FunctionNodeMapper {
    /**
     * Check the operator is a unary sign or not
     * @param operation the operation to check whether it is a unary sign or not
     * @return `true` if the operation is a unary sign, otherwise, return `false`
     */
    public static boolean isUnarySign(String operation) {
        return switch (operation) {
            case "sin", "cos", "tan" -> true;
            default -> false;
        };
    }

    /**
     * Transform the String operation to the LaTeX Function node which could be processed by the program
     * @param operation the operation or the name of the Function node
     * @param left the left operand or the only operand of the operation since it is a unary operator
     * @return The corresponding FunctionNode of the given operation
     */
    public static FunctionNode map2unary(String operation, ValueNode left) {
                switch (operation) {
            case "sin":
                return new TriSinNode(left, null);
            case "cos":
                return new TriCosNode(left, null);
            case "tan":
                return new TriTanNode(left, null);
            default:
                System.out.println("Cannot find a binary operation " + operation);
                return null;
        }
    }

    /**
     * Check the operator is a binary sign or not
     * @param operation the operation to check whether it is a binary sign or not
     * @return `true` if the operation is a binary sign, otherwise, return `false`
     */
    public static boolean isBinarySign(String operation) {
        return switch (operation) {
            case "+", "-", "^", "*", "/" -> true;
            default -> false;
        };
    }

    /**
     * Transform the String operation to the LaTeX Function node which could be processed by the program
     * @param operation the operation or the name of the Function node
     * @param left the left operand of the operation
     * @param right the right operand of the operation
     * @return The corresponding FunctionNode of the given operation
     */
    public static FunctionNode map2binary(String operation, ValueNode left, ValueNode right) {
        switch (operation) {
            case "+":
                return new AdditionNode(left, right);
            case "-":
                return new AdditionNode(left, right.toNegative());
            case "*":
                return new MultiplicationNode(left, right);
            case "/":
                return new MultiplicationNode(left, right.toReciprocal());
            case "^":
                return new PowerNode(left, right);
            default:
                System.out.println("Cannot find a binary operation " + operation);
                return null;
        }
    }

    /**
     * Check whether this operation is predefined or not
     * @param operation the operation to check
     * @return `true` if the given operation is predefined, otherwise, return `false`
     */
    public static boolean isDefinedSign(String operation) {
        return isUnarySign(operation) || isBinarySign(operation);
    }

    public static int precedence(String operation) {
        return switch (operation) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }
}
