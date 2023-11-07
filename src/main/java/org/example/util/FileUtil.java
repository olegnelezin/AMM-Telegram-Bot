package org.example.util;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    private final String fileName;
    private FileWriter fileWriter;

    public FileUtil(String fileName) {
        this.fileName = fileName;
        this.fileWriter = setFile();
    }
    private FileWriter setFile() {
        try {
            File file = new File("logs/" + fileName);
            file.createNewFile();
            fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return fileWriter;
    }

    public void write(String line) {
        try {
            fileWriter.write(line + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
