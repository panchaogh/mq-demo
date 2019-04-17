package com.pcgrw.sbrabbit.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = 5755277853630195948L;
    private String id;
    private String name;
    private String MessageId;//存储消息发送的唯一标识

    public Order() {
    }

    public Order(String id, String name, String messageId) {
        this.id = id;
        this.name = name;
        MessageId = messageId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return MessageId;
    }

    public void setMessageId(String messageId) {
        MessageId = messageId;
    }
}
