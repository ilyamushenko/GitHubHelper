package vsu.tp.tgbot.telegrambot;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommitListener {

    Map<Message, TelegramCommitSender> messageTelegramCommitSenderMap;
    List<Long> listOfIds;

    public CommitListener() {
        this.messageTelegramCommitSenderMap = new HashMap<>();
        this.listOfIds = new ArrayList<>();

    }

    public void addListeners(Message message, Long chadId) {
        if (!listOfIds.contains(chadId)) {
            TelegramCommitSender sender = new TelegramCommitSender(chadId, message);
            sender.start();
            messageTelegramCommitSenderMap.put(message, sender);
            listOfIds.add(chadId);
        }
    }
}
