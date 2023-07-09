package com.example.telegramnotificationservice.rabbit;

import com.example.telegramnotificationservice.dto.MessageDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    private static int id;
    private static String text;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        RabbitMQConsumer.id = id;
    }

    public static String getText() {
        return text;
    }

    public static void setText(String text) {
        RabbitMQConsumer.text = text;
    }

    @RabbitListener(queues = {"${rabbitmq.queue}"})
    public void consume(MessageDTO message) {
        setId(message.getIdChat());
        setText(message.getText());
    }
}
