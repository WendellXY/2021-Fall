package com.ltc.base;

import com.ltc.preprocesser.PreProcessor;
import com.ltc.tree.functions.FunctionNode;
import com.ltc.treebuilder.TreeBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static String readLaTeX() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Mathematical LaTeX Expression Below: ");

        String latex = reader.readLine();
        System.out.println("Received: \n" + latex);

        return latex;
    }

    public static void main(String[] args) throws IOException {
        Test.test();

	    System.out.println("Welcome to LaTeX Calculator.");

        while (true) {
            String latex = readLaTeX();

            if (latex.equals("q") || latex.equals("quit"))
                break;

            // PreProcess the LaTeX string before build the expression tree
            PreProcessor preProcessor = new PreProcessor();
            latex = preProcessor.process(latex);

            TreeBuilder treeBuilder = new TreeBuilder(latex);

            FunctionNode tree = treeBuilder.build();

            assert tree != null;
            System.out.println("Result: " + tree.process());
        }
    }
}
