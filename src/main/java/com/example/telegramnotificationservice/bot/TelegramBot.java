package com.example.telegramnotificationservice.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String name;

    public TelegramBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        // do nothing yet
    }

    public void sendMessage(long chatId, String text) throws TelegramApiException {
        SendMessage command = new SendMessage();
        command.setChatId(chatId);
        command.setText(text);

        this.execute(command);
    }

    @Override
    public String getBotUsername() {
        return name;
    }
}