package com.ukuloskit.mylang.frontend.pascal.tokens;

import com.ukuloskit.mylang.frontend.Source;
import com.ukuloskit.mylang.frontend.Token;

import java.io.IOException;

import static com.ukuloskit.mylang.frontend.pascal.PascalErrorCode.INVALID_NUMBER;
import static com.ukuloskit.mylang.frontend.pascal.PascalErrorCode.RANGE_INTEGER;
import static com.ukuloskit.mylang.frontend.pascal.PascalErrorCode.RANGE_REAL;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.ERROR;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.INTEGER;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.REAL;
import static java.lang.Float.MAX_EXPONENT;

public class PascalNumberToken extends Token {
    public PascalNumberToken(Source source) throws Exception {
        super(source);
    }

    public void extract() throws Exception {

        StringBuilder sb = new StringBuilder();
        extractNumber(sb);
        text = sb.toString();

    }

    protected void extractNumber(StringBuilder textBuffer) throws IOException {
        String wholeDigits = null;
        String fractionDigits = null;
        String exponentDigits = null;
        char exponentSign = '+';
        boolean sawDotDot = false;
        char currentChar;
        type = INTEGER;
        wholeDigits = unsignedIntegerDigits(textBuffer);

        if (type == ERROR) {
            return;
        }

        currentChar = currentChar();

        if (currentChar == '.') {
            if (peekChar() == '.') {
                sawDotDot = true;
            } else {
                type = REAL;
                textBuffer.append(currentChar);
                currentChar = nextChar();

                fractionDigits = unsignedIntegerDigits(textBuffer);
                if (type == ERROR) {
                    return;

                }
            }

        }

        currentChar = currentChar();

        if (!sawDotDot && (( currentChar == 'e') || (currentChar == 'E'))) {
            type = REAL;
            textBuffer.append(currentChar);
            currentChar = nextChar();

            if ((currentChar == '+') || (currentChar == '-')) {
                textBuffer.append(currentChar);
                exponentSign = currentChar;
                currentChar = nextChar();
            }
            exponentDigits = unsignedIntegerDigits(textBuffer);

        }

        if (type == INTEGER) {
            int integerValue = computeIntegerValue(wholeDigits);

            if (type != ERROR) {
                value = new Integer(integerValue);
            }

        } else if (type == REAL) {
            float floatValue = computeFloatValue(wholeDigits, fractionDigits,
                                                   exponentDigits, exponentSign);
            if (type != ERROR) {
                value = new Float(floatValue);
            }
        }

    }

    private float computeFloatValue(String wholeDigits, String fractionDigits, String exponentDigits, char exponentSign) {
        double floatValue = 0.0;
        int exponentValue = computeIntegerValue(exponentDigits);
        String digits = wholeDigits;

        if (exponentSign == '-') {
            exponentValue = -exponentValue;
        }
        if (fractionDigits != null) {
            exponentValue -= fractionDigits.length();
            digits += fractionDigits;
        }

        if (Math.abs(exponentValue + wholeDigits.length()) > MAX_EXPONENT) {
            type = ERROR;
            value = RANGE_REAL;
            return 0.0f;
        }

        int index = 0;
        while (index < digits.length()) {
            floatValue = 10 * floatValue + Character.getNumericValue(digits.charAt(index++));
        }
        if (exponentValue != 0) {
            floatValue = Math.pow(10, exponentValue);
        }

        return (float) floatValue;
    }

    private int computeIntegerValue(String digits) {
        if (digits == null) {
            return 0;
        }
         int integerValue = 0;
         int prevValue = -1;

        int index = 0;
        // 10 * 0 * + 4 4
        // 4*10+2=42
        // 42*10+ 0
        //
        while ((index < digits.length()) && (integerValue >= prevValue))  {
            prevValue = integerValue;
            integerValue = 10 * integerValue + Character.getNumericValue(digits.charAt(index++));

        }

        if (integerValue >= prevValue) {
            return integerValue;
        } else {
            type = ERROR;
            value = RANGE_INTEGER;
            return 0;
        }
    }

    private String unsignedIntegerDigits(StringBuilder textBuffer) throws IOException {
        char currentChar = currentChar();

        if (!Character.isDigit(currentChar)) {
            type = ERROR;
            value = INVALID_NUMBER;
            return null;
        }
        StringBuilder digits = new StringBuilder();
        while (Character.isDigit(currentChar)) {
            textBuffer.append(currentChar);
            digits.append(currentChar);
            currentChar = nextChar();
        }

        return digits.toString();
    }
}
