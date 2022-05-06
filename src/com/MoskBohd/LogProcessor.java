package com.MoskBohd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

public class LogProcessor {
    private String date;

    public LogProcessor() {
    }

    public LogProcessor(String date) {
        this.date = date;
    }

    public void findLogsByDateCount(String dateStr) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");

        LocalDateTime start = LocalDateTime.now();
        Files.lines(Paths.get("logs.txt"))
                .filter(line->line.contains(dateStr))
                .filter(line -> line.contains("ERROR"))
                .forEach(line->stringBuilder.append(line + System.lineSeparator()));

        Path path1 = Paths.get("ERROR " + dateStr + ".log");
        Files.write(path1, Collections.singleton(stringBuilder));
        System.out.println(ChronoUnit.MILLIS.between(start, LocalDateTime.now()));
        //countThreading.add(ChronoUnit.MILLIS.between(start, LocalDateTime.now()));
    }
    private List<Long> countThreading;
    public long inTotalTimeThreading(){
        long count = 0;
        for (int i = 0; i < countThreading.size(); i++) {
            count += countThreading.get(i);
        }
        return count;
    }
}
