package com.ukuloskit.mylang.frontend.pascal.tokens;

import com.ukuloskit.mylang.frontend.Source;
import com.ukuloskit.mylang.frontend.pascal.PascalTokenType;

import java.io.IOException;

import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.IDENTIFIER;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.RESERVED_WORDS;

/**
 * Created by uku on 18.11.16.
 */
public class PascalWordToken extends PascalToken {

    public PascalWordToken(Source source) throws IOException {
        super(source);
    }

    public void extract() throws Exception {
        StringBuilder textBuffer = new StringBuilder();
        char currentChar = currentChar();

        while (Character.isLetterOrDigit(currentChar)) {
            textBuffer.append(currentChar);
            currentChar = nextChar();
        }

        text = textBuffer.toString();

        if (RESERVED_WORDS.contains(text.toLowerCase())) {
            type = PascalTokenType.valueOf(text.toUpperCase());
        } else {
            type = IDENTIFIER;
        }

    }
}
