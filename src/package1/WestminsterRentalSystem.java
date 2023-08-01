
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package package1;

/**
 *
 * @author Admin
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

abstract class Vehicle {
    private String plate;
    private String make;

    public Vehicle(String plate, String make) {
        this.plate = plate;
        this.make = make;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public abstract void displayInfo();
}

class Car extends Vehicle {
    private String engineType;
    private int numOfDoors;

    public Car(String plate, String make, String engineType, int numOfDoors) {
        super(plate, make);
        this.engineType = engineType;
        this.numOfDoors = numOfDoors;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public int getNumOfDoors() {
        return numOfDoors;
    }

    public void setNumOfDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }

    @Override
    public void displayInfo() {
        System.out.println("Plate: " + getPlate());
        System.out.println("Make: " + getMake());
        System.out.println("Engine Type: " + engineType);
        System.out.println("Number of Doors: " + numOfDoors);
        System.out.println("Vehicle Type: Car");
    }
}

class Motorbike extends Vehicle {
    private int numOfSeats;
    private int engineSize;

    public Motorbike(String plate, String make, int numOfSeats, int engineSize) {
        super(plate, make);
        this.numOfSeats = numOfSeats;
        this.engineSize = engineSize;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    @Override
    public void displayInfo() {
        System.out.println("Plate: " + getPlate());
        System.out.println("Make: " + getMake());
        System.out.println("Number of Seats: " + numOfSeats);
        System.out.println("Engine Size: " + engineSize);
        System.out.println("Vehicle Type: Motorbike");
    }
}

class RentalSlot {
    private String pickupDateTime;
    private String dropOffDateTime;

    public RentalSlot(String pickupDateTime, String dropOffDateTime) {
        this.pickupDateTime = pickupDateTime;
        this.dropOffDateTime = dropOffDateTime;
    }

    public String getPickupDateTime() {
        return pickupDateTime;
    }

    public void setPickupDateTime(String pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    public String getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(String dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }
}

interface RentalManager {
    void addVehicle(Vehicle vehicle);

    void deleteVehicle(String plate);

    void printVehicleList();

    void saveToFile();

    void readFromFile();
}



public class WestminsterRentalSystem {
    public static void main(String[] args) {
        WestminsterRentalManager rentalManager = new WestminsterRentalManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add a vehicle");
            System.out.println("2. Delete a vehicle");
            System.out.println("3. Print vehicles");
            System.out.println("4. Save vehicles");
            System.out.println("5. Load vehicles");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addVehicle(rentalManager, scanner);
                    break;
                case 2:
                    deleteVehicle(rentalManager, scanner);
                    break;
                case 3:
                    printVehicles(rentalManager);
                    break;
                case 4:
                    saveVehicles(rentalManager);
                    break;
                case 5:
                    loadVehicles(rentalManager);
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addVehicle(WestminsterRentalManager rentalManager, Scanner scanner) {
        System.out.println("Enter vehicle type (1 - Car, 2 - Motorbike):");
        int vehicleType = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.println("Enter plate number:");
        String plate = scanner.nextLine();

        System.out.println("Enter make:");
        String make = scanner.nextLine();

        if (vehicleType == 1) {
            System.out.println("Enter engine type:");
            String engineType = scanner.nextLine();

            System.out.println("Enter number of doors:");
            int numOfDoors = scanner.nextInt();

            Car car = new Car(plate, make, engineType, numOfDoors);
            rentalManager.addVehicle(car);
        } else if (vehicleType == 2) {
            System.out.println("Enter number of seats:");
            int numOfSeats = scanner.nextInt();

            System.out.println("Enter engine size:");
            int engineSize = scanner.nextInt();

            Motorbike motorbike = new Motorbike(plate, make, numOfSeats, engineSize);
            rentalManager.addVehicle(motorbike);
        } else {
            System.out.println("Invalid vehicle type.");
        }
    }

    private static void deleteVehicle(WestminsterRentalManager rentalManager, Scanner scanner) {
        System.out.println("Enter plate number of the vehicle to delete:");
        String plate = scanner.next();
        rentalManager.deleteVehicle(plate);
    }

    private static void printVehicles(WestminsterRentalManager rentalManager) {
        rentalManager.printVehicleList();
    }

    private static void saveVehicles(WestminsterRentalManager rentalManager) {
        rentalManager.saveToFile();
        System.out.println("Vehicles saved to file.");
    }

    private static void loadVehicles(WestminsterRentalManager rentalManager) {
        rentalManager.readFromFile();
        System.out.println("Vehicles loaded from file.");
    }
}
