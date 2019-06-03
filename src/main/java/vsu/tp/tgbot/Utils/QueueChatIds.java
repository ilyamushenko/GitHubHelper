package vsu.tp.tgbot.Utils;

import java.util.ArrayList;
import java.util.List;

public class QueueChatIds {

    private static List<Long> chatIds = new ArrayList<>();

    private QueueChatIds() {}

    public static List<Long> getChatIds() {
        return chatIds;
    }

    public static void setChatIds(List<Long> chatIds) {
        QueueChatIds.chatIds = chatIds;
    }

    public static void add(Long id) {
        chatIds.add(id);
    }

    public static boolean check(Long id) {
        if (chatIds.contains(id)) {
            chatIds.remove(id);
            return true;
        }
        return false;
    }
}
