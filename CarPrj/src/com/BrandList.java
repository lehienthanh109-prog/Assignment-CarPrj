
package com;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BrandList extends ArrayList<Brand> {

    private final Scanner sc = new Scanner(System.in);

    // 1. Constructor
    public BrandList() {
        super();
    }

    // 2. Read brands from file
    public boolean loadFromFile(String filename) {

        File f = new File(filename);

        if (!f.exists()) {
            System.out.println("File not found: " + filename);
            return false;
        }

        try (BufferedReader br =
                new BufferedReader(new FileReader(f))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length != 3) {
                    System.out.println("Invalid line: " + line);
                    continue;
                }

                String id = parts[0].trim();
                String name = parts[1].trim();

                String[] soundPrice = parts[2].split(":");

                if (soundPrice.length != 2) {
                    System.out.println("Invalid line: " + line);
                    continue;
                }

                String sound = soundPrice[0].trim();
                double price = Double.parseDouble(
                        soundPrice[1].trim());

                Brand b = new Brand(id, name, sound, price);

                this.add(b);
            }

            return true;

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // 3. Save brands to file
    public boolean saveToFile(String filename) {

        try (PrintWriter pw =
                new PrintWriter(new FileWriter(filename))) {

            for (Brand b : this) {
                pw.println(b.toString());
            }

            return true;

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // 4. Search brand by ID
    public int searchID(String id) {

        for (int i = 0; i < this.size(); i++) {

            if (this.get(i).getBrandID()
                    .equalsIgnoreCase(id)) {
                return i;
            }
        }

        return -1;
    }

    // 5. Choose a brand using Menu
    public Brand getUserChoice() {

        if (this.isEmpty()) {
            System.out.println("Brand list is empty!");
            return null;
        }

        Menu menu = new Menu();

        return menu.ref_getChoice(this);
    }

    // 6. Add a new brand
    public void addBrand() {

        String id;

        do {
            System.out.print("Enter brand ID: ");
            id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("Brand ID cannot be blank!");
            } else if (searchID(id) != -1) {
                System.out.println("Brand ID already exists!");
            } else {
                break;
            }

        } while (true);

        String name;

        do {
            System.out.print("Enter brand name: ");
            name = sc.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Brand name cannot be blank!");
            }

        } while (name.isEmpty());

        String sound;

        do {
            System.out.print("Enter sound brand: ");
            sound = sc.nextLine().trim();

            if (sound.isEmpty()) {
                System.out.println("Sound brand cannot be blank!");
            }

        } while (sound.isEmpty());

        double price;

        while (true) {
            System.out.print("Enter price: ");

            try {
                price = Double.parseDouble(
                        sc.nextLine().trim());

                if (price > 0) {
                    break;
                }

                System.out.println("Price must be positive!");

            } catch (NumberFormatException e) {
                System.out.println("Invalid price!");
            }
        }

        Brand b = new Brand(id, name, sound, price);

        this.add(b);

        System.out.println("Brand added successfully!");
    }

    // 7. Update an existing brand
    public void updateBrand() {

        System.out.print("Enter brand ID to update: ");
        String id = sc.nextLine().trim();

        int pos = searchID(id);

        if (pos == -1) {
            System.out.println("Brand not found!");
            return;
        }

        Brand b = this.get(pos);

        System.out.println("Current brand: " + b);

        String name;

        do {
            System.out.print("Enter new brand name: ");
            name = sc.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Brand name cannot be blank!");
            }

        } while (name.isEmpty());

        String sound;

        do {
            System.out.print("Enter new sound brand: ");
            sound = sc.nextLine().trim();

            if (sound.isEmpty()) {
                System.out.println("Sound brand cannot be blank!");
            }

        } while (sound.isEmpty());

        double price;

        while (true) {
            System.out.print("Enter new price: ");

            try {
                price = Double.parseDouble(
                        sc.nextLine().trim());

                if (price > 0) {
                    break;
                }

                System.out.println("Price must be positive!");

            } catch (NumberFormatException e) {
                System.out.println("Invalid price!");
            }
        }

        b.setBrandName(name);
        b.setSoundBrand(sound);
        b.setPrice(price);

        System.out.println("Brand updated successfully!");
    }

    // 8. List all brands
    public void listBrands() {

        if (this.isEmpty()) {
            System.out.println("Brand list is empty!");
            return;
        }

        System.out.println("=== BRAND LIST ===");

        for (Brand b : this) {
            System.out.println(b);
        }
    }
}
