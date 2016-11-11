package com.ukuloskit.mylang.message;


/**
 * Created by uku on 7.11.16.
 */
public interface MessageProducer {
    void addMessageListener(MessageListener listener);
    void removeMessageListener(MessageListener listener);
    void sendMessage(Message message);

}
