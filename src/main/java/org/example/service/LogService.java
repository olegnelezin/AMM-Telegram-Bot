package org.example.service;

import org.example.util.FileUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogService {
    private final FileUtil fileUtil;
    public LogService() {
        fileUtil = new FileUtil("logs.txt");
    }
    public void log(String log) {
        fileUtil.write(log);
    }

    public static String parse(String id, String username, String firstname, String lastname, String action) {
       LocalDateTime localDateTime = LocalDateTime.now();
        return String.format("time: %s id: %s username: %s firstname: %s lastname: %s action: %s",
                localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                id,
                username,
                firstname,
                lastname,
                action);
    }
}
