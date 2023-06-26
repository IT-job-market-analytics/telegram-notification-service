package com.example.telegramnotificationservice.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.example.telegramnotificationservice.util.Constants.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        if(update.hasMessage()) {
            String chatId = update.getMessage().getChatId().toString();
            String text = update.getMessage().getText();
            if(text != null) {
                sendMessage.setChatId(chatId);
                sendMessage.setText(TEST_OUTPUT + text);

                try {
                    this.execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Метод возвращает имя бота для связи с сервером Telegram
     */
    public String getBotUsername() {
        return BOT_NAME;
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     * @return token для бота
     */
    public String getBotToken() {
        return TOKEN_BOT;
    }
}