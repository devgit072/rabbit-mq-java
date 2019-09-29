package com.devraj.rabbitmq.connection;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

// Create one singleton connection factory which can be used to create many conenctions.
public class RabbitMQConnection {

    private ConnectionFactory connectionFactory;
    private String configFile = "rabbitmq.properties";

    public RabbitMQConnection() throws IOException {
        initiliazeConnectionFactory();
    }

    private void initiliazeConnectionFactory() throws IOException {
        if (connectionFactory != null) return;
        connectionFactory = new ConnectionFactory();
        RabbitMQProperties rabbitMQProperties = getRabbitMQProperties();
        connectionFactory.setHost(rabbitMQProperties.getEndpoint());
        connectionFactory.setPort(rabbitMQProperties.getPort());
        connectionFactory.setUsername(rabbitMQProperties.getUserName());
        connectionFactory.setPassword(rabbitMQProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitMQProperties.getVirtualHost());
    }

    private RabbitMQProperties getRabbitMQProperties() throws IOException {
        Properties properties = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream(configFile);
        RabbitMQProperties rabbitMQProperties = new RabbitMQProperties();
        if (is != null) {
            properties.load(is);
            rabbitMQProperties.setEndpoint(properties.getProperty("endPoint"));
            rabbitMQProperties.setUserName(properties.getProperty("userName"));
            rabbitMQProperties.setPassword(properties.getProperty("password"));
            rabbitMQProperties.setPort(Integer.parseInt(properties.getProperty("port")));
            rabbitMQProperties.setVirtualHost(properties.getProperty("virtualHost"));
        }

        return rabbitMQProperties;
    }

    public Connection createNewConnection() throws IOException, TimeoutException {
        return connectionFactory.newConnection();
    }
}
