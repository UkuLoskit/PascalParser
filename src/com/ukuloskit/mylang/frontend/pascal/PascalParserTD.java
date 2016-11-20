package com.ukuloskit.mylang.frontend.pascal;

import com.ukuloskit.mylang.backend.Parser;
import com.ukuloskit.mylang.frontend.EofToken;
import com.ukuloskit.mylang.frontend.Scanner;
import com.ukuloskit.mylang.frontend.Token;
import com.ukuloskit.mylang.frontend.TokenType;
import com.ukuloskit.mylang.message.Message;
import com.ukuloskit.mylang.message.MessageType;

import java.io.IOException;

public class PascalParserTD extends Parser {
    public PascalParserTD(Scanner scanner) {
        super(scanner);
    }

    protected static PascalErrorHandler errorHandler = new PascalErrorHandler();

    @Override
    public void parse() throws Exception {
        Token token;
        long startTime = System.currentTimeMillis();

        try {
            while (!((token = nextToken()) instanceof EofToken)) {
                TokenType tokenType = token.getType();

                if (tokenType == TokenType.ERROR) {
                    sendMessage(new Message(
                            MessageType.TOKEN,
                            new Object[]{
                                    token.getLineNumber(),
                                    token.getPosition(),
                                    tokenType,
                                    token.getText(),
                                    token.getValue()
                            }

                    ));

                } else {
                    errorHandler.flag(token, (PascalErrorCode) token.getValue(),
                            this);
                }

            }

            float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
            sendMessage(
                    new Message(
                            MessageType.PARSER_SUMMARY,
                            new Number[]{
                                    token.getLineNumber(),
                                    getErrorCount(),
                                    elapsedTime
                            }
                    )
            );
        } catch (IOException ex) {
            errorHandler.abortTranslation(IO_ERROR, this);
        }

    }

    @Override
    public int getErrorCount() {
        errorHandler.getErrorCount();
    }
}
