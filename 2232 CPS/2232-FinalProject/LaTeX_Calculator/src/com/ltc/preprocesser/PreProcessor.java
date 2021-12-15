package com.ltc.preprocesser;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The PreProcessor is used to remove and format the LaTeX string such that it could be processed from the StrReader
 *
 * The Preprocessor layer performs intermediate data processing, such as cleaning up invalid expressions and completing
 * irregular or simplified equations, to make Tree Builder work properly. In addition, Preprocessor converts some common
 * LaTeX expressions into mathematical expressions, for example, fractional expressions in LaTeX would be converted into
 * division form here.
 */
public class PreProcessor {
    public PreProcessor() { }

    /**
     * Common mark that is missed braces, like 1^2 should/could be written into 1^{2}. We only put '^' inside the array
     * since the others do not influence the result of the expression, or it should not appear inside the expression
     * that this command line application accept.
     */
    public char[] commonMarkThatMissBraces = {
        '^'
    };


    /**
     * Fill the missing braces of the latex string, for instance, the 1^2 would be transformed into 1^{2}
     *
     * You can put more characters inside the array property, if you need more character mark that perform this kind of
     * process.
     *
     * @param str the latex string to be processed
     * @return the latex string that has been filled missing braces
     */
    public String fillMissingBracesForSign(String str) {
        final int strLength = str.length();

        StringBuilder stringBuilder = new StringBuilder();

        StringBuilder buffer = new StringBuilder();

        boolean checkingMissingBraces = false;

        for (int i = 0; i < strLength; i++) {
            final char ch = str.charAt(i);
            // check mark inside the commonMarkThatMissBraces, if you find the mark, then make checkingMissingBraces to
            // true, else false
            for (char mark : commonMarkThatMissBraces) {
                if (ch == mark) {
                    checkingMissingBraces = true;
                    break;
                }
            }

            if (checkingMissingBraces) {
                buffer.append(ch); // note the first character would be the mark
                if (ch == '{') {
                    stringBuilder.append(buffer);
                    checkingMissingBraces = false;
                    buffer = new StringBuilder();
                } else if (Character.isDigit(ch)) {
                    stringBuilder.append(
                        String.format("%s{%s}", buffer.substring(0,1), ch)
                    );
                    checkingMissingBraces = false;
                    buffer = new StringBuilder();
                }
            } else { // by default, the character would be appended to stringBuilder
                stringBuilder.append(ch);
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Exception for the built-in method removeUnusedBeginEnd(String).
     * DO NOT USE ELEMENT FROM THE ARRAY, it may influence the result of the testing module
     */
    public String[] BeginEndException = {
        "array"
    };

    /**
     * Remove unused begin and end latex mark, like \begin{document} \end{document} does not influence the result of our
     * result of LaTeX mathematical expression, so we could remove it to make our next work easier.
     *
     * You can add some exceptions in the property BeginEndException such that this method would ignore that keyword
     *
     * @param str the latex string to be processed
     * @return the latex string after removing some unused begin and end.
     */
    public String removeUnusedBeginEnd(String str) {
        final int strLength = str.length();

        StringBuilder stringBuilder = new StringBuilder();

        StringBuilder buffer = new StringBuilder();

        boolean checkingUnused = false;

        for (int i = 0; i < strLength; i++) {
            final char ch = str.charAt(i);
            if (!checkingUnused && ch == '\\') {
                checkingUnused = true;
                continue;
            }

            if (checkingUnused) {
                if (ch == '{') {
                    buffer.append(ch);
                    final String keyword = buffer.substring(0, buffer.length() - 1); // exclude '{'
                    if (!keyword.equals("begin") && !keyword.equals("end")) {
                        checkingUnused = false;
                        stringBuilder.append('\\');
                        stringBuilder.append(buffer);
                        buffer = new StringBuilder();
                    }
                } else if (ch == '}') {
                    final String keyword = buffer.substring(buffer.substring(0, 5).equals("begin") ? 6 : 4);

                    for (String element : BeginEndException) {
                        if (element.equals(keyword)) {
                            stringBuilder.append('\\');
                            stringBuilder.append(buffer);
                            stringBuilder.append(ch);
                            break;
                        }
                    }
                    checkingUnused = false;
                    buffer = new StringBuilder();
                } else if (Character.isAlphabetic(ch)) {
                    buffer.append(ch);
                } else {
                    checkingUnused = false;
                    stringBuilder.append('\\');
                    stringBuilder.append(buffer);
                    stringBuilder.append(ch);
                    buffer = new StringBuilder();
                }
            } else {
                stringBuilder.append(ch);
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Convert the LaTeX Fraction form into the division form, for example, for the latex expression \frac{1}{2}, it
     * would be converted into ((1)/(2))
     * @param str the latex string to be processed
     * @return the latex string after removing some unused begin and end.
     */
    public String convertLaTeXFractionToDivision(String str) {
        ArrayList<Integer> indicesOfFraction = new ArrayList<>();

        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == '\\' && str.startsWith("frac", i + 1))
                indicesOfFraction.add(i);

        for (int i = indicesOfFraction.size() - 1; i >= 0; i--) {
            final int indexOfFraction = indicesOfFraction.get(i);

            Stack<Character> braceStack = new Stack<>();

            StringBuilder buffer = new StringBuilder();

            buffer.append(str, 0, indexOfFraction);
            buffer.append('(');

            for (int j = indexOfFraction + 5, counter = 0; j < str.length(); j++) {
                final char ch = str.charAt(j);

                if (ch == '{') {
                    braceStack.push(ch);
                    buffer.append(braceStack.size() == 1 ? '(' : ch);
                } else if (ch == '}') {
                    braceStack.pop();
                    if (braceStack.isEmpty()) {
                        buffer.append(')');
                        if (++counter == 1)
                            buffer.append('/');
                        if (counter == 2) {
                            buffer.append(')');
                            buffer.append(str.substring(j+1));
                            str = buffer.toString();
                            break;
                        }
                    } else {
                        buffer.append(ch);
                    }
                } else {
                    buffer.append(ch);
                }
            }
        }

        return str;
    }

    public String process(String latex) {
        return removeUnusedBeginEnd(
            fillMissingBracesForSign(
                convertLaTeXFractionToDivision(
                        latex
                )
            )
        );
    }
}
