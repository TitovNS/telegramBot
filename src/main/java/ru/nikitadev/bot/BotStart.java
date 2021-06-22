package ru.nikitadev.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class BotStart extends TelegramLongPollingBot {

    private long chat_id;
    private final String BOT_NAME = "bookRecipe1927_bot";
    private final String BOT_TOKEN = "1706729039:AAHm_-E0V5aV9Wu6coLbcPi9IMywOKoAniM";
    private final String help = "Актуальные запросы:\n Asterios-x? - информация о последнем убитом боссе. Где ? - рейты сервера(Пример: Asterios-x7)";
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    ArrayList<KeyboardRow> keyboard = new ArrayList<>();
    KeyboardRow keyboardFirstRow = new KeyboardRow();
    KeyboardRow keyboardSecondRow = new KeyboardRow();
    KeyboardRow keyboardThirdRow = new KeyboardRow();

    @Override
    public String getBotUsername() {
        return BOT_NAME;
        //возвращаем имя бота
    }

    @Override
    public void onUpdateReceived(Update e) {
        // Тут будет то, что выполняется при получении сообщения
        chat_id = e.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage().setChatId(chat_id);
        String text = e.getMessage().getText();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            sendMessage.setText(getMessage(text));
            execute(sendMessage);
        } catch (TelegramApiException err) {
            err.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
        //возвращаем токен бота
    }

    //возвращает сообщение о последнем убитом боссе
    public String getInfoLastBoss(int exp) {
        Book book = new Book(exp);
        if (exp == 7 || exp == 5 || exp == 3) {
            return "Последний убитый бос сервера [х" + exp + "]:\n\n" + book.getLastBoss();
        } else {
            return "Сервер не существует.";
        }

    }
    public String getInfoCabrio(int exp) {
        Book book = new Book(exp);
        if (exp == 7 || exp == 5 || exp == 3) {
            return "Сервер [х" + exp + "]:\n\n" + book.getCabrio() + ".";
        } else {
            return "Сервер не существует.";
        }

    }
    public String getInfoHallate(int exp) {
        Book book = new Book(exp);
        if (exp == 7 || exp == 5 || exp == 3) {
            return "Сервер [х" + exp + "]:\n\n" + book.getHallate() + ".";
        } else {
            return "Сервер не существует.";
        }

    }
    public String getInfoGolkonda(int exp) {
        Book book = new Book(exp);
        if (exp == 7 || exp == 5 || exp == 3) {
            return "Сервер [х" + exp + "]:\n\n" + book.getGolkonda() + ".";
        } else {
            return "Сервер не существует.";
        }

    }
    public String getInfoKernon(int exp) {
        Book book = new Book(exp);
        if (exp == 7 || exp == 5 || exp == 3) {
            return "Сервер [х" + exp + "]:\n\n" + book.getKernon() + ".";
        } else {
            return "Сервер не существует.";
        }

    }
    public String getMessage(String msg) {
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        if (msg.equals("/start")) {
            getKeyboardServer(); // клавиатура выбора сервера
            return "Это бот для сервера L2-Asterios. Выбери сервер:";
        } else if (msg.equals("[x7]")) {
            getKeyboardBoss(msg); // клавиатура выбора боссов
            return getInfoLastBoss(Integer.parseInt(msg.substring(2, 3)));
        } else if (msg.equals("[x5]")) {
            getKeyboardBoss(msg);
            return getInfoLastBoss(Integer.parseInt(msg.substring(2, 3)));
        } else if (msg.equals("[x3]")) {
            getKeyboardBoss(msg);
            return getInfoLastBoss(Integer.parseInt(msg.substring(2, 3)));
        } else if (msg.contains("Cabrio")) {
            getKeyboardBoss(msg);
            return getInfoCabrio(Integer.parseInt(msg.substring(2, 3)));
        } else if (msg.contains("ToI 3 Hallate")) {
            getKeyboardBoss(msg);
            return getInfoHallate(Integer.parseInt(msg.substring(2, 3)));
        } else if (msg.contains("ToI 8 Kernon")) {
            getKeyboardBoss(msg);
            return getInfoKernon(Integer.parseInt(msg.substring(2, 3)));
        } else if (msg.contains("ToI 11 Golkonda")) {
            getKeyboardBoss(msg);
            return getInfoGolkonda(Integer.parseInt(msg.substring(2, 3)));
        } else if (msg.contains("Последний убитый РБ")) {
            getKeyboardBoss(msg);
            return getInfoLastBoss(Integer.parseInt(msg.substring(2, 3)));
        } else {
            getKeyboardServer();
            return "Выбери сервер:";
        }


    }
    public void getKeyboardServer () {
        keyboard.clear();
        keyboardFirstRow.clear();
        keyboardFirstRow.add("[x7]");
        keyboardFirstRow.add("[x5]");
        keyboardFirstRow.add("[x3]");
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }
    public void getKeyboardBoss (String msg) {
        msg = msg.substring(2, 3);
        keyboard.clear();
        keyboardFirstRow.clear();
        keyboardSecondRow.clear();
        keyboardThirdRow.clear();

        keyboardFirstRow.add("[x" + msg + "] Cabrio");
        keyboardFirstRow.add("[x" + msg + "] ToI 3 Hallate");
        keyboardSecondRow.add("[x" + msg + "] ToI 8 Kernon");
        keyboardSecondRow.add("[x" + msg + "] ToI 11 Golkonda");
        keyboardThirdRow.add("Сменить сервер");
        keyboardThirdRow.add("[x" + msg + "] Последний убитый РБ");

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}
