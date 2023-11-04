package org.example.bot;

import org.example.keyboardFactory.InlineKeyboardFactory;
import org.example.config.Config;
import org.example.constant.Constants;
import org.example.model.Course;
import org.example.model.Group;
import org.example.service.CourseService;
import org.example.service.UserService;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Reply;
import org.telegram.abilitybots.api.util.AbilityUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

public class ScheduleBot extends AbilityBot implements Constants {

    private final UserService userService;
    private final CourseService courseService;

    public ScheduleBot() {
        super(Config.getToken(), Config.getName());
        userService = new UserService();
        courseService = new CourseService();
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

    public Ability start_registration() {
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

    public Reply chooseGrope() {
        BiConsumer<BaseAbilityBot, Update> action = (bot, upd) -> {
            int courseNumber = Integer.parseInt(upd.getCallbackQuery().getData().split("_")[1]);
            Course course = courseService.findByNumber(courseNumber);

            Long chatId = AbilityUtils.getChatId(upd);
            Integer messageId = upd.getCallbackQuery().getMessage().getMessageId();
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(chatId);
            editMessageText.setMessageId(messageId);
            editMessageText.setText(CHOOSE_COURSE_MESSAGE);
            editMessageText.setReplyMarkup(InlineKeyboardFactory.allGroupsForCourse(course));
            silent.execute(editMessageText);
        };
        return Reply.of(action, Flag.CALLBACK_QUERY, isPressInlineKeyboardAllCourses());
    }

    private Predicate<Update> isPressInlineKeyboardAllCourses() {
        return upd -> upd.hasCallbackQuery() && upd.getCallbackQuery().getData().startsWith("course");
    }

    public Reply addOrUpdateUserInDB() {
        BiConsumer<BaseAbilityBot, Update> action = (bot, upd) -> {
            String[] data = upd.getCallbackQuery().getData().split("_");
            int groupNumber = Integer.parseInt(data[1]);
            int courseNumber = Integer.parseInt(data[3]);
            Long telegramId = upd.getCallbackQuery().getFrom().getId();
            userService.saveOrUpdate(telegramId, courseNumber, groupNumber);
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(AbilityUtils.getChatId(upd));
            editMessageText.setMessageId(upd.getCallbackQuery().getMessage().getMessageId());
            editMessageText.setText(SUCCESSFUL_REGISTRATION);
            silent.execute(editMessageText);
        };
        return Reply.of(action, Flag.CALLBACK_QUERY, isPressInlineKeyboardAllGroupsForCourse());
    }

    private Predicate<Update> isPressInlineKeyboardAllGroupsForCourse() {
        return upd -> upd.hasCallbackQuery() && upd.getCallbackQuery().getData().startsWith("group");
    }
}