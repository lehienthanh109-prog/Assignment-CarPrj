
package com;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CarList extends ArrayList<Car> {

    private final BrandList brands;
    private final Scanner sc = new Scanner(System.in);

    // 1. Constructor
    public CarList(BrandList brands) {
        super();
        this.brands = brands;
    }

    // 2. Load cars from file
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

                if (parts.length != 5) {
                    System.out.println("Invalid line: " + line);
                    continue;
                }

                String id = parts[0].trim();
                String brandID = parts[1].trim();
                String color = parts[2].trim();
                String frame = parts[3].trim();
                String engine = parts[4].trim();

                int brandPos = brands.searchID(brandID);

                if (brandPos == -1) {
                    System.out.println(
                            "Brand not found: " + brandID);
                    continue;
                }

                if (id.isEmpty() || color.isEmpty()
                        || searchID(id) != -1
                        || !frame.matches("F\\d{5}")
                        || !engine.matches("E\\d{5}")
                        || searchFrame(frame) != -1
                        || searchEngine(engine) != -1) {

                    System.out.println("Invalid car: " + line);
                    continue;
                }

                Brand brand = brands.get(brandPos);

                this.add(new Car(
                        id, brand, color, frame, engine));
            }

            return true;

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // 3. Save cars to file
    public boolean saveToFile(String filename) {

        try (PrintWriter pw =
                new PrintWriter(new FileWriter(filename))) {

            for (Car c : this) {
                pw.println(c.toString());
            }

            return true;

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // 4. Search by car ID
    public int searchID(String id) {

        for (int i = 0; i < this.size(); i++) {

            if (this.get(i).getCarID()
                    .equalsIgnoreCase(id)) {
                return i;
            }
        }

        return -1;
    }

    // 5. Search by frame ID
    public int searchFrame(String frameID) {

        for (int i = 0; i < this.size(); i++) {

            if (this.get(i).getFrameID()
                    .equalsIgnoreCase(frameID)) {
                return i;
            }
        }

        return -1;
    }

    // 6. Search by engine ID
    public int searchEngine(String engineID) {

        for (int i = 0; i < this.size(); i++) {

            if (this.get(i).getEngineID()
                    .equalsIgnoreCase(engineID)) {
                return i;
            }
        }

        return -1;
    }

    // 7. Add a new car
    public void addCar() {

        String id;

        while (true) {
            System.out.print("Enter car ID: ");
            id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("Car ID cannot be blank!");
            } else if (searchID(id) != -1) {
                System.out.println("Car ID already exists!");
            } else {
                break;
            }
        }

        System.out.println("Choose a brand:");
        Brand brand = brands.getUserChoice();

        if (brand == null) {
            System.out.println("Cannot add car without brand!");
            return;
        }

        String color;

        do {
            System.out.print("Enter color: ");
            color = sc.nextLine().trim();

            if (color.isEmpty()) {
                System.out.println("Color cannot be blank!");
            }

        } while (color.isEmpty());

        String frame;

        while (true) {
            System.out.print("Enter frame ID (F00000): ");
            frame = sc.nextLine().trim();

            if (!frame.matches("F\\d{5}")) {
                System.out.println("Invalid frame format!");
            } else if (searchFrame(frame) != -1) {
                System.out.println("Frame ID already exists!");
            } else {
                break;
            }
        }

        String engine;

        while (true) {
            System.out.print("Enter engine ID (E00000): ");
            engine = sc.nextLine().trim();

            if (!engine.matches("E\\d{5}")) {
                System.out.println("Invalid engine format!");
            } else if (searchEngine(engine) != -1) {
                System.out.println("Engine ID already exists!");
            } else {
                break;
            }
        }

        this.add(new Car(
                id, brand, color, frame, engine));

        System.out.println("Car added successfully!");
    }

    // 8. Remove car by ID
    public void removeCar() {

        System.out.print("Enter car ID to remove: ");
        String id = sc.nextLine().trim();

        int pos = searchID(id);

        if (pos == -1) {
            System.out.println("Car not found!");
            return;
        }

        System.out.println("Found car:");
        System.out.println(this.get(pos).screenString());

        System.out.print("Confirm remove (Y/N): ");
        String confirm = sc.nextLine().trim();

        if (confirm.equalsIgnoreCase("Y")) {
            this.remove(pos);
            System.out.println("Car removed successfully!");
        } else {
            System.out.println("Remove cancelled!");
        }
    }

    // 9. Update car by ID
    public void updateCar() {

        System.out.print("Enter car ID to update: ");
        String id = sc.nextLine().trim();

        int pos = searchID(id);

        if (pos == -1) {
            System.out.println("Car not found!");
            return;
        }

        Car car = this.get(pos);

        System.out.println("Current car:");
        System.out.println(car.screenString());

        System.out.println("Choose a new brand:");
        Brand brand = brands.getUserChoice();

        if (brand == null) {
            System.out.println("Update cancelled!");
            return;
        }

        String color;

        do {
            System.out.print("Enter new color: ");
            color = sc.nextLine().trim();

            if (color.isEmpty()) {
                System.out.println("Color cannot be blank!");
            }

        } while (color.isEmpty());

        String frame;

        while (true) {
            System.out.print("Enter new frame ID: ");
            frame = sc.nextLine().trim();

            int framePos = searchFrame(frame);

            if (!frame.matches("F\\d{5}")) {
                System.out.println("Invalid frame format!");
            } else if (framePos != -1 && framePos != pos) {
                System.out.println("Frame ID already exists!");
            } else {
                break;
            }
        }

        String engine;

        while (true) {
            System.out.print("Enter new engine ID: ");
            engine = sc.nextLine().trim();

            int enginePos = searchEngine(engine);

            if (!engine.matches("E\\d{5}")) {
                System.out.println("Invalid engine format!");
            } else if (enginePos != -1 && enginePos != pos) {
                System.out.println("Engine ID already exists!");
            } else {
                break;
            }
        }

        car.setBrand(brand);
        car.setColor(color);
        car.setFrameID(frame);
        car.setEngineID(engine);

        System.out.println("Car updated successfully!");
    }

    // 10. List cars sorted by brand name
    public void listCars() {

        if (this.isEmpty()) {
            System.out.println("Car list is empty!");
            return;
        }

        ArrayList<Car> sorted = new ArrayList<>(this);

        Collections.sort(sorted);

        System.out.println("=== CAR LIST ===");

        for (Car c : sorted) {
            System.out.println(c.screenString());
            System.out.println("--------------------");
        }
    }

    // 11. Find cars by part of brand name
    public void printBasedBrandName() {

        System.out.print("Enter part of brand name: ");
        String name = sc.nextLine().trim();

        boolean found = false;

        for (Car c : this) {

            if (c.getBrand().getBrandName()
                    .toLowerCase()
                    .contains(name.toLowerCase())) {

                System.out.println(c.screenString());
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching cars found!");
        }
    }
}
