package com.ltc.tree.functions;

import com.ltc.tree.ValueNode;

public class FunctionNodeMapper {
    public static boolean isUnarySign(String operation) {
        switch (operation) {
            case "\\sin":
            case "\\cos":
            case "\\tan":
                return true;
            default:
                return false;
        }
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
        switch (operation) {
            case "+":
            case "-":
            case "^":
            case "*":
            case "/":
                return true;
            default:
                return false;
        }
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
        switch (operation) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }
}
