package via.pro.rabbitmqdemo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import via.pro.rabbitmqdemo.models.Message;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Send {

    private final static String QUEUE_NAME = "hello";


    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            while (true) {
                int randomTimeout = 1000 + (int)(Math.random() * 1000);
                System.out.println("Sleeping for " + randomTimeout + " milliseconds");
                Thread.sleep(randomTimeout);

                Message message = new Message(new Timestamp(System.currentTimeMillis()), 1);

                byte[] messageBodyBytes = objectMapper.writeValueAsBytes(message);

                channel.basicPublish("", QUEUE_NAME, null, messageBodyBytes);
                System.out.println("Sending message: " + objectMapper.writeValueAsString(message));
            }
        }
    }
}