package com.ukuloskit.mylang.frontend.pascal.tokens;

import com.ukuloskit.mylang.frontend.Source;
import com.ukuloskit.mylang.frontend.Token;

import java.io.IOException;

import static com.ukuloskit.mylang.frontend.pascal.PascalErrorCode.INVALID_CHARACTER;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.ERROR;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.SPECIAL_SYMBOLS;

public class PascalSpecialSymbolToken extends Token {
    public PascalSpecialSymbolToken(Source source) throws Exception {
        super(source);
    }

    protected void extract() throws Exception {
        char currentChar = currentChar();

        text = Character.toString(currentChar);

        switch (currentChar) {
            case '+': case '-': case '*': case '/': case ',':
            case ';': case '\'': case '=': case '(': case ')':
            case '[': case ']': case '{': case '}': case '^': {
                nextChar();
                break;
            }

            case ':': {
                currentChar = nextChar();
                if (currentChar == '=') {
                    text += currentChar();
                    nextChar();
                }
                break;
            }
            case '<': {
                currentChar = nextChar();
                if (currentChar == '=' ||  currentChar == '>') {
                    text += currentChar;
                    nextChar();
                }
                break;

            }
            case '>': {
                currentChar = nextChar();
                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }
                break;
            }
            case '.': {
                currentChar = nextChar();
                 if (currentChar == '.') {
                    text += currentChar;
                     nextChar();
                 }
                 break;

            }
            default: {
                nextChar();
                type = ERROR;
                value = INVALID_CHARACTER;
            }
        }

        if (type == null) {
            type = SPECIAL_SYMBOLS.get(text);
        }


    }
}
