package com.devrajs.rabbitmq.consumer;

import com.devraj.rabbitmq.connection.RabbitMQConnection;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ConsumeMessage {
    private String queueName;
    private Channel channel;

    public ConsumeMessage(String queueName) throws IOException, TimeoutException {
        this.queueName = queueName;
        RabbitMQConnection rabbitMQConnection = new RabbitMQConnection();
        Connection connection = rabbitMQConnection.createNewConnection();
        channel = connection.createChannel();
        channel.queueDeclare(this.queueName, false, false, false, null);
    }

    public void consumeMessage() throws IOException {
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String consumedMessage = new String(body, StandardCharsets.UTF_8);
                //super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("Consumed Message: " + consumedMessage);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
