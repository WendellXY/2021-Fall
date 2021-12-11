package com.ltc.preprocesser;

/**
 * The PreProcessor is used to remove and format the LaTeX string such that it could be processed from the StrReader
 */
public class PreProcessor {
    public PreProcessor() { }

    /**
     * Common mark that is missed braces, like 1^2 should/could be write into 1^{2}. We only put '^' inside the array
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
            // check mark inside the commonMarkThatMissBraces, if find the mark, then make checkingMissingBraces to
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
                    stringBuilder.append(buffer.toString());
                    checkingMissingBraces = false;
                    buffer = new StringBuilder();
                } else if (Character.isDigit(ch)) {
                    stringBuilder.append(
                        String.format("%s{%s}", buffer.substring(0,1), ch)
                    );
                    checkingMissingBraces = false;
                    buffer = new StringBuilder();
                }
            } else { // by default, the character would be append to stringBuilder
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
                        stringBuilder.append(buffer.toString());
                        buffer = new StringBuilder();
                    }
                } else if (ch == '}') {
                    final String keyword = buffer.substring(buffer.substring(0, 5).equals("begin") ? 6 : 4);

                    for (String element : BeginEndException) {
                        if (element.equals(keyword)) {
                            stringBuilder.append('\\');
                            stringBuilder.append(buffer.toString());
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
                    stringBuilder.append(buffer.toString());
                    stringBuilder.append(ch);
                    buffer = new StringBuilder();
                }
            } else {
                stringBuilder.append(ch);
            }
        }

        return stringBuilder.toString();
    }

    public String process(String latex) {
        return removeUnusedBeginEnd(
            fillMissingBracesForSign(
                latex
            )
        );
    }
}
