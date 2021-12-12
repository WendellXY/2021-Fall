package com.ltc.tree.functions;

import com.ltc.tree.ValueNode;

public class FunctionNodeMapper {
    public static boolean isUnarySign(String operation) {
        return switch (operation) {
            case "\\sin", "\\cos", "\\tan" -> true;
            default -> false;
        };
    }

    public static FunctionNode map2unary(String operation, ValueNode left) {
                switch (operation) {
            case "\\sin":
                return new TriSinNode(left, null);
            case "\\cos":
                return new TriCosNode(left, null);
            case "\\tan":
                return new TriTanNode(left, null);
            default:
                System.out.println("Cannot find a binary operation " + operation);
                return null;
        }
    }

    public static boolean isBinarySign(String operation) {
        return switch (operation) {
            case "+", "-", "^", "*", "/" -> true;
            default -> false;
        };
    }

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
