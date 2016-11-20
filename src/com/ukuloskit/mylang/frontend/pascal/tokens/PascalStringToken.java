package com.ukuloskit.mylang.frontend.pascal.tokens;

import com.ukuloskit.mylang.frontend.Source;

import java.io.IOException;

import static com.ukuloskit.mylang.frontend.Source.EOF;
import static com.ukuloskit.mylang.frontend.pascal.PascalErrorCode.UNEXPECTED_EOF;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.ERROR;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.STRING;

public class PascalStringToken extends PascalToken {
    public PascalStringToken(Source source) throws IOException {
        super(source);
    }

    public void extract() throws IOException {

        final char singleQuote = '\'';
        StringBuilder textBuffer = new StringBuilder();
        // contains the value of the string token. for example: 'my string' => my string
        // 'let''s go' => let's go
        StringBuilder valueBuffer = new StringBuilder();

        char currentChar = nextChar();
        textBuffer.append(singleQuote);
        do {
            if (Character.isWhitespace(currentChar)) {
                currentChar = ' ';
            }
            if ((currentChar != singleQuote) && (currentChar != EOF)) {
                textBuffer.append(currentChar);
                valueBuffer.append(currentChar);
                currentChar = nextChar();
            }

            if (currentChar == singleQuote) {
                while ((currentChar == singleQuote && peekChar() == singleQuote)) {
                    textBuffer.append(("''"));
                    valueBuffer.append(currentChar);
                    nextChar();
                    currentChar = nextChar();
                }

            }
        } while ((currentChar != singleQuote) && ( currentChar != EOF));

        if (currentChar == singleQuote) {
            nextChar();
            textBuffer.append(singleQuote);
            type = STRING;
            value = valueBuffer.toString();
        } else {
            type = ERROR;
            value = UNEXPECTED_EOF;
        }
        text = textBuffer.toString();

    }
}
