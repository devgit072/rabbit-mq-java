package com.devraj.rabbitmq.connection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RabbitMQProperties {
    private String endpoint;
    private int port;
    private String userName;
    private String password;
    private String virtualHost;
}
