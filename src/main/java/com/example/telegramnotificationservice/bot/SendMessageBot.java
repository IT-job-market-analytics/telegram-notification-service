package com.example.telegramnotificationservice.bot;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class SendMessageBot {
    public static void sendToTelegram(long idUser, String botToken, String linkWork) {
        String startURL ="https://api.telegram.org/bot";
        String apiToken = botToken;
        String middleUrlAddress = "/sendMessage?chat_id=";
        String chatId = "" + idUser;
        String endUrlAddress = "&text=";

        String testText = linkWork;

        String urlString = startURL + apiToken + middleUrlAddress + chatId + endUrlAddress + testText;
        System.out.println(urlString);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}