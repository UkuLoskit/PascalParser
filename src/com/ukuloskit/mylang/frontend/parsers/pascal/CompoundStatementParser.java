package com.ukuloskit.mylang.frontend.parsers.pascal;

import com.ukuloskit.mylang.frontend.Token;
import com.ukuloskit.mylang.frontend.pascal.PascalParserTD;
import com.ukuloskit.mylang.intermediate.ICodeFactory;
import com.ukuloskit.mylang.intermediate.ICodeNode;

import static com.ukuloskit.mylang.frontend.pascal.PascalErrorCode.MISSING_END;
import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.END;
import static com.ukuloskit.mylang.intermediate.icodeimpl.ICodeNodeTypeImpl.COMPOUND;

public class CompoundStatementParser extends PascalParserTD {
    public CompoundStatementParser(PascalParserTD parser) {
        super(parser);
    }

    public ICodeNode parse(Token token) throws Exception {

        token = nextToken();
        ICodeNode compoundNode = ICodeFactory.createICodeNode(COMPOUND);

        StatementParser statementParser = new StatementParser(this);
        statementParser.parseList(token, compoundNode, END, MISSING_END);
        return compoundNode;

    }

}
