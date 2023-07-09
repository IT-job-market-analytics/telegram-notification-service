package com.example.telegramnotificationservice.bot;

import com.example.telegramnotificationservice.rabbit.RabbitMQConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.token}")
    private String token;
    @Value("${bot.name}")
    private String name;

    private final static String TEST_OUTPUT = "Ваши вакансии: ";
    private final static String TEST_CRITERION = "Tecт. Java+стажировка: ";

    public void onUpdateReceived(Update update) {

        String command = update.getMessage().getText();

        if (command.equals("/start")) {
            String message_your_links = TEST_OUTPUT;
            String message_criterion = TEST_CRITERION;
            SendMessage response = new SendMessage();
            SendMessage responseTest = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            responseTest.setChatId(update.getMessage().getChatId().toString());
            response.setText(message_your_links);
            responseTest.setText(message_criterion);

            try {
                this.execute(response);
                this.execute(responseTest);
                SendMessageBot.sendToTelegram(RabbitMQConsumer.getId(), token, RabbitMQConsumer.getText());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return name;
    }
}