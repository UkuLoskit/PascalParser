package com.ukuloskit.mylang.frontend;

import java.io.IOException;

public class Token {
    protected TokenType type;
    protected String text;
    protected Object value;
    protected Source source;
    protected int lineNum;
    protected int position;

    public Token(Source source) throws Exception {
        this.source = source;
        this.lineNum = source.getLineNum();
        this.position = source.getPosition();

        extract();
    }

    protected void extract() throws IOException, Exception {
       text = Character.toString(currentChar());
        value = null;
        nextChar();
    }

    protected char currentChar() throws IOException {
        return source.currentChar();
    }

    protected char nextChar() throws IOException {
        return source.nextChar();
    }
    protected char peekChar() throws IOException {
        return source.peekChar();
    }

    public int getLineNumber() {
        return source.getLineNum();
    }

    public TokenType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }

    public String getText() {
        return text;
    }

}
