package com.practice.msgqueue.model;

import com.practice.msgqueue.service.Subscriber;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Partition {
    private final int id;
    private final List<Message> messageList;
    private final Map<Subscriber, AtomicInteger> subscriberOffsets;

    public Partition(int id) {
        this.id = id;
        messageList = Collections.synchronizedList(new ArrayList<>());
        subscriberOffsets = new ConcurrentHashMap<>();
    }

    public synchronized void addMessage(Message message) {
        messageList.add(message);
    }

    public void registerSubscriber(Subscriber subscriber) {
        subscriberOffsets.putIfAbsent(subscriber, new AtomicInteger(0));
    }

    public Message getNextMessageForSubscriber(Subscriber subscriber) {
        AtomicInteger offset = subscriberOffsets.get(subscriber);
        int currentOffset = offset.get();

        if (currentOffset < messageList.size()) {
            offset.incrementAndGet();
            return messageList.get(currentOffset);
        }
        return null;
    }

    public int getId() {
        return id;
    }
}