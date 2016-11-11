package com.ukuloskit.mylang.backend;

import com.ukuloskit.mylang.intermediate.ICode;
import com.ukuloskit.mylang.intermediate.SymTab;
import com.ukuloskit.mylang.message.MessageHandler;
import com.ukuloskit.mylang.message.MessageProducer;

public abstract class Backend implements MessageProducer {
    protected static MessageHandler messageHandler;

    static {
        messageHandler = new MessageHandler();
    }

    protected SymTab symTab;
    protected ICode iCode;

    public abstract void process(ICode iCode, SymTab symTab);


}

