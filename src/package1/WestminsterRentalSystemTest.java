/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package package1;

/**
 *
 * @author Admin
 */
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class WestminsterRentalSystemTest {
    private WestminsterRentalManager rentalManager;
    private ByteArrayOutputStream outputStream;

//    @BeforeEach
    public void setUp() {
        rentalManager = new WestminsterRentalManager();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

//    @Test
    public void testAddVehicle_Car() {
        String input = "1\n1\nABC123\nToyota\nPetrol\n4\n3\n";
        provideInput(input);

//        WestminsterRentalSystem.addVehicle(rentalManager, new Scanner(System.in));

//        Assertions.assertTrue(outputStream.toString().contains("Vehicle added successfully."));
    }

//    @Test
    public void testAddVehicle_Motorbike() {
        String input = "1\n2\nXYZ789\nHonda\n2\n150\n";
        provideInput(input);

//        WestminsterRentalSystem.addVehicle(rentalManager, new Scanner(System.in));

//        Assertions.assertTrue(outputStream.toString().contains("Vehicle added successfully."));
    }

//    @Test
    public void testDeleteVehicle_VehicleExists() {
        String input = "2\nABC123\n";
        provideInput(input);

        rentalManager.addVehicle(new Car("ABC123", "Toyota", "Petrol", 4));

//        WestminsterRentalSystem.deleteVehicle(rentalManager, new Scanner(System.in));

//        Assertions.assertTrue(outputStream.toString().contains("Vehicle deleted successfully."));
    }

//    @Test
    public void testDeleteVehicle_VehicleNotFound() {
        String input = "2\nXYZ789\n";
        provideInput(input);

//        WestminsterRentalSystem.deleteVehicle(rentalManager, new Scanner(System.in));

//        Assertions.assertTrue(outputStream.toString().contains("Vehicle not found."));
    }

//    @Test
    public void testPrintVehicles() {
        rentalManager.addVehicle(new Car("ABC123", "Toyota", "Petrol", 4));
        rentalManager.addVehicle(new Motorbike("XYZ789", "Honda", 2, 150));

//        WestminsterRentalSystem.printVehicles(rentalManager);

//        Assertions.assertTrue(outputStream.toString().contains("Plate: ABC123"));
//        Assertions.assertTrue(outputStream.toString().contains("Plate: XYZ789"));
    }

    // Helper method to provide input to System.in
    private void provideInput(String data) {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        System.setIn(stdin);
    }
}
