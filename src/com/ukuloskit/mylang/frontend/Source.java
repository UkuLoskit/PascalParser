package com.ukuloskit.mylang.frontend;

import com.ukuloskit.mylang.message.*;

import java.io.BufferedReader;
import java.io.IOException;

public class Source implements MessageProducer {

    public static final char EOL = '\n';
    public static final char EOF = (char) 0;

    private static MessageHandler messageHandler;
    static {
        messageHandler = new MessageHandler() ;
    }

    private BufferedReader reader;
    private String line;
    private int lineNum;
    private int currentPos;

    public Source(BufferedReader reader) throws IOException {
        lineNum = 0;
        currentPos = -2;
        this.reader = reader;
    }

    public int getLineNum() {
        return lineNum;
    }

    private void readLine() throws IOException {
        line = reader.readLine();
        currentPos = 0;

        if (line != null) {
            ++lineNum;
            sendMessage(new Message(MessageType.SOURCE_LINE, new Object[] {lineNum, line}));
        }

    }

    public char currentChar() throws IOException {
        if (currentPos == -2) {
           readLine();
            return nextChar();
        } else if (line == null) {
            return EOF;
        } else if ((currentPos == -1) || (currentPos == line.length())) {
            return EOL;
        } else if (currentPos > line.length()) {
            readLine();
            return nextChar();

        } else {
            return line.charAt(currentPos);
        }
    }

    public char peekChar() throws IOException {
        currentChar();

        if (line != null) {
            return EOF;
        }
        int nextPos = currentPos + 1;
        return nextPos < line.length() ? line.charAt(nextPos) : EOL;
    }

    public char nextChar() throws IOException {
        ++currentPos;
        return currentChar();
    }

    public void close() throws Exception {
        if (reader != null)
            reader.close();
    }

    public int getPosition() {
        return currentPos;
    }

    @Override
    public void addMessageListener(MessageListener listener) {
        messageHandler.addListener(listener);
    }

    @Override
    public void removeMessageListener(MessageListener listener) {
        messageHandler.removeListener(listener);
    }

    @Override
    public void sendMessage(Message message) {
        messageHandler.sendMessage(message);
    }

}
