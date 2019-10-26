package vsu.tp.tgbot.telegrambot;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import vsu.tp.tgbot.Utils.Utils;
import vsu.tp.tgbot.database.models.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


class BotTelegram extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(BotTelegram.class);

    BotTelegram(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public void onUpdateReceived(Update update) {
        /*List<User> newChatMembers = update.getMessage().getNewChatMembers();
        if (newChatMembers != null) {
            for (User user : newChatMembers) {
               logger.info("Username: {}, FirstName: {}", user.getUserName(), user.getFirstName());
            }
        }
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String messageText = message.getText();
            logger.info("Recived message: " + messageText);
            //ToDo START COMMAND
            if (messageText.equals("/start")) {
                sendMessageTelegramBot(message, "Привет, " + message.getFrom().getFirstName() + "! Введите на сайте код: " + message.getChatId());
            //ToDo REPLIST COMMAND
            } else if (messageText.equals("Список репозиториев, на которые подписан")) {

                Set<Repository> allSubscribedRepos = TelegramBotUtils.getAllSubscribedRepos(message.getChatId());
                logger.info("Set: {}", allSubscribedRepos);
                StringBuilder allSubscribedReposInBeautifulViewForMessage = new StringBuilder();
                int i = 0;
                for (Repository repository : allSubscribedRepos) {
                    allSubscribedReposInBeautifulViewForMessage.append(i).append(". ").append(repository);
                    i++;
                }
                sendMessageTelegramBot(message, allSubscribedReposInBeautifulViewForMessage.toString());
                //TODO INFO COMMAND
            } else if (Utils.stringBeginWith(messageText, "/info")) {
                if (messageText.split(" ").length == 2)
                    sendMessageTelegramBot(message, TelegramBotUtils.getLastCommitInfo(message.getChatId(), messageText.split(" ")[1]));
                else
                    sendMessageTelegramBot(message, TelegramBotUtils.getLastCommitInfo(message.getChatId(), "Укажите имя репозитория в команде"));
            }
//            switch (messageText) {
//                case "/start":
//                    sendMessageTelegramBot(message, "Привет, " + message.getFrom().getFirstName() + "! Введите на сайте код: " + message.getChatId());
//                    break;
//                case "Список репозиториев, на которые подписан":
//                    Set<Repository> allSubscribedRepos = TelegramBotUtils.getAllSubscribedRepos(message.getChatId());
//                    logger.info("Set: {}", allSubscribedRepos);
//                    StringBuilder allSubscribedReposInBeautifulViewForMessage = new StringBuilder();
//                    int i = 0;
//                    for(Repository repository: allSubscribedRepos) {
//                        allSubscribedReposInBeautifulViewForMessage.append(i).append(". ").append(repository);
//                        i++;
//                    }
//                    sendMessageTelegramBot(message, allSubscribedReposInBeautifulViewForMessage.toString());
//                    break;
//                case "/info":
//                    if(messageText.split(" ").length > 1) {
//                        sendMessageTelegramBot(message, TelegramBotUtils.getLastCommitInfo(message.getChatId(), messageText.split(" ")[1]));
//                    } else {
//                        sendMessageTelegramBot(message, TelegramBotUtils.getLastCommitInfo(message.getChatId(), "Укажите имя репозитория в команде"));
//                    }
//                    break;
//                default:
//                    sendMessageTelegramBot(message, "Не понимаю :(");
//                    break;
////                default:
////                    System.out.println("default");
////                    sendMessageTelegramBot(message, "Введите команду");
////                    break;
//            }
        }*/
    }

    public void sendMessageTelegramBot (Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("Список репозиториев, на которые подписан");

        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

//    private void sendMessageTelegramBot(Message message, String text) {
//        Long chatId = message.getChatId();
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.enableMarkdown(true);
//        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setText(text);
//        //sendMessageTelegramBot.setReplyToMessageId(message.getMessageId()); // для ответа на сообщение
//        //sendMessageTelegramBot.setText(text); //отправка сообщения
//        System.out.println(text);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public String getBotUsername() {
        return "GitHubHelperTgBot"; //
    }


    @Override
    public String getBotToken() {
        return "953464988:AAF5y3Q8eqVmaHYoF75dUlVC0qcSbacFydY";
    }

}