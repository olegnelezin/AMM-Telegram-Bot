package org.example.util;

import org.example.model.Subject;
import org.example.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MessageUtils {

    /**
     * Выводит основную информацию о юзере, сегодняшнюю дату, день недели, Числитель/Знаменатель
     */
    public static String header(User user, LocalDateTime localDateTime) {
        String course;
        if(user.getCourse().getNumber() < 6) {
            course = user.getCourse().getNumber() + " курс";
        } else {
            course = (user.getCourse().getNumber() - 5) + " курс(Магистратура)";
        }
        return String.format("<i>%s %d группа  [%s]</i>\n<b><u>%s</u></b>(%s):\n\n",
                course,
                user.getGroup().getNumber(),
                localDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yy")),
                DateUtil.translateWeekday(localDateTime.getDayOfWeek().name()),
                DateUtil.isNumerator(localDateTime.toLocalDate()) ? "Числитель" : "Знаменатель");
    }

    /**
     * Выводит информацию о предмете в форматированном виде
     */
    public static String subject(Subject subject) {
        return String.format("> <b><i>%s-%s:</i></b>\n   %s\n   <i>%s</i>\n   [%s]\n\n",
                subject.getBeginTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                subject.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                subject.getTitle(),
                subject.getLecturer(),
                subject.getInfo());
    }

    public static String subjectList(List<Subject> subjects) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Subject subject: subjects) {
            stringBuilder.append(MessageUtils.subject(subject));
        }
        return stringBuilder.toString();
    }

    public static String subjectListOrNoSubjectMessage(List<Subject> subjects, String message) {
        if(subjects.isEmpty()) {
            return message;
        } else {
            return MessageUtils.subjectList(subjects);
        }
    }
}
