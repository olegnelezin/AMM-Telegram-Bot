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
import org.telegram.abilitybots.api.toggle.CustomToggle;
import org.telegram.abilitybots.api.util.AbilityUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

public class ScheduleBot extends AbilityBot implements Constants {

    private static final CustomToggle toggle = new CustomToggle()
            .turnOff("commands")
            .turnOff("claim")
            .turnOff("backup")
            .turnOff("recover")
            .turnOff("promote")
            .turnOff("demote")
            .turnOff("ban")
            .turnOff("unban");
    private final UserService userService;
    private final CourseService courseService;
    private final SubjectService subjectService;

    public ScheduleBot() {
        super(Config.getToken(), Config.getName(), toggle);
        userService = new UserService();
        courseService = new CourseService();
        subjectService = new SubjectService();
    }

    @Override
    public long creatorId() {
        return 980677384;
    }

    /**
     * Функция вызываеться по команде /start<br>
     * Выводит приветственное сообщение бота
     */
    public Ability start() {
        return Ability
                .builder()
                .name("start")
                .info(START_INFO)
                .locality(USER)
                .privacy(PUBLIC)
                .action(ctx -> silent.send(START_MESSAGE, ctx.chatId()))
                .build();
    }

    /**
     * Функция вызываеться по команде /reg<br>
     * Выводит клавиатуру содержащуюю все курсы
     */
    public Ability start_registration() {
        return Ability
                .builder()
                .name("reg")
                .info(REGISTRATION_INFO)
                .locality(USER)
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
     */
    public Reply chooseGroup() {
        BiConsumer<BaseAbilityBot, Update> action = (bot, upd) -> {
            int courseNumber = Integer.parseInt(upd.getCallbackQuery().getData().split("_")[1]);
            Course course = courseService.findByNumber(courseNumber);

            Long chatId = AbilityUtils.getChatId(upd);
            Integer messageId = upd.getCallbackQuery().getMessage().getMessageId();
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(chatId);
            editMessageText.setMessageId(messageId);
            editMessageText.setText(CHOOSE_GROUP_MESSAGE);
            editMessageText.setReplyMarkup(InlineKeyboardFactory.allGroupsForCourse(course));
            silent.execute(editMessageText);
        };
        return Reply.of(action, Flag.CALLBACK_QUERY, isPressInlineKeyboardAllCourses());
    }

    /**
     * Функция-условие определяет нажатие на клавиатуру с выбором курса
     */
    private Predicate<Update> isPressInlineKeyboardAllCourses() {
        return upd -> upd.hasCallbackQuery() && upd.getCallbackQuery().getData().startsWith("course");
    }

    /**
     * Функция вызывается при нажатии кнопки "назад" при выборе группы<br>
     * Меняет сообщение на выбор курса
     */
    public Reply backToChooseCourse() {
        BiConsumer<BaseAbilityBot, Update> action = (bot, upd) -> {
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(AbilityUtils.getChatId(upd));
            editMessageText.setMessageId(upd.getCallbackQuery().getMessage().getMessageId());
            editMessageText.setText(REGISTRATION_MESSAGE);
            editMessageText.setReplyMarkup(InlineKeyboardFactory.allCourses());
            silent.execute(editMessageText);
        };
        return Reply.of(action, Flag.CALLBACK_QUERY, isPressButtonBackToCourse());
    }

    /**
     * Функция-условие определяет нажатие на кнопку назад при выборе группы
     */
    private Predicate<Update> isPressButtonBackToCourse() {
        return upd -> upd.hasCallbackQuery() && upd.getCallbackQuery().getData().equals("backToCourse");
    }

    /**
     * Функция вызваеться при нажатии на клавиатуру с выбором группы<br>
     * Меняет описание и клавиатуру сообщения на выбор подгруппы
     */
    public Reply chooseSubgroup() {
        BiConsumer<BaseAbilityBot, Update> action = (bot, upd) -> {
            String[] data = upd.getCallbackQuery().getData().split("_");
            int courseNumber = Integer.parseInt(data[2]);
            int groupNumber = Integer.parseInt(data[4]);
            long chatId = AbilityUtils.getChatId(upd);
            int messageId = upd.getCallbackQuery().getMessage().getMessageId();

            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(chatId);
            editMessageText.setMessageId(messageId);
            editMessageText.setText(CHOOSE_SUBGROUP_MESSAGE);
            editMessageText.setReplyMarkup(InlineKeyboardFactory.allSubgroup(courseNumber, groupNumber));
            silent.execute(editMessageText);
        };
        return Reply.of(action, Flag.CALLBACK_QUERY, isPressInlineKeyboardAllGroupsForCourse());
    }

    /**
     * Функция-условие определяет нажатие на клавиатуру с выбором группы
     */
    private Predicate<Update> isPressInlineKeyboardAllGroupsForCourse() {
        return upd -> upd.hasCallbackQuery() && upd.getCallbackQuery().getData().startsWith("chooseGroup");
    }

    /**
     * Функция вызываеться при нажатии на клавиатуру с выбором группы<br>
     * Добаваляет или изменяет запись в базе данных о пользователе
     */
    public Reply addOrUpdateUserInDB() {
        BiConsumer<BaseAbilityBot, Update> action = (bot, upd) -> {
            String[] data = upd.getCallbackQuery().getData().split("_");
            int courseNumber = Integer.parseInt(data[2]);
            int groupNumber = Integer.parseInt(data[4]);
            int subgroupNumber = Integer.parseInt(data[6]);
            Long telegramId = upd.getCallbackQuery().getFrom().getId();
            userService.saveOrUpdate(telegramId, courseNumber, groupNumber, subgroupNumber);
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(AbilityUtils.getChatId(upd));
            editMessageText.setMessageId(upd.getCallbackQuery().getMessage().getMessageId());
            editMessageText.setText(SUCCESSFUL_REGISTRATION);
            silent.execute(editMessageText);
        };
        return Reply.of(action, Flag.CALLBACK_QUERY, isPressInlineKeyboardAllSubgroup());
    }

    private Predicate<Update> isPressInlineKeyboardAllSubgroup() {
        return upd -> upd.hasCallbackQuery() && upd.getCallbackQuery().getData().startsWith("reg");
    }

    /**
     * Функция вызывается по команде /now<br>
     * Выводит текущуюю пару
     */
    public Ability now() {
        return Ability
                .builder()
                .name("now")
                .info(NOW_INFO)
                .locality(USER)
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
                        List<Subject> subjects = subjectService.findByCourseAndGroupAndSubgroupAndDateAndIsNumerator_NOW(user.getCourse(), user.getGroup(), user.getSubgroup(), localDateTime, DateUtil.isNumerator(localDateTime.toLocalDate()));
                        if(subjects.isEmpty()) {
                            body = NO_SUBJECT_NOW_MESSAGE;
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
     * Функция вызывается по команде /today<br>
     * Выводит расписание на сегодня
     */
    public Ability today() {
        return Ability
                .builder()
                .name("today")
                .info(TODAY_INFO)
                .locality(USER)
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
                        List<Subject> subjects = subjectService.findByCourseAndGroupAndSubgroupAndDateAndIsNumerator_Day(user.getCourse(), user.getGroup(), user.getSubgroup(), localDateTime, DateUtil.isNumerator(localDateTime.toLocalDate()));
                        String body = MessageUtils.subjectListOrNoSubjectMessage(subjects, NO_SUBJECTS_MESSAGE);
                        sendMessage.setText(header +  body);
                    }
                    silent.execute(sendMessage);
                })
                .build();
    }

