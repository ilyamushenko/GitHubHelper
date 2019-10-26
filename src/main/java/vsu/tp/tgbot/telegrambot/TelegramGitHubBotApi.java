package vsu.tp.tgbot.telegrambot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import vsu.tp.tgbot.database.service.GithubUserService;
import vsu.tp.tgbot.database.service.implementations.GithubUserServiceImplementation;

import java.util.logging.LogManager;
public class TelegramGitHubBotApi {

    //FIXME: if proxy doesn't work - https://hidemy.name/ru/proxy-list/?type=5#list
    private final static String PROXY_HOST = "212.47.240.194"; /* proxy host */
    private final static Integer PROXY_PORT = 1080; /* proxy port */

    private Logger logger = LoggerFactory.getLogger(TelegramGitHubBotApi.class);

    private BotTelegram myBot;

    public void start() {
        try {
            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
            logger.info("Set proxy");
            botOptions.setProxyHost(PROXY_HOST);
            botOptions.setProxyPort(PROXY_PORT);
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
            logger.info("Proxy success!");

            myBot = new BotTelegram(botOptions);
            telegramBotsApi.registerBot(myBot);
            logger.info("Bot registration successfully");
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

}
