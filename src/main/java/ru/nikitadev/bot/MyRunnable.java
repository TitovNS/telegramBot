package ru.nikitadev.bot;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyRunnable implements Runnable {
    private int var;
    private String chat_id = "449638628";
    public MyRunnable(int var) {
        this.var = var;
    }


    @Override
    public void run() {
        String respawn = "null";

        while (true) {
            Book book = new Book(7);
            if (!respawn.equals(book.getLastBoss())) {
                respawn = book.getLastBoss();
                SendMessage message = new SendMessage();
                message.setText(respawn);
                message.setParseMode(ParseMode.MARKDOWN);
                message.setChatId(Long.parseLong(chat_id));
                try {
                    //просто вызываем класс BotStart с методом execute
                    new BotStart().execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
