package ru.nikitadev.bot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Book {
    private String Url = null; // url страницы
    private Document document; // хранит страницу
    private final int x7 = 8; // номер в url страницы об убитых босах. url сервера х7 - https://asterios.tm/index.php?cmd=rss&serv=8&filter=all
    private final int x5 = 0; // url сервера х5 - https://asterios.tm/index.php?cmd=rss&serv=0&filter=all
    private final int x3 = 6; // url сервера х3 - https://asterios.tm/index.php?cmd=rss&serv=6&filter=all

    public Book(int exp) {
        if (exp == 7) {
            exp = this.x7;
        } else if (exp == 5) {
            exp = this.x5;
        } else if (exp == 3) {
            exp = this.x3;
        }
        this.Url = "https://asterios.tm/index.php?cmd=rss&serv="+ exp + "&filter=all"; // параметр exp определяет данные с какого сервера астериоса нужны
        connect();
    }

    private void connect() {
        try {
            document = Jsoup.connect(Url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // возвращает тег <title>
    public String getTitle() {
        return document.title();
    }
    //возвращает подстроку с данными о последнем убитом боссе
    public String getLastBoss() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dtf.format( LocalDateTime.now());
        Elements elements = document.getElementsByTag("td");
        StringBuilder sb = new StringBuilder();
        sb.append(elements.text());
        int startResp = sb.indexOf(date); // находит начало подстроки (первая попавшаяся текущая дата)
        // проверяет есть ли с текущей датой босс. если нет, то берет предыдущий день.
        if (startResp < 0) {
            date = dtf.format( LocalDateTime.now().minusDays(1));
            startResp = sb.indexOf(date);
        }
        int finish = sb.indexOf(date, startResp + 1); // находит конец подстроки по следующей попавшейся дате.
        // проверяет, если не нашлась текущая дата в отрезке длинной до 2000, то уменьшает дату на день и ищет по дате
        if (finish >= 2000) {
            date = dtf.format( LocalDateTime.now().minusDays(1));
            finish = sb.indexOf(date, startResp + 1);
        }
        String respawn = sb.substring(startResp, finish); // вырезаем подстроку и передаем
        return respawn;
    }
    public String getCabrio() {
        Elements elements = document.getElementsByTag("td");
        StringBuilder sb = new StringBuilder();
        sb.append(elements.text());
        int startResp = sb.indexOf("Cabrio") - 50;
        int finish = sb.indexOf("Cabrio") + 6;
        String respawn = sb.substring(startResp, finish);
        return respawn;
    }
    public String getHallate() {
        Elements elements = document.getElementsByTag("td");
        StringBuilder sb = new StringBuilder();
        sb.append(elements.text());
        int startResp = sb.indexOf("Hallate") - 42;
        int finish = sb.indexOf("Hallate") + 7;
        String respawn = sb.substring(startResp, finish);
        return respawn;
    }
    public String getGolkonda() {
        Elements elements = document.getElementsByTag("td");
        StringBuilder sb = new StringBuilder();
        sb.append(elements.text());
        int startResp = sb.indexOf("Golkonda") - 40;
        int finish = sb.indexOf("Golkonda") + 8;
        String respawn = sb.substring(startResp, finish);
        return respawn;
    }
    public String getKernon() {
        Elements elements = document.getElementsByTag("td");
        StringBuilder sb = new StringBuilder();
        sb.append(elements.text());
        int startResp = sb.indexOf("Kernon") - 31;
        int finish = sb.indexOf("Kernon") + 6;
        String respawn = sb.substring(startResp, finish);
        return respawn;
    }

}
