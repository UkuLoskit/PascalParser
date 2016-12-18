package com.ukuloskit.mylang.frontend.pascal;

import com.ukuloskit.mylang.frontend.EofToken;
import com.ukuloskit.mylang.frontend.Scanner;
import com.ukuloskit.mylang.frontend.Source;
import com.ukuloskit.mylang.frontend.Token;
import com.ukuloskit.mylang.frontend.pascal.tokens.PascalErrorToken;
import com.ukuloskit.mylang.frontend.pascal.tokens.PascalNumberToken;
import com.ukuloskit.mylang.frontend.pascal.tokens.PascalStringToken;
import com.ukuloskit.mylang.frontend.pascal.tokens.PascalWordToken;

import java.io.IOException;

import static com.ukuloskit.mylang.frontend.Source.EOF;

public class PascalScanner extends Scanner {
    public PascalScanner(Source source) {
        super(source);
    }

    @Override
    protected Token extractToken() throws Exception {
        Token token;

        skipWhiteSpace();
        char currentChar = currentChar();

        if (currentChar == EOF) {
            token = new EofToken(source);
        } else if (Character.isLetter(currentChar)){
            token = new PascalWordToken(source);
        } else if (Character.isDigit(currentChar)){
            token = new PascalNumberToken(source);
        } else if (currentChar == '\'') {
            token = new PascalStringToken(source);
        } else if (PascalTokenType.SPECIAL_SYMBOLS.containsKey(
                Character.toString(currentChar))) {
            token = new PascalStringToken(source);
        } else {
            token = new PascalErrorToken(source, PascalErrorCode.INVALID_CHARACTER,
                                         Character.toString(currentChar));
            nextChar();
        }
        return token;
    }

    private void skipWhiteSpace() throws Exception {

        char currentChar = currentChar();

        while (Character.isWhitespace(currentChar) || currentChar == '{') {
            if (currentChar == '{') {
                do {
                    currentChar = nextChar();
                } while ((currentChar != '}') && currentChar != EOF);

                if (currentChar == '}') {
                    currentChar = nextChar();
                }
            } else {
                // not a comment
                currentChar = nextChar();
            }
        }

    }


}
