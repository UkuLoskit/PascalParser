package com.ukuloskit.mylang.intermediate.icodeimpl;

import com.ukuloskit.mylang.intermediate.ICodeNodeType;

public enum ICodeNodeTypeImpl implements ICodeNodeType{

    PROGRAM, PROCEDURE, FUNCTION,

    COMPOUND, ASSIGN, LOOP, TEST, CALL, PARAMETERS,
    IF, SELECT, SELECT_BRANCH, SELECT_CONSTANTS, NO_OP,

    EQ, NE, LT, LE, GT, GE, NOT,

    ADD, SUBTRACT, OR, NEGATE,

    MULTIPLY, INTEGER_DIVIDE, FLOAT_DIVIDE, MOD, AND,

    VARIABLE, SUBSCRIPTS, FIELD,
    INTEGER_CONSTANT, REAL_CONSTANT,
    STRING_CONSTANT, BOOLEAN_CONSTANT


}
