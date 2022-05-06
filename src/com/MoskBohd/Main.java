package com.MoskBohd;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static class MyThread extends Thread{
        private String date;

        public MyThread(String date) {
            this.date = date;
        }
        @Override
        public void run(){
            LogProcessor logProcessor = new LogProcessor(date);
            try {
                logProcessor.findLogsByDateCount(date);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        LogProcessor logProcessor = new LogProcessor();

        LocalDateTime start = LocalDateTime.now();
        System.out.println("------consistent way-------");
        logProcessor.findLogsByDateCount("2019-10-13");
        logProcessor.findLogsByDateCount("2019-10-14");
        logProcessor.findLogsByDateCount("2019-10-15");
        logProcessor.findLogsByDateCount("2019-10-16");
        logProcessor.findLogsByDateCount("2019-10-17");
        System.out.println("in total = " + ChronoUnit.MILLIS.between(start, LocalDateTime.now()));

        System.out.println("------multithreading-------");

        LocalDateTime startThreading = LocalDateTime.now();
        List<MyThread> myThreadArrayList = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            MyThread myThread = new MyThread("2019-10-1"+i);
            myThread.start();
            myThreadArrayList.add(myThread);
        }
        // and now wait for all of them to complete
        for (Thread thread : myThreadArrayList) {
            thread.join();
        }
        LocalDateTime finishThreading = LocalDateTime.now();
        System.out.println("in total = " + ChronoUnit.MILLIS.between(startThreading, finishThreading));


    }
}
