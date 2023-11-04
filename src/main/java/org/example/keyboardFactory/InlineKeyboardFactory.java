package org.example.keyboardFactory;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.model.Course;
import org.example.model.Group;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardFactory {
    public static InlineKeyboardMarkup allCourses() {
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        // Добавление 1-5 курс
        for(int i = 1; i <= 5; ++i) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(i + " курс");
            button.setCallbackData("course_" + i);
            rowInline.add(button);
            if(i % 2 == 0) {
                rowsInline.add(rowInline);
                rowInline = new ArrayList<>();
            }
        }
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        for(int i = 6; i <= 7; ++i) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(i-5 + " курс (Магистратура)");
            button.setCallbackData("course_" + i);
            rowInline.add(button);
        }
        rowsInline.add(rowInline);
        inlineKeyboard.setKeyboard(rowsInline);
        return inlineKeyboard;
    }

    public static InlineKeyboardMarkup allGroupsForCourse(Course course) {
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        int i = 0;
        List<Group> groups = course.getGroups();
        for (Group group: groups) {
            ++i;
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(Integer.toString(group.getNumber()));
            button.setCallbackData("group_" + group.getNumber() + "_course_" + course.getNumber());
            rowInline.add(button);
            if(i == 3) {
                rowsInline.add(rowInline);
                rowInline = new ArrayList<>();
            }
        }
        rowsInline.add(rowInline);
        inlineKeyboard.setKeyboard(rowsInline);
        return inlineKeyboard;
    }

    public static InlineKeyboardMarkup empty() {
        return new InlineKeyboardMarkup();
    }
}
