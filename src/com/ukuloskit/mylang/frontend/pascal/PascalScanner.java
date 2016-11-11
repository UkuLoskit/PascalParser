package com.ukuloskit.mylang.frontend.pascal;

import com.ukuloskit.mylang.frontend.EofToken;
import com.ukuloskit.mylang.frontend.Scanner;
import com.ukuloskit.mylang.frontend.Source;
import com.ukuloskit.mylang.frontend.Token;

import static com.ukuloskit.mylang.frontend.Source.EOF;

public class PascalScanner extends Scanner {
    public PascalScanner(Source source) {
        super(source);
    }

    @Override
    protected Token extractToken() throws Exception {
        Token token;
        char currentChar = currentChar();

        if (currentChar == EOF) {
            token = new EofToken(source);
        } else {
            token = new Token(source);
        }
        return token;
    }
}
