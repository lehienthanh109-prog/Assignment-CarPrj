
import com.BrandList;
import com.CarList;
import com.Menu;

import java.util.ArrayList;

public class CarManager {

    public static void main(String[] args) {

        // 1. Create brand list
        BrandList brands = new BrandList();

        // 2. Load brands from file
        if (!brands.loadFromFile("brands.txt")) {
            System.out.println("Cannot load brands.txt!");
            return;
        }

        // 3. Create car list
        CarList cars = new CarList(brands);

        // 4. Load cars from file
        if (!cars.loadFromFile("cars.txt")) {
            System.out.println("Cannot load cars.txt!");
            return;
        }

        // 5. Create menu options
        ArrayList<String> options = new ArrayList<>();

        options.add("List all brands");
        options.add("Add a new brand");
        options.add("Search a brand by ID");
        options.add("Update a brand");
        options.add("Save brands to file");
        options.add("List all cars");
        options.add("Find cars by part of brand name");
        options.add("Add a new car");
        options.add("Remove a car by ID");
        options.add("Update a car");
        options.add("Save cars to file");

        Menu menu = new Menu();

        int choice;

        do {
            System.out.println(
                    "\n===== BMW SHOWROOM MANAGEMENT ====="
            );

            choice = menu.int_getChoice(options);

            if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            }

            switch (choice) {

                case 1:
                    brands.listBrands();
                    break;

                case 2:
                    brands.addBrand();
                    break;

                case 3:
                    System.out.print("Enter brand ID: ");

                    java.util.Scanner sc
                            = new java.util.Scanner(System.in);

                    String id = sc.nextLine().trim();

                    int pos = brands.searchID(id);

                    if (pos == -1) {
                        System.out.println("Brand not found!");
                    } else {
                        System.out.println(brands.get(pos));
                    }
                    break;

                case 4:
                    brands.updateBrand();
                    break;

                case 5:
                    if (brands.saveToFile("brands.txt")) {
                        System.out.println(
                                "Brands saved successfully!"
                        );
                    }
                    break;

                case 6:
                    cars.listCars();
                    break;

                case 7:
                    cars.printBasedBrandName();
                    break;

                case 8:
                    cars.addCar();
                    break;

                case 9:
                    cars.removeCar();
                    break;

                case 10:
                    cars.updateCar();
                    break;

                case 11:
                    if (cars.saveToFile("cars.txt")) {
                        System.out.println(
                                "Cars saved successfully!"
                        );
                    }
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please choose 0-11."
                    );
            }

        } while (true);

        // 6. Main program loop
        do {

            System.out.println("\n===== BMW SHOWROOM MANAGEMENT =====");

            menu.int_getChoice(options);
            // See note below: use the returned value
            // in the actual menu selection.

        } while (false);
    }
}
