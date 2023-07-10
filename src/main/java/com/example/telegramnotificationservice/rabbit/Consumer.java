package com.example.telegramnotificationservice.rabbit;

import com.example.telegramnotificationservice.bot.TelegramBot;
import com.example.telegramnotificationservice.dto.TelegramNotificationTaskDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final TelegramBot telegramBot;

    public Consumer(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @RabbitListener(queues = {"${rabbitmq.queue}"})
    public void consume(TelegramNotificationTaskDTO message) {
        telegramBot.sendMessage(message.getChatId(), message.getMessage());
    }
}
