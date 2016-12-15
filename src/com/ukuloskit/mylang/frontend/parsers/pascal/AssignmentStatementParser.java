package com.ukuloskit.mylang.frontend.parsers.pascal;

import com.ukuloskit.mylang.frontend.Token;
import com.ukuloskit.mylang.frontend.pascal.PascalErrorCode;
import com.ukuloskit.mylang.frontend.pascal.PascalParserTD;
import com.ukuloskit.mylang.intermediate.ICodeFactory;
import com.ukuloskit.mylang.intermediate.ICodeNode;
import com.ukuloskit.mylang.intermediate.SymTabEntry;

import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.COLON_EQUALS;
import static com.ukuloskit.mylang.intermediate.icodeimpl.ICodeKeyImpl.ID;
import static com.ukuloskit.mylang.intermediate.icodeimpl.ICodeNodeTypeImpl.ASSIGN;
import static com.ukuloskit.mylang.intermediate.icodeimpl.ICodeNodeTypeImpl.VARIABLE;

public class AssignmentStatementParser extends PascalParserTD {

    public AssignmentStatementParser(PascalParserTD parser) {
        super(parser);
    }

    public ICodeNode parse(Token token) throws Exception {
        ICodeNode assignNode = ICodeFactory.createICodeNode(ASSIGN);

        String targetName = token.getText().toLowerCase();
        SymTabEntry targetId = symTabStack.lookup(targetName);

        if (targetId == null) {
            targetId = symTabStack.enterLocal(targetName);
        }
        targetId.appendLineNumber(token.getLineNumber());

        token = nextToken();

        ICodeNode variableNode = ICodeFactory.createICodeNode(VARIABLE);
        variableNode.setAttribute(ID, targetId);

        assignNode.addChild(variableNode);

        if (token.getType() == COLON_EQUALS) {
            token =nextToken();
        } else {
            errorHandler.flag(token, PascalErrorCode.MISSING_COLON_EQUALS, this);
        }
        ExpressionParser expressionParser = new ExpressionParser(this);
        assignNode.addChild(expressionParser.parse(token));

        return assignNode;

    }
}
