package com.wu.pdm;

import lombok.Data;

@Data
public class TopicInfo {

    private String firstTopic;

    private String secondTopic;

    private String tableId;

    public TopicInfo() {

    }

    public TopicInfo(String firstTopic, String secondTopic, String tableId) {
        this.firstTopic = firstTopic;
        this.secondTopic = secondTopic;
        this.tableId = tableId;
    }
}
