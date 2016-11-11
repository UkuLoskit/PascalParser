package com.ukuloskit.mylang.message;

/**
 * Created by uku on 7.11.16.
 */
public enum MessageType {
    SOURCE_LINE,
    SYNTAX_ERROR,
    PARSER_SUMMARY,
    INTERPRETER_SUMMARY,
    COMPILER_SUMMARY,
    MISCELLANEOUS,
    TOKEN,
    ASSIGN,
    FETCH,
    BREAKPOINT,
    RUNTIME_ERROR,
    CALL,
    RETURN
}