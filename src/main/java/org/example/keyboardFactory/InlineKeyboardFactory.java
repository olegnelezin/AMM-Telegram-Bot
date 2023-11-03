package org.example.keyboardFactory;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardFactory {
    public static ReplyKeyboard allCourses() {
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
        for(int i = 1; i <= 2; ++i) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(i + " курс (Магистратура)");
            button.setCallbackData("courseMag_" + i);
            rowInline.add(button);
        }
        rowsInline.add(rowInline);
        inlineKeyboard.setKeyboard(rowsInline);
        return inlineKeyboard;
    }
}
