package com.practice.msgqueue.model;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private final String name;
    private final List<Partition> partitions;

    public Topic(String name, int partitionCount) {
        this.name = name;
        this.partitions = new ArrayList<>();
        for (int i = 0; i < partitionCount; i++) {
            partitions.add(new Partition(i));
        }
    }

    public String getName() {
        return name;
    }

    public Partition getPartition(int partitionId) {
        return partitions.get(partitionId);
    }

    public List<Partition> getPartitions() {
        return partitions;
    }
}
