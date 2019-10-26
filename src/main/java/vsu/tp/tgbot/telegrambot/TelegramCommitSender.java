package vsu.tp.tgbot.telegrambot;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TelegramCommitSender extends Thread {

    Long chatId;
    Message message;
    Map<GHRepository, GHCommit> previousInfoAboutCommits;

    public TelegramCommitSender(Long chatId, Message message) {
        this.chatId = chatId;
        this.message = message;
        this.previousInfoAboutCommits = TelegramBotUtils.getLastCommitInfo(this.chatId);

    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    List<GHCommit> update() {
        List<GHCommit> needToSendInfo = new ArrayList<>();
        Map<GHRepository, GHCommit> lastCommitInfo = TelegramBotUtils.getLastCommitInfo(chatId);
        Set<GHRepository> ghRepositories = lastCommitInfo.keySet();
        for (GHRepository repository : ghRepositories) {
            GHCommit lastCommitOnPreviousStep = previousInfoAboutCommits.get(repository);
            GHCommit lastCommitNow = lastCommitInfo.get(repository);
            if (!lastCommitOnPreviousStep.getSHA1().equals(lastCommitNow.getSHA1())) {
                needToSendInfo.add(lastCommitNow);
            }
        }
        previousInfoAboutCommits = lastCommitInfo;
        return needToSendInfo;
    }

    private void doSmth() {
        List<GHCommit> update = update();
        for (GHCommit commit : update) {
            String text = TelegramBotUtils.commitToString(commit);
            TelegramGitHubBotApi.sendMessage(message, text);
        }
    }

    @Override
    public void run() {
        while (true) {
            doSmth();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.run();
        }
    }
}
