package com.ukuloskit.mylang.message;

import java.util.ArrayList;

/**
 * Created by uku on 7.11.16.
 */
public class MessageHandler {

    private Message message;
    private ArrayList<MessageListener> listeners;


    public MessageHandler() {
        this.listeners = new ArrayList<MessageListener>();
    }

    public void addListener(MessageListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(MessageListener listener) {
        this.listeners.remove(listener);
    }

    public void sendMessage(Message message) {
        this.message = message;
        notifyListeners();
    }

    public void notifyListeners() {
        for (MessageListener listener: listeners) {
            listener.messageReceived(this.message);
        }

    }

}
