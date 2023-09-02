package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {

    private String offline;

    public void unavailable(String source, String target) {
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            String line;
            boolean serverOn = true;
            List<String> output = new ArrayList<>();
            StringBuilder result = new StringBuilder();
            while (br.ready()) {
                line = br.readLine();
                if (!line.trim().isEmpty()) {
                    if ((line.startsWith("400") || line.startsWith("500")) && serverOn) {
                        offline = line.split(" ")[1];
                        serverOn = false;
                    } else if ((line.startsWith("200") || line.startsWith("300")) && !serverOn) {
                        String online = line.split(" ")[1];
                        result.append(String.format("%s;%s;%s", offline,
                                online, System.lineSeparator()));
                        output.add(result.toString());
                        result = new StringBuilder();
                        serverOn = true;
                    }
                }
            }
            writeToTarget(output, target);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void writeToTarget(List<String> input, String target) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            for (String line : input) {
                writer.write(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}