    /**
     * Функция вызывается по команде /week<br>
     * Выводит расписание на текущую неделю
     */
    public Ability week() {
        return Ability
                .builder()
                .name("week")
                .info(WEEK_INFO)
                .locality(USER)
                .privacy(PUBLIC)
                .action(ctx -> {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(ctx.chatId());
                    sendMessage.setParseMode("HTML");
                    User user = userService.findUserByTelegramId(ctx.user().getId());
                    if(user == null) {
                        sendMessage.setText(UNREGISTER_MESSAGE);
                        silent.execute(sendMessage);
                    } else {
                        LocalDateTime localDateTime = LocalDateTime.now();
                        LocalDateTime currentDayWeek = localDateTime.with(DayOfWeek.MONDAY);
                        for(int i = 0; i < 6; ++i) {
                            List<Subject> subjects = subjectService.findByCourseAndGroupAndSubgroupAndDateAndIsNumerator_Day(
                                    user.getCourse(),
                                    user.getGroup(),
                                    user.getSubgroup(),
                                    currentDayWeek,
                                    DateUtil.isNumerator(currentDayWeek.toLocalDate())
                            );
                            String header = MessageUtils.header(user, currentDayWeek);
                            String body = MessageUtils.subjectListOrNoSubjectMessage(subjects, NO_SUBJECTS_MESSAGE);
                            sendMessage.setText(header + body);
                            silent.execute(sendMessage);
                            currentDayWeek = currentDayWeek.plusDays(1);
                        }
                    }
                })
                .build();
    }

