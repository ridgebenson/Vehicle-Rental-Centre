/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package package2;

/**
 *
 * @author Admin
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.SwingUtilities;

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




public class WestminsterRentalSystem{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WestminsterRentalSystemGUI();
            }
        });
    }
}
