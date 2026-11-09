package com;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);

    // Hien thi menu va nhan lua chon dang so nguyen
    public <E> int int_getChoice(ArrayList<E> options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println(i + 1 + "." + options.get(i));
        }
        System.out.println("Please choose an option: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input! Enter the interger: ");
            sc.nextLine();
            System.out.println("Please choose an option: ");
        }
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }

    // Tra ve doi tuong duoc nguoi dung chon
    public <E> E ref_getChoice(ArrayList<E> options) {

        if (options.isEmpty()) {
            System.out.println("No options available!");
            return null;
        }

        int choice;

        do {
            choice = int_getChoice(options);

            if (choice < 1 || choice > options.size()) {
                System.out.println("Invalid choice! Try again.");
            }

        } while (choice < 1 || choice > options.size());

        return options.get(choice - 1);
    }
}
