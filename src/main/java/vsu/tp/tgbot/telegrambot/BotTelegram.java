package vsu.tp.tgbot.telegrambot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


class BotTelegram extends TelegramLongPollingBot {

    private Logger logger = LoggerFactory.getLogger(BotTelegram.class);

    BotTelegram(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public void onUpdateReceived(Update update) {
        List<User> newChatMembers = update.getMessage().getNewChatMembers();
        if (newChatMembers != null) {
            for (User user : newChatMembers) {
                logger.info(user.getUserName() + " " + user.getFirstName());
            }
        }
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            logger.info("Recived message: " + message.getText());
            switch (message.getText()) {
                case "/help":
                    sendMessageTelegramBot(message, "Чем могу помочь?");
                    break;
                case "/settings":
                    sendMessageTelegramBot(message, "Что будем настраивать?");
                    break;
//                default:
//                    System.out.println("default");
//                    sendMessageTelegramBot(message, "Введите команду");
//                    break;
            }
        }
    }

    private void sendMessageTelegramBot(Message message, String text) {
        Long chatId = message.getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        //sendMessageTelegramBot.setReplyToMessageId(message.getMessageId()); // для ответа на сообщение
        //sendMessageTelegramBot.setText(text); //отправка сообщения
        System.out.println(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "tg_test_github_bot";
    }


    @Override
    public String getBotToken() {
        return "630193940:AAGv2wYNLXnBcXsYjGGZEcs2EBBwO2h8bX4";
    }

}