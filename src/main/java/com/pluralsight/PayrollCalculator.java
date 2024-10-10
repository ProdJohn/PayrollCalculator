package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class PayrollCalculator {
    public static void main(String[] args) {
        Scanner Keyboard = new Scanner(System.in);

        System.out.print("Enter the name of the file employee file to process: ");
        String employeeFile = Keyboard.nextLine();
        System.out.print("Enter the name of the payroll file to create: ");
        String payrollFile = Keyboard.nextLine();
        try (
        BufferedReader br = new BufferedReader(new FileReader(employeeFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(payrollFile))) {
        String line = br.readLine();
        if (line != null && line.toLowerCase().contains("id")) {
        bw.write("id|name|gross pay");
        bw.newLine(); }
        while ((line = br.readLine()) != null) {
        String[] employeeData = line.split("\\|");
        if (employeeData.length == 4) {
            try {
             int employeeID = Integer.parseInt(employeeData[0].trim());
             String name = employeeData[1].trim();
             double hoursWorked = Double.parseDouble(employeeData[2].trim());
             double payRate = Double.parseDouble(employeeData[3].trim());
             Employee employee = new Employee(employeeID, name, hoursWorked, payRate);
               bw.write(String.format("%d|%s|%.2f",
               employee.getEmployeeId(),
               employee.getName(),
               employee.getGrossPay()));
               bw.newLine();

         } catch (NumberFormatException e) {
           System.out.println("Invalid ID or number format: " + line);
                    }
        } else {
          System.out.println("Invalid line format: " + line);
                }
            }
          System.out.println("Payroll report written to: " + payrollFile);
        } catch (FileNotFoundException e) {
          System.out.println("File not found: " + employeeFile);
          e.printStackTrace();
        } catch (IOException e) {
          System.out.println("Error in reading or writing the file.");
          e.printStackTrace();
        } finally {
          Keyboard.close();
        }
    }
}
