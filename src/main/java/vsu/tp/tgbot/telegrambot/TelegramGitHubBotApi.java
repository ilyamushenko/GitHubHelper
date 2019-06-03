package vsu.tp.tgbot.telegrambot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class TelegramGitHubBotApi {

    private final static String PROXY_HOST = "47.52.195.189"; /* proxy host */
    private final static Integer PROXY_PORT = 443; /* proxy port */

    private Logger logger = LoggerFactory.getLogger(TelegramGitHubBotApi.class);


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

            BotTelegram myBot = new BotTelegram(botOptions);
            telegramBotsApi.registerBot(myBot);
            logger.info("Bot registration successfully");
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
