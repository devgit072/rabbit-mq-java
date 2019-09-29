package com.devrajs.rabbitmq;

import com.devrajs.rabbitmq.consumer.ConsumeMessage;
import com.devrajs.rabbitmq.publisher.PublishMessage;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Application {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        PublishMessage publishMessage = new PublishMessage("NewQueue");
        ConsumeMessage consumeMessage = new ConsumeMessage("NewQueue");

        // Publisher publishes the message and consumer will consume it.
        for (int i = 0; i < 10; i++) {
            String randomMessage = UUID.randomUUID().toString() + "--" + i;
            publishMessage.publishMessage(randomMessage);
            Thread.sleep(1000);
            consumeMessage.consumeMessage();
        }

        // Publishes several messages in queue and later consumer will consume it.
        for (int i = 0; i < 10; i++) {
            String randomMessage = UUID.randomUUID().toString() + "--" + i;
            publishMessage.publishMessage(randomMessage);
        }
        Thread.sleep(5000);

        // Consume all messages from the queue.
        for (int i = 0; i < 10; i++) {
            consumeMessage.consumeMessage();
        }

        // close both connection and channel else it will be alive till Application stops.
        publishMessage.closeChannelAndConnection();
        consumeMessage.closeChannelAndConnection();
    }
}
