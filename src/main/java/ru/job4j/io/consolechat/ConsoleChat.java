package ru.job4j.io.consolechat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> phrases = new ArrayList<>();
    private List<String> chatLog = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        System.out.println("Введите текст: ");
        phrases.addAll(readPhrases());
        while (!scanner.hasNext(OUT)) {
            if (scanner.hasNext(STOP)) {
                while (!scanner.hasNext(CONTINUE)) {
                    System.out.println(" ");
                    chatLog.add("- ");
                    askQuestion();
                }
                System.out.println("Продолжаем");
                chatLog.add("-" + "Продолжаем");
            } else {
                String answer = giveAnswer();
                chatLog.add("-" + answer);
                System.out.println(answer);
                askQuestion();
            }
        }
        chatLog.add("-" + OUT);
        saveLog(chatLog);
    }

    private void askQuestion() {
        System.out.println(":");
        chatLog.add(scanner.nextLine());
    }

    private String giveAnswer() {
        int answer = new Random().nextInt(phrases.size());
        return phrases.get(answer);
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            String line;
            while ((line = read.readLine()) != null) {
                answers.add(Arrays.toString(line.split("\n")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            for (String line : log) {
                bufferedWriter.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("/Users/michael/job4j_design/src/main/java/ru/job4j/io/consolechat/ChatLog.txt",
                "/Users/michael/job4j_design/src/main/java/ru/job4j/io/consolechat/Phrases.txt");
        cc.run();
    }
}
