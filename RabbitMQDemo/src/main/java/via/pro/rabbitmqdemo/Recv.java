package via.pro.rabbitmqdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import via.pro.rabbitmqdemo.models.Message;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            try {
                byte[] body = delivery.getBody();

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


                Message message = objectMapper.readValue(body, Message.class);


                Calendar calendar = Calendar.getInstance();
                calendar.setTime(message.getTimestamp());
                int seconds = calendar.get(Calendar.SECOND);


                System.out.println(" [x] Received message with timestamp: " +
                        message.getTimestamp() +
                        " and counter: " + message.getCounter());


                if(message.getTimestamp().getTime() < System.currentTimeMillis() - 60000) {
                    System.out.println(" [x] Message is over 1 minute old, removing it");
                    return;
                }

                if(message.getCounter() > 5) {
                    System.out.println(" [x] Message has been sent more than 5 times, removing it");
                    return;
                }

                if(seconds % 2 != 0) {

                    System.out.println(" [x] Odd second - sending message again");
                    message.setCounter(message.getCounter() + 1);

                    // Simulate a random delay
                    Thread.sleep(1000 + (int)(Math.random() * 1000));
                    byte[] messageBodyBytes = objectMapper.writeValueAsBytes(message);

                    channel.basicPublish("", QUEUE_NAME, null, messageBodyBytes);
                } else {
                    System.out.println(" [x] Even second");

                    //add to the database

                }

            } catch (Exception e) {
                System.err.println("Error processing message: " + e.getMessage());
                e.printStackTrace();
            }
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}