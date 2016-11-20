package com.ukuloskit.mylang.frontend.pascal;

import com.ukuloskit.mylang.backend.Parser;
import com.ukuloskit.mylang.frontend.Token;
import com.ukuloskit.mylang.message.Message;
import com.ukuloskit.mylang.message.MessageType;

import static com.ukuloskit.mylang.frontend.pascal.PascalErrorCode.TOO_MANY_ERRORS;

public class PascalErrorHandler {
    private static final int MAX_ERRORS = 25;
    private static int errorCount = 0;

    public void flag(Token token, PascalErrorCode errorCode, Parser parser) {
        parser.sendMessage(new Message(MessageType.SYNTAX_ERROR,
                                        new Object[] { token.getLineNumber(),
                                                       token.getPosition(),
                                                       token.getText(),
                                                       errorCode.toString()
                                        }
                          )
        );

        if (++errorCount > MAX_ERRORS) {
            abortTranslation(TOO_MANY_ERRORS, parser);
        }
    }

    public void abortTranslation(PascalErrorCode errorCode, Parser parser) {
        String errorText = "FATAL ERROR: " + errorCode.toString();

        parser.sendMessage(new Message(MessageType.SYNTAX_ERROR,
                                       new Object[] {0, 0, "", errorText}));
        System.exit(errorCode.getStatus());
    }
}
