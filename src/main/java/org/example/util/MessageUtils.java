package org.example.util;

import org.example.model.Subject;
import org.example.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageUtils {

    /**
     * Выводит основную информацию о юзере, сегодняшнюю дату, день недели, Числитель/Знаменатель
     */
    public static String header(User user, LocalDateTime localDateTime) {
        return String.format("<i>%d курс %d   [%s]</i>\n<b><u>%s</u></b>(%s):\n\n",
                user.getCourse().getNumber(),
                user.getGroup().getNumber(),
                localDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yy")),
                DateUtil.translateWeekday(localDateTime.getDayOfWeek().name()),
                DateUtil.isNumerator(localDateTime.toLocalDate()) ? "Числитель" : "Знаменатель");
    }

    /**
     * Выводит информацию о предмете в форматированном виде
     */
    public static String subject(Subject subject) {
        return String.format("> <b><i>%s-%s:</i></b>\n   %s\n   %s\n\n",
                subject.getBeginTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                subject.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                subject.getTitle(),
                subject.getLecturer());
    }
}
