package ru.nikitadev.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class BotApplication extends BotStart{

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(BotApplication.class, args);
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new BotApplication());
            MyRunnable runnable = new MyRunnable(10);
            runnable.run();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
