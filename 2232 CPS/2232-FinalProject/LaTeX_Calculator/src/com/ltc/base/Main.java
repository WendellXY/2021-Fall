package com.ltc.base;

import com.ltc.preprocesser.PreProcessor;
import com.ltc.tree.functions.FunctionNode;
import com.ltc.treebuilder.TreeBuilder;

import java.util.Scanner;

public class Main {
    private static String readLaTeX() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Mathematical LaTeX Expression Below: ");

        String latex = scanner.nextLine();
        System.out.println("Received: \n" + latex);

        scanner.close();

        return latex;
    }

    public static void main(String[] args) {
        Test.test();

	    System.out.println("Welcome to LaTeX Calculator.");

        String latex = readLaTeX();

        // PreProcess the LaTeX string before build the expression tree
        PreProcessor preProcessor = new PreProcessor();
        latex = preProcessor.process(latex);

        TreeBuilder treeBuilder = new TreeBuilder(latex);

        FunctionNode tree = treeBuilder.build();

        System.out.println(tree.process());
    }
}
