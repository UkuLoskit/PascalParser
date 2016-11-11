package com.ukuloskit.mylang.frontend.pascal;

import com.ukuloskit.mylang.backend.Parser;
import com.ukuloskit.mylang.frontend.EofToken;
import com.ukuloskit.mylang.frontend.Scanner;
import com.ukuloskit.mylang.frontend.Token;
import com.ukuloskit.mylang.frontend.TokenType;
import com.ukuloskit.mylang.message.Message;
import com.ukuloskit.mylang.message.MessageType;

public class PascalParserTD extends Parser {
    public PascalParserTD(Scanner scanner) {
        super(scanner);
    }

    protected static PascalErrorHandler errorHandler = new PascalErrorHandler();

    @Override
    public void parse() throws Exception {
        Token token;
        long startTime = System.currentTimeMillis();
        while (!((token = nextToken()) instanceof EofToken)) {
            TokenType tokenType = token.getType();

            if (tokenType = TokenType.ERROR) {


            } else {
               errorHandler.flag(token, (PascalErrorCode))
            }

        }

        float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
        sendMessage(
            new Message(
                MessageType.PARSER_SUMMARY,
                    new Number[] {
                            token.getLineNumber(),
                            getErrorCount(),
                            elapsedTime
                    }
            )
        );

    }

    @Override
    public int getErrorCount() {
        return 0;
    }
}
