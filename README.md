Queue is one of the best way (if not best) to create distributed systems. Specially in MicroServices
architecture, queue holds its importance.       
Queue can be used for:
1) Decouple Software components.
2) Perform asynchronous operation.
3) Perform some offline or batch(analytics kind of).
4) Implement chat server.


<h4>Install RabbitMQ</h4>
Docker is convenient way to install RabbitMQ amd best way to install RabbitMQ is using docker.      
How to run rabbitmq as docker:      
docker container run -d --hostname local-rabbit-mq -p 15672:15672 -p 5672:5672 --name rabbit rabbitmq:3.8.0-rc.1-management     
I have picked management tag , because I dont have to install separate management plugin(Erlang). It will provide you GUI for viewing all thing in your rabbitMQ.

If everythings works fine, then you can visit RabbitMQ UI interface: localhost:15672 (guest/guest)      
Please note that rabbitmq runs on 5672 and UI runs on 15672.   
So your application need to point to 5672 port.

In this project, I have implemented basic publisher and consumer using Java and rabbitMQ client library.
I have put ample code comments, and class name descriptive in-order to make it easy to understand.
In Application.java class, publisher and consumer publishes and consumes the messages respectively.



