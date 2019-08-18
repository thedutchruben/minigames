package dev.thedutchruben.minigames.master;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Master {

    public static void main(String[] strings){

        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            String command;

            while ((command = input.readLine()) != null) {
                if (command.equalsIgnoreCase("help")) {
                    System.out.println("----- Help Page -----");
                    System.out.println("help - this page");
                    System.out.println("exit - shutdown the application");
                    System.out.println("---------------------");
                }
                if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("stop") || command.equalsIgnoreCase("end")) {
                    System.exit(0);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
