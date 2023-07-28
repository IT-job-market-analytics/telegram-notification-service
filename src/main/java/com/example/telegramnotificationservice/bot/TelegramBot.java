package com.example.telegramnotificationservice.bot;

import com.example.telegramnotificationservice.exceptions.TelegramApiQuotaException;
import com.example.telegramnotificationservice.service.QuotaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String name;

    private final QuotaService quotaService;


    public TelegramBot(@Value("${bot.token}") String botToken, QuotaService quotaService) {
        super(botToken);
        this.quotaService = quotaService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // do nothing yet
    }

    public void sendMessage(long chatId, String text) throws TelegramApiException {
        try {
            quotaService.requestQuota();
            log.debug("Has quota");

            SendMessage command = new SendMessage();
            command.setChatId(chatId);
            command.setText(text);

            this.execute(command);
        } catch (TelegramApiQuotaException e) {
            log.error("We have exceeded the Telegram API quotas by swallowing the exception so the message will not be sent to the user");
        } catch (TelegramApiException e) {
            log.error("Unknown exception occurred");
            throw e;
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }
}