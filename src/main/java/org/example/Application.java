package org.example;

import org.example.bot.ScheduleBot;
import org.example.util.FileUtil;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new ScheduleBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
