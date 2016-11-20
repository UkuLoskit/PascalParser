package com.ukuloskit.mylang.frontend.pascal;

public enum PascalErrorCode {

    ALREADY_FORWARDED("Already specified in FORWARD"),
    IDENTIFIER_REDEFINED("Redefined identifier"),
    IDENTIFIER_UNDEFINED("Undefined identifier"),
    INCOMPATIBLE_ASSIGNMENT("Incompatible assignment"),
    INCOMPATIBLE_TYPES("Incompatible types"),
    INVALID_ASSIGNMENT("Invalid assignment statement"),
    INVALID_CHARACTER("Invalid character"),
    INVALID_CONSTANT("Invalid constant"),
    INVALID_EXPONTENT("Invalid exponent"),
    INVALID_EXPRESSION("Invalid expression"),
    INVALID_FIELD("Invalid field"),
    INVALID_FRACTION("Invalid fraction"),
    INVALID_IDENTIFIER_USAGE("Invalid identifier usage"),
    INVALID_INDEX_TYPE("Invalid index type"),
    INVALID_NUMBER("Invalid number"),
    INVALID_STATEMENT("Invalid statement"),
    INVALID_SUBRANGE_TYPE("Invalid subrange type"),
    INVALID_TARGET("Invalid target"),
    INVALID_TYPE("Invalid type"),
    INVALID_VAR_PARM("Invalid VAR parameter"),
    MIN_GT_MAX("Min limit greater than max limit"),
    MISSING_BEGIN("Missing BEGIN"),
    MISSING_COLON("Missing :"),
    MISSING_COLON_EQUALS("Missing :="),
    MISSING_CONSTATNT("Missing constant"),
    MISSING_DO("Missing DO"),
    MISSING_DOT_DOT("Missing .."),
    MISSING_END("Missing END"),
    MISSING_EQUALS("Missing ="),
    MISSING_FOR_CONTROL("Invalid FOR control variable"),
    MISSING_IDENTIFIER("Missing identifier"),
    MISSING_LEFT_BRACKET("Missing ["),
    MISSING_OF("Missing OF"),
    MISSING_PERIOD("Missing ."),
    MISSING_PROGRAM("Missing PROGRAM"),
    MISSING_RIGHT_BRACKET("Missing ["),
    MISSING_RIGHT_PAREN("Missing )"),
    MISSING_SEMICOLON("Missing ;"),
    MISSING_THEN("Missing THEN"),
    MISSING_TO_DOWNTO("Missing TO or DOWNTO"),
    MISSING_UNTIL("Missing UNTIL"),
    CASE_CONSTANT_REUSED("CASE constant reused"),
    NOT_CONSTANT_IDENTIFIER("Not a constant identifier"),
    NOT_RECORD_VARIABLE("Not a record variable"),
    RANGE_INTEGER("Integer literal out of range"),
    RANGE_REAL("Real literal out of range"),
    STACK_OVERFLOW("Stack overflow"),
    TOO_MANY_LEVELS("Too many levels"),
    TOO_MANY_SUBSCRIPTS("Too many subscripts"),
    UNEXPECTED_EOF("Unexpected end of file"),
    UNEXPECTED_TOKEN("Unexpected token"),
    UNIMPLEMENTED("Unimplemented feature"),
    UNRECOGNIZABLE("Unrecognizable output"),
    WRONG_NUMBER_OF_PARMS("Wrong number of actual parameters"),

    IO_ERROR(-101, "Object I/O error"),
    TOO_MANY_ERRORS(-102, "Too many syntax errors");

    private int status;
    private String message;

    PascalErrorCode(String message) {
        this.message = message;
    }

    PascalErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return message;
    }
}
