package me.dhillon.bot;

import java.util.Scanner;

public class Main {


 // Bot Token: REDACTED
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter bot token: ");
        String token = scanner.nextLine();
        try {
            new Bot(token);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
