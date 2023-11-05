package org.example.bot;

import org.example.keyboardFactory.InlineKeyboardFactory;
import org.example.config.Config;
import org.example.constant.Constants;
import org.example.model.Course;
import org.example.model.Subject;
import org.example.model.User;
import org.example.service.CourseService;
import org.example.service.SubjectService;
import org.example.service.UserService;
import org.example.util.DateUtil;
import org.example.util.MessageUtils;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Reply;
import org.telegram.abilitybots.api.util.AbilityUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

public class ScheduleBot extends AbilityBot implements Constants {

    private final UserService userService;
    private final CourseService courseService;
    private final SubjectService subjectService;

    public ScheduleBot() {
        super(Config.getToken(), Config.getName());
        userService = new UserService();
        courseService = new CourseService();
        subjectService = new SubjectService();
    }

    @Override
    public long creatorId() {
        return 878758046;
    }

    /**
     * Функция вызываеться по команде /start<br>
     * Выводит приветственное сообщение бота
     * @return
     */
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

    /**
     * Функция вызываеться по команде /reg<br>
     * Выводит клавиатуру содержащуюю все курсы
     * @return
     */
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

    /**
     * Функция вызваеться при нажатии на клавиатуру с выбором курса<br>
     * Меняет описание и клавиатуру сообщения на выбор группы
     * @return
     */
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

    /**
     * Функция-условие определяет нажатие на клавиатуру с выбором курса
     * @return
     */
    private Predicate<Update> isPressInlineKeyboardAllCourses() {
        return upd -> upd.hasCallbackQuery() && upd.getCallbackQuery().getData().startsWith("course");
    }

    /**
     * Функция вызываеться при нажатии на клавиатуру с выбором группы<br>
     * Добаваляет или изменяет запись в базе данных о пользователе
     * @return
     */
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

    /**
     * Функция-условие определяет нажатие на клавиатуру с выбором группы
     */
    private Predicate<Update> isPressInlineKeyboardAllGroupsForCourse() {
        return upd -> upd.hasCallbackQuery() && upd.getCallbackQuery().getData().startsWith("group");
    }

    /**
     * Функция вызывается по команде /today<br>
     * Выводит расписание на сегодня
     */
    public Ability now() {
        return Ability
                .builder()
                .name("now")
                .info(NOW_INFO)
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(ctx.chatId());
                    sendMessage.setParseMode("HTML");
                    User user = userService.findUserByTelegramId(ctx.user().getId());
                    if(user == null) {
                        sendMessage.setText(UNREGISTER_MESSAGE);
                    } else {
                        LocalDateTime localDateTime = LocalDateTime.now();
                        String header = MessageUtils.header(user, localDateTime);
                        String body;
                        List<Subject> subjects = subjectService.findByCourseAndGroupAndDateAndIsNumerator_NOW(user.getCourse(), user.getGroup(), localDateTime, DateUtil.isNumerator(localDateTime.toLocalDate()));
                        if(subjects.isEmpty()) {
                            body = NO_SUBJECTS_MESSAGE;
                        } else {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (Subject subject: subjects) {
                                stringBuilder.append(MessageUtils.subject(subject));
                            }
                            body = stringBuilder.toString();
                        }
                        sendMessage.setText(header +  body);
                    }
                    silent.execute(sendMessage);
                })
                .build();
    }

    /**
     * Функция вызывается по команде /week<br>
     * Выводит расписание на неделю
     */
    public Ability today() {
        return Ability
                .builder()
                .name("today")
                .info(TODAY_INFO)
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(ctx.chatId());
                    sendMessage.setParseMode("HTML");
                    User user = userService.findUserByTelegramId(ctx.user().getId());
                    if(user == null) {
                        sendMessage.setText(UNREGISTER_MESSAGE);
                    } else {
                        LocalDateTime localDateTime = LocalDateTime.now();
                        String header = MessageUtils.header(user, localDateTime);
                        String body;
                        List<Subject> subjects = subjectService.findByCourseAndGroupAndDateAndIsNumerator_Day(user.getCourse(), user.getGroup(), localDateTime, DateUtil.isNumerator(localDateTime.toLocalDate()));
                        if(subjects.isEmpty()) {
                            body = NO_SUBJECTS_MESSAGE;
                        } else {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (Subject subject: subjects) {
                                stringBuilder.append(MessageUtils.subject(subject));
                            }
                            body = stringBuilder.toString();
                        }
                        sendMessage.setText(header +  body);
                    }
                    silent.execute(sendMessage);
                })
                .build();
    }
}