package com.ukuloskit.mylang.backend;

import com.ukuloskit.mylang.intermediate.SymTab;
import com.ukuloskit.mylang.frontend.Token;
import com.ukuloskit.mylang.frontend.Scanner;
import com.ukuloskit.mylang.intermediate.ICode;
import com.ukuloskit.mylang.intermediate.SymTabFactory;
import com.ukuloskit.mylang.intermediate.SymTabStack;
import com.ukuloskit.mylang.message.Message;
import com.ukuloskit.mylang.message.MessageHandler;
import com.ukuloskit.mylang.message.MessageListener;
import com.ukuloskit.mylang.message.MessageProducer;

public abstract class Parser implements MessageProducer {

    protected static SymTab symTab;
    protected static SymTabStack symTabStack;
    protected static MessageHandler messageHandler;

    static {
        symTab = null;
        messageHandler = new MessageHandler();
        symTabStack = SymTabFactory.createSymTabStack();
    }

    protected Parser(Scanner scanner) {
        this.scanner = scanner;
        this.iCode = null;
    }

    protected Scanner scanner;
    protected ICode iCode;

    public void addMessageListener(MessageListener listener) {
        messageHandler.addListener(listener);
    }

    public void removeMessageListener(MessageListener listener) {
        messageHandler.removeListener(listener);
    }

    public void sendMessage(Message message) {
        messageHandler.sendMessage(message);
    }

    public abstract void parse() throws Exception;

    public abstract int getErrorCount();

    public Token currentToken() {
        return scanner.currentToken();
    }

    public Token nextToken() throws Exception {
        return scanner.nextToken();
    }

    public ICode getIcode() {
        return iCode;
    }

    public SymTab getSymTab() {
        return symTab;
    }
}
