package org.example.bot;

import org.example.keyboardFactory.InlineKeyboardFactory;
import org.example.config.Config;
import org.example.constant.Constants;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

public class ScheduleBot extends AbilityBot implements Constants {

    public ScheduleBot() {
        super(Config.getToken(), Config.getName());
    }

    @Override
    public long creatorId() {
        return 878758046;
    }

    public Ability start() {
        return Ability
                .builder()
                .name("start")
                .info(START_INFO)
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> silent.send(START_MESSAGE, ctx.chatId()))
                .build();
    }

    public Ability registration() {
        return Ability
                .builder()
                .name("reg")
                .info(REGISTRATION_INFO)
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(ctx.chatId());
                    sendMessage.setText(REGISTRATION_MESSAGE);
                    sendMessage.setReplyMarkup(InlineKeyboardFactory.allCourses());
                    silent.execute(sendMessage);
                })
                .build();
    }
}