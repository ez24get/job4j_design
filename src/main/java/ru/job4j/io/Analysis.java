package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String line;
            boolean serverOn = true;
            while (br.ready()) {
                line = br.readLine();
                if (serverOn == (line.startsWith("400") || line.startsWith("500"))) {
                    writer.append(line.substring(4))
                            .append(';')
                            .append(serverOn ? "" : System.lineSeparator());
                    serverOn = !serverOn;
                }
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