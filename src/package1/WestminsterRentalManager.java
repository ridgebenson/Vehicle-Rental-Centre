/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package package1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Admin
 */
class WestminsterRentalManager implements RentalManager {
    private List<Vehicle> vehicles;

    public WestminsterRentalManager() {
        this.vehicles = new ArrayList<>();
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        if (vehicles.size() >= 15) {
            System.out.println("Maximum vehicle limit reached.");
            return;
        }
        vehicles.add(vehicle);
        System.out.println("Vehicle added successfully.");
    }

    @Override
    public void deleteVehicle(String plate) {
        Vehicle vehicle = findVehicleByPlate(plate);
        if (vehicle != null) {
            vehicles.remove(vehicle);
            System.out.println("Vehicle deleted successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    @Override
    public void printVehicleList() {
        Collections.sort(vehicles, Comparator.comparing(Vehicle::getPlate));
        for (Vehicle vehicle : vehicles) {
            vehicle.displayInfo();
        }
    }

    @Override
    public void saveToFile() {
        // Implementation to save vehicles to a file
    }

    @Override
    public void readFromFile() {
        // Implementation to read vehicles from a file
    }

    private Vehicle findVehicleByPlate(String plate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlate().equalsIgnoreCase(plate)) {
                return vehicle;
            }
        }
        return null;
    }
}