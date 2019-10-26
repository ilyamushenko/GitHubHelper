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

    private static CommitListener commitListener = new CommitListener();

    @Override
    public void onUpdateReceived(Update update) {
        List<User> newChatMembers = update.getMessage().getNewChatMembers();
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
                commitListener.addListeners(message, message.getChatId());
                //ToDo REPLIST COMMAND
            } else if (messageText.equals("Список репозиториев, на которые подписан")) {

                List<Repository> allSubscribedRepos = TelegramBotUtils.getAllSubscribedRepos(message.getChatId());
                logger.info("Set: {}", allSubscribedRepos);
                String resultTextMessage = TelegramBotUtils.repositoriesToString(allSubscribedRepos);
                sendMessageTelegramBot(message, resultTextMessage);
                //TODO INFO COMMAND
            }
        }
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

    @Override
    public String getBotUsername() {
        return "GitHubHelperTgBot"; //
    }


    @Override
    public String getBotToken() {
        return "953464988:AAF5y3Q8eqVmaHYoF75dUlVC0qcSbacFydY";
    }

}