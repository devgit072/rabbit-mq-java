package com.devrajs.rabbitmq.publisher;

import com.devraj.rabbitmq.connection.RabbitMQConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PublishMessage {
    private String queueName;
    private Connection connection;
    private Channel channel;

    public PublishMessage(String queueName) throws IOException, TimeoutException {
        this.queueName = queueName;
        RabbitMQConnection rabbitMQConnection = new RabbitMQConnection();
        connection = rabbitMQConnection.createNewConnection();
        channel = connection.createChannel();
        // QueueName, durable, exclusive, autodelete, map_argument_for_rabbitMQ
        // durable: queue persists if rabbitMQ restarts.
        // exclusive: this queue will be exclusive for this connection and no connection can use it.
        // Server will delete queue if it is no longer in use.
        channel.queueDeclare(this.queueName, false, false, false, null);
    }

    public void publishMessage(String message) throws IOException {
        channel.basicPublish("", this.queueName, null, message.getBytes());
        System.out.println("Published: " + message);
    }

    public void closeChannelAndConnection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
