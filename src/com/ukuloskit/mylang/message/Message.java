package com.ukuloskit.mylang.message;

/**
 * Created by uku on 7.11.16.
 */
public class Message {
    private MessageType type;
    private Object body;


    public Message(MessageType type, Object body) {
        this.type = type;
        this.body = body;

    }

    public MessageType getType() {
        return type;
    }

    public Object getBody() {
        return body;
    }
}
