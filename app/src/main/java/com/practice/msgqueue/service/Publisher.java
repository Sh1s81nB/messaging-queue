package com.practice.msgqueue.service;

import java.util.Random;

import com.practice.msgqueue.model.Message;
import com.practice.msgqueue.model.Partition;
import com.practice.msgqueue.model.Topic;

public class Publisher {
    private final String name;
    private final Random random = new Random();

    public Publisher(String name) {
        this.name = name;
    }

    public void publish(Topic topic, String content) {
        int partitionId = random.nextInt(topic.getPartitions().size());
        Partition partition = topic.getPartition(partitionId);
        Message message = new Message(content);
        partition.addMessage(message);
        System.out.println("Timestamp: "+ System.currentTimeMillis() +
                " Publisher " + name + " published message to " + topic.getName() +
                " Partition " + partitionId);
    }
}
