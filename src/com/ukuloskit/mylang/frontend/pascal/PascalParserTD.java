package com.ukuloskit.mylang.frontend.pascal;

import com.ukuloskit.mylang.backend.Parser;
import com.ukuloskit.mylang.frontend.EofToken;
import com.ukuloskit.mylang.frontend.Scanner;
import com.ukuloskit.mylang.frontend.Token;
import com.ukuloskit.mylang.frontend.TokenType;
import com.ukuloskit.mylang.intermediate.SymTab;
import com.ukuloskit.mylang.intermediate.SymTabEntry;
import com.ukuloskit.mylang.message.Message;
import com.ukuloskit.mylang.message.MessageType;

import java.io.IOException;

import static com.ukuloskit.mylang.frontend.pascal.PascalErrorCode.IO_ERROR;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.IDENTIFIER;

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

                if (tokenType == IDENTIFIER) {
                    String name = token.getText().toLowerCase();

                    SymTabEntry entry = symTab.lookup(name);
                    if (entry == null) {
                        entry = symTabStack.enterLocal(name);
                    }
                    entry.appendLineNumber(token.getLineNumber());
                } else if (tokenType == PascalTokenType.ERROR) {
                    errorHandler.flag(token, (PascalErrorCode) token.getValue(), this);
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
        return errorHandler.getErrorCount();
    }
}
