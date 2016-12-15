package com.ukuloskit.mylang.frontend.pascal;

import com.ukuloskit.mylang.backend.Parser;
import com.ukuloskit.mylang.frontend.EofToken;
import com.ukuloskit.mylang.frontend.Scanner;
import com.ukuloskit.mylang.frontend.Token;
import com.ukuloskit.mylang.frontend.TokenType;
import com.ukuloskit.mylang.frontend.parsers.pascal.StatementParser;
import com.ukuloskit.mylang.intermediate.ICodeFactory;
import com.ukuloskit.mylang.intermediate.ICodeNode;
import com.ukuloskit.mylang.intermediate.SymTab;
import com.ukuloskit.mylang.intermediate.SymTabEntry;
import com.ukuloskit.mylang.message.Message;
import com.ukuloskit.mylang.message.MessageType;

import java.io.IOException;

import static com.ukuloskit.mylang.frontend.pascal.PascalErrorCode.IO_ERROR;
import static com.ukuloskit.mylang.frontend.pascal.PascalErrorCode.MISSING_PERIOD;
import static com.ukuloskit.mylang.frontend.pascal.PascalErrorCode.UNEXPECTED_TOKEN;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.BEGIN;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.DOT;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.IDENTIFIER;

public class PascalParserTD extends Parser {
    public PascalParserTD(Scanner scanner) {
        super(scanner);
    }

    public PascalParserTD(PascalParserTD parser) {
        super(parser.getScanner());
    }


    protected static PascalErrorHandler errorHandler = new PascalErrorHandler();

    @Override
    public void parse() throws Exception {
        long startTime = System.currentTimeMillis();
        iCode = ICodeFactory.createICode();

        try {
            Token token = nextToken();
            ICodeNode rootNode = null;

            if (token.getType() == BEGIN) {
                StatementParser statementParser = new StatementParser(this);
                rootNode = statementParser.parse(token);
                token = currentToken();
            } else {
                errorHandler.flag(token, UNEXPECTED_TOKEN, this);
            }

            if (token.getType() != DOT) {
                errorHandler.flag(token, MISSING_PERIOD, this);
            }

            token = currentToken();

            if (rootNode != null) {
                iCode.setRoot(rootNode);
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
