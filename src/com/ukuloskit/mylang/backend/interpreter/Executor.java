package com.ukuloskit.mylang.backend.interpreter;

import com.ukuloskit.mylang.backend.Backend;
import com.ukuloskit.mylang.intermediate.SymTab;
import com.ukuloskit.mylang.intermediate.ICode;
import com.ukuloskit.mylang.message.Message;
import com.ukuloskit.mylang.message.MessageListener;
import com.ukuloskit.mylang.message.MessageType;


public class Executor extends Backend {
    public void process(ICode iCode, SymTab symTab) {

        long startTime = System.currentTimeMillis();
        float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
        int executionCount = 0;
        int runtimeErrors = 0;

        sendMessage(
            new Message(
               MessageType.INTERPRETER_SUMMARY,
                new Number[] {executionCount, runtimeErrors, elapsedTime}
            )
        );

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
