/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package package2;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
class WestminsterRentalSystemGUI extends JFrame {
    private WestminsterRentalManager rentalManager;
    private DefaultTableModel tableModel;
    private JTable vehicleTable;

    public WestminsterRentalSystemGUI() {
        rentalManager = new WestminsterRentalManager();
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Westminster Rental System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        // Create components
        JLabel titleLabel = new JLabel("Westminster Rental System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton printButton = new JButton("Print Vehicles");
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printVehicles();
            }
        });

        JButton sortButton = new JButton("Sort Alphabetically");
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortVehicles();
            }
        });

        JLabel plateLabel = new JLabel("Plate Number:");
        JTextField plateTextField = new JTextField(10);

        JLabel pickupLabel = new JLabel("Pick-up Date and Time (yyyy-MM-dd HH:mm):");
        JTextField pickupTextField = new JTextField(16);

        JLabel dropoffLabel = new JLabel("Drop-off Date and Time (yyyy-MM-dd HH:mm):");
        JTextField dropoffTextField = new JTextField(16);

        JButton checkAvailabilityButton = new JButton("Check Availability and Book");
        checkAvailabilityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String plate = plateTextField.getText();
                String pickupDateTime = pickupTextField.getText();
                String dropoffDateTime = dropoffTextField.getText();
                checkAvailabilityAndBook(plate, pickupDateTime, dropoffDateTime);
            }
        });

        // Create table
        String[] columnNames = {"Plate", "Make", "Type"};
        tableModel = new DefaultTableModel(columnNames, 0);
        vehicleTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(vehicleTable);

        // Add components to the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        contentPane.add(titleLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        contentPane.add(printButton, c);

        c.gridx = 1;
        c.gridy = 1;
        contentPane.add(sortButton, c);

        c.gridx = 0;
        c.gridy = 2;
        contentPane.add(plateLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        contentPane.add(plateTextField, c);

        c.gridx = 0;
        c.gridy = 3;
        contentPane.add(pickupLabel, c);

        c.gridx = 1;
        c.gridy = 3;
        contentPane.add(pickupTextField, c);

        c.gridx = 0;
        c.gridy = 4;
        contentPane.add(dropoffLabel, c);

        c.gridx = 1;
        c.gridy = 4;
        contentPane.add(dropoffTextField, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        contentPane.add(checkAvailabilityButton, c);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        contentPane.add(tableScrollPane, c);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void printVehicles() {
        tableModel.setRowCount(0);
        List<Vehicle> vehicles = rentalManager.getVehicles();
        for (Vehicle vehicle : vehicles) {
            String plate = vehicle.getPlate();
            String make = vehicle.getMake();
            String type = (vehicle instanceof Car) ? "Car" : "Motorbike";
            tableModel.addRow(new Object[]{plate, make, type});
        }
    }

    private void sortVehicles() {
        rentalManager.sortVehiclesByPlate();
        printVehicles();
    }

    private void checkAvailabilityAndBook(String plate, String pickupDateTime, String dropoffDateTime) {
        Vehicle vehicle = rentalManager.findVehicleByPlate(plate);
        if (vehicle == null) {
            JOptionPane.showMessageDialog(this, "Vehicle not found.");
            return;
        }

        RentalSlot rentalSlot = new RentalSlot(pickupDateTime, dropoffDateTime);
        boolean isAvailable = rentalManager.checkAvailability(vehicle, rentalSlot);
        if (isAvailable) {
            double totalCost = calculateTotalCost(vehicle, rentalSlot);
            DecimalFormat df = new DecimalFormat("#.##");
            String message = "Vehicle is available.\nTotal Cost: " + df.format(totalCost);
            JOptionPane.showMessageDialog(this, message);
            rentalManager.bookVehicle(vehicle, rentalSlot);
        } else {
            JOptionPane.showMessageDialog(this, "Vehicle is not available for the specified rental slot.");
        }
    }

    private double calculateTotalCost(Vehicle vehicle, RentalSlot rentalSlot) {
        double rentalHours = calculateRentalHours(rentalSlot);
        double baseCostPerHour = (vehicle instanceof Car) ? 20.0 : 10.0;
        double totalCost = rentalHours * baseCostPerHour;

        if (rentalHours > 72) {
            double discount = baseCostPerHour * 24 * 0.15;
            totalCost -= discount;
        }

        return totalCost;
    }

    private double calculateRentalHours(RentalSlot rentalSlot) {
        LocalDateTime pickupDateTime = LocalDateTime.parse(rentalSlot.getPickupDateTime());
        LocalDateTime dropoffDateTime = LocalDateTime.parse(rentalSlot.getDropOffDateTime());
        long hours = pickupDateTime.until(dropoffDateTime, java.time.temporal.ChronoUnit.HOURS);
        return hours;
    }

    private static class WestminsterRentalManager {

        public WestminsterRentalManager() {
        }

        private List<Vehicle> getVehicles() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void sortVehiclesByPlate() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Vehicle findVehicleByPlate(String plate) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private boolean checkAvailability(Vehicle vehicle, RentalSlot rentalSlot) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void bookVehicle(Vehicle vehicle, RentalSlot rentalSlot) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}