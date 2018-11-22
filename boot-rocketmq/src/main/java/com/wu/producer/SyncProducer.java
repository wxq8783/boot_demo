package com.wu.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class SyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");

        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();

        for(int i=0;i<10;i++){
            Message msg = new Message("TopicTest","TagA",("Hello RocketMQ"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult result = producer.send(msg);
            System.out.printf(result.toString());
        }

        Thread.sleep(1000000);
        producer.shutdown();
    }
}
