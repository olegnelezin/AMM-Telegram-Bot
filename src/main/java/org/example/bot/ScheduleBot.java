package org.example.bot;

import org.example.config.Config;
import org.example.constant.Constants;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;

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
                .info("start")
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> silent.send(startResponse, ctx.chatId()))
                .build();
    }
}