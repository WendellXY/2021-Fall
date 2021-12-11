package com.ltc.base;

import com.ltc.preprocesser.PreProcessor;

public class Test {
    private static int testCount = 0;

    private static void CheckPassOrFail(boolean condition) {
        System.out.println(" [" + ++testCount + "] " + (condition ? "Pass" : "Fail"));
    }

    private static void testPreProcessor() {
        System.out.println("Testing PreProcessor Modules.");

        PreProcessor processor = new PreProcessor();

        // Test removeUnusedBeginEnd(String)
        String latex = "\\begin{document}\\begin{equation}1+1=4\\frac{1}{2}\\end{equation}\\end{document}";
        String result = processor.removeUnusedBeginEnd(latex);
        CheckPassOrFail(result.equals("1+1=4\\frac{1}{2}"));

        latex = "\\begin{document}\\begin{equation}1+1=2\\end{equation}\\end{document}";
        result = processor.removeUnusedBeginEnd(latex);
        CheckPassOrFail(result.equals("1+1=2"));

        latex = "\\begin{document}\\begin{array}{}1+1=2\\end{array}\\end{document}";
        result = processor.removeUnusedBeginEnd(latex);
        CheckPassOrFail(result.equals("\\begin{array}{}1+1=2\\end{array}"));

        latex = "\\int_{-1}^1f(x)dx \\approx 0.\\overline{5}f (−0.774596669)+0.\\overline{8}f(0)+0.\\overline{5}f ((0.774596669) = 0.64270";
        result = processor.removeUnusedBeginEnd(latex);
        CheckPassOrFail(result.equals(latex));

        // Test fillMissingBracesForSign
        latex = "1^2=1";
        result = processor.fillMissingBracesForSign(latex);
        CheckPassOrFail(result.equals("1^{2}=1"));

        latex = "1^2+3^2-10^{10}";
        result = processor.fillMissingBracesForSign(latex);
        CheckPassOrFail(result.equals("1^{2}+3^{2}-10^{10}"));

        // Test convertLaTeXFractionToDivision
        latex = "\\frac{1}{2}";
        result = processor.convertLaTeXFractionToDivision(latex);
        CheckPassOrFail(result.equals("((1)/(2))"));

        latex = "\\frac{\\sin{1}}{2}";
        result = processor.convertLaTeXFractionToDivision(latex);
        CheckPassOrFail(result.equals("((\\sin{1})/(2))"));

        latex = "\\frac{\\frac{1}{2}}{3}";
        result = processor.convertLaTeXFractionToDivision(latex);
        CheckPassOrFail(result.equals("((((1)/(2)))/(3))"));

        System.out.println("PreProcessor Test Done.");
    }

    public static void test() {
        System.out.println("Testing Modules...");
	    testPreProcessor();
	    System.out.println("All Modules Test Done");
    }
}
