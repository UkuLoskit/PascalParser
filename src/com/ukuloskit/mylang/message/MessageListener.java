package com.ukuloskit.mylang.message;

import com.ukuloskit.mylang.message.Message;

/**
 * Created by uku on 7.11.16.
 */
public interface MessageListener {
    public void messageReceived(Message message);
}
