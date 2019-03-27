import bot.Bot;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Main {

    private static String PROXY_HOST = "207.180.233.152"; /* proxy host */
    private static Integer PROXY_PORT = 50195; /* proxy port */

//    private static String PROXY_USER = "hotgram" /* proxy user */;
//    private static String PROXY_PASSWORD = "0000086c67be17" /* proxy password */;

    public static void main(String[] args) {
        try {
            ApiContextInitializer.init();

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

//            CredentialsProvider credsProvider = new BasicCredentialsProvider();
//            credsProvider.setCredentials(
//                    new AuthScope(PROXY_HOST, PROXY_PORT),
//                    new UsernamePasswordCredentials(PROXY_USER, PROXY_PASSWORD));


            HttpHost httpHost = new HttpHost(PROXY_HOST, PROXY_PORT);

            RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).setAuthenticationEnabled(false).build();
            botOptions.setRequestConfig(requestConfig);
//            botOptions.setCredentialsProvider(credsProvider);
            System.out.println(botOptions.getBaseUrl());
            System.out.println(botOptions.getProxyHost());
            botOptions.setProxyHost(PROXY_HOST);
            botOptions.setProxyPort(PROXY_PORT);
            System.out.println("подключили прокси");
            System.out.println(botOptions.getBaseUrl());
            System.out.println(botOptions.getProxyHost());



            Bot bot = new Bot(botOptions);

            System.out.println("Предпоследние действие");
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