    /**
     * Функция вызывается по команде /nextday<br>
     * Выводит расписание на следующий день
     */
    public Ability nextday() {
        return Ability
                .builder()
                .name("nextday")
                .info(NEXTDAY_INFO)
                .locality(USER)
                .privacy(PUBLIC)
                .action(ctx -> {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(ctx.chatId());
                    sendMessage.setParseMode("HTML");
                    User user = userService.findUserByTelegramId(ctx.user().getId());
                    if(user == null) {
                        sendMessage.setText(UNREGISTER_MESSAGE);
                    } else {
                        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
                        String header = MessageUtils.header(user, localDateTime);
                        List<Subject> subjects = subjectService.findByCourseAndGroupAndSubgroupAndDateAndIsNumerator_Day(
                                user.getCourse(),
                                user.getGroup(),
                                user.getSubgroup(),
                                localDateTime,
                                DateUtil.isNumerator(localDateTime.toLocalDate()));
                        String body = MessageUtils.subjectListOrNoSubjectMessage(subjects, NO_SUBJECTS_MESSAGE);
                        sendMessage.setText(header +  body);
                    }
                    silent.execute(sendMessage);
                })
                .build();
    }

    /**
     * Функция вызывается по команде /nextweek<br>
     * Выводит расписание на следующую неделю
     */
    public Ability nextweek() {
        return Ability
                .builder()
                .name("nextweek")
                .info(NEXTWEEK_INFO)
                .locality(USER)
                .privacy(PUBLIC)
                .action(ctx -> {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(ctx.chatId());
                    sendMessage.setParseMode("HTML");
                    User user = userService.findUserByTelegramId(ctx.user().getId());
                    if(user == null) {
                        sendMessage.setText(UNREGISTER_MESSAGE);
                        silent.execute(sendMessage);
                    } else {
                        LocalDateTime localDateTime = LocalDateTime.now();
                        LocalDateTime currentDayWeek = localDateTime.with(DayOfWeek.SUNDAY).plusDays(1);
                        for(int i = 0; i < 6; ++i) {
                            List<Subject> subjects = subjectService.findByCourseAndGroupAndSubgroupAndDateAndIsNumerator_Day(
                                    user.getCourse(),
                                    user.getGroup(),
                                    user.getSubgroup(),
                                    currentDayWeek,
                                    DateUtil.isNumerator(currentDayWeek.toLocalDate())
                            );
                            String header = MessageUtils.header(user, currentDayWeek);
                            String body = MessageUtils.subjectListOrNoSubjectMessage(subjects, NO_SUBJECTS_MESSAGE);
                            sendMessage.setText(header + body);
                            silent.execute(sendMessage);
                            currentDayWeek = currentDayWeek.plusDays(1);
                        }
                    }
                })
                .build();
    }
}