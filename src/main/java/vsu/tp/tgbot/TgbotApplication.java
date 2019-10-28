package vsu.tp.tgbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import vsu.tp.tgbot.telegrambot.TelegramGitHubBotApi;

@SpringBootApplication
public class TgbotApplication {

    public static void main(String[] args) {
        TelegramGitHubBotApi tgApi = new TelegramGitHubBotApi();
        tgApi.start();
        SpringApplication.run(TgbotApplication.class, args);
        //добавить многопоточный метод, который будет проверять обновление репозиториев по последнему коммиту
    }

}
