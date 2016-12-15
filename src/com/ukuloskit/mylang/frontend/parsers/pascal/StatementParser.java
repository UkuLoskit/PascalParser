package com.ukuloskit.mylang.frontend.parsers.pascal;

import com.ukuloskit.mylang.frontend.EofToken;
import com.ukuloskit.mylang.frontend.Token;
import com.ukuloskit.mylang.frontend.TokenType;
import com.ukuloskit.mylang.frontend.pascal.PascalErrorCode;
import com.ukuloskit.mylang.frontend.pascal.PascalParserTD;
import com.ukuloskit.mylang.frontend.pascal.PascalTokenType;
import com.ukuloskit.mylang.intermediate.ICodeFactory;
import com.ukuloskit.mylang.intermediate.ICodeNode;

import static com.ukuloskit.mylang.frontend.pascal.PascalErrorCode.MISSING_SEMICOLON;
import static com.ukuloskit.mylang.frontend.pascal.PascalErrorCode.UNEXPECTED_TOKEN;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.IDENTIFIER;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.SEMICOLON;
import static com.ukuloskit.mylang.intermediate.icodeimpl.ICodeNodeTypeImpl.NO_OP;
import static com.ukuloskit.mylang.intermediate.icodeimpl.ICodeKeyImpl.LINE;

public class StatementParser extends PascalParserTD {
    public StatementParser(PascalParserTD parser) {
        super(parser);
    }

    public ICodeNode parse(Token token) throws Exception {
        ICodeNode statementNode = null;

        switch ((PascalTokenType) token.getType()) {
            case BEGIN:
                CompoundStatementParser compoundStatementParser =
                        new CompoundStatementParser(this);
                statementNode = compoundStatementParser.parse(token);
                break;
            case IDENTIFIER:
                AssignmentStatementParser assignmentStatementParser =
                        new AssignmentStateParser(this);
                statementNode = assignmentStatementParser.parse(token);
                break;
            default:
                statementNode = ICodeFactory.createICodeNode(NO_OP);
                break;

        }
        setLineNumber(statementNode, token);
        return statementNode;
    }

    protected void setLineNumber(ICodeNode node, Token token) {
        if (node != null) {
            node.setAttribute(LINE, token.getLineNumber());
        }
    }

    protected void parseList(Token token, ICodeNode parentNode, PascalTokenType terminator, PascalErrorCode errorCode) throws Exception {

        while ((!(token instanceof EofToken) && (token.getType() != terminator))) {
            ICodeNode statementNode = parse(token);
            parentNode.addChild(statementNode);

            token = currentToken();
            TokenType tokenType = token.getType();

            if (tokenType == SEMICOLON) {
                token = nextToken();
            } else if (tokenType == IDENTIFIER) {
                // this means that the statement was not followed by a semi-colon
                errorHandler.flag(token, MISSING_SEMICOLON, this);
            } else if (tokenType != terminator) {
                errorHandler.flag(token, UNEXPECTED_TOKEN, this);
                token = nextToken();
            }
        }

        if (token.getType() == terminator) {
            nextToken();
        } else {
            errorHandler.flag(token, errorCode, this);
        }

    }


}
