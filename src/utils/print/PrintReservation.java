/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.print;

import utils.validation.ValidateDate;
import model.Flight;
import model.Passenger;
import model.Reservation;

/**
 *
 * @author nklua
 */
public class PrintReservation {

    public static void printReservationHeading(String heading) {
        printBar();
        System.out.print(heading);
        for (int i = 1; i <= 15; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printBar() {
        for (int i = 1; i <= 40; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printSpace(String item) {
        for (int i = 1; i <= 66 - item.length(); i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }

    public static void printFooterBar() {
        for (int i = 1; i <= 40; i++) {
            System.out.print("-");

        }
        System.out.println();
    }

    public static void printPassenger(Passenger passenger) {
        String name = passenger.getName();
        String phone = passenger.getPhone();
        String email = passenger.getEmail();
        System.out.printf("| NAME: %-20s           |\n", name);
        System.out.printf("| EMAIL: %-20s          |\n", email);
        System.out.printf("| PHONE: %-20s          |\n", phone);
        PrintReservation.printBar();
    }

    public static void printFlight(Flight flight) {
        String number = flight.getFlightNumber();
        String deptCity = flight.getDepartureCity();
        String destCity = flight.getDestinationCity();
        String deptTime = ValidateDate.convertDayToString(flight.getDepartureTime());
        String arrivalTime = ValidateDate.convertDayToString(flight.getArrivalTime());
        System.out.printf("| NUMBER: %-20s         |\n", number);
        System.out.printf("|                                      |\n");
        System.out.printf("| FROM: %-20s           |\n", deptCity);
        System.out.printf("| %-20s                 |\n", deptTime);
        System.out.printf("|                                      |\n");
        System.out.printf("| TO: %-20s             |\n", destCity);
        System.out.printf("| %-20s                 |\n", arrivalTime);
        PrintReservation.printBar();
    }

    
    
    
    
    
    public static void printDashedLine(int length) {
        String dashes = new String(new char[length]).replace("\0", "-");
        System.out.print(dashes);
    }

    public static void printBoxed(String text, int width) {

        System.out.print("+");
        printDashedLine(width);
        System.out.println("+");

        int padding = (width - text.length()) / 2;
        System.out.print("|");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(text.toUpperCase());
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.println("|");

        System.out.print("+");
        printDashedLine(width);
        System.out.print("+");

    }
    
    
    // Width of each column 
    int textWidth = 5;
    int nameWidth = 8; 
    int codeWidth = 12;

//// Print header
//System.out.print("+");
//printDashedLine(textWidth);
//System.out.print("+");
//printDashedLine(nameWidth);
//System.out.print("+");
//printDashedLine(codeWidth);
//System.out.println("+");
//
//// Print field names
//printCell("TEXT", textWidth);
//printCell("NAME", nameWidth);
//printCell("Code", codeWidth);
//System.out.println();
//
//// Print separator  
//System.out.print("+");
//printDashedLine(textWidth);
//System.out.print("+");
//printDashedLine(nameWidth);
//System.out.print("+");
//printDashedLine(codeWidth);
//System.out.println("+");

//void printCell(String text, int width) {
//  // Calculate padding and print cell
//}

//public static void printCell(String text, int width) {
//
//  int padding = (width - text.length()) / 2;
//  
//  System.out.print("|");
//  for(int i=0; i<padding; i++) {
//    System.out.print(" ");
//  }
//  System.out.print(text);
//  for(int i=0; i<padding; i++) { 
//     System.out.print(" ");
//  }
//  System.out.print("|");
//
//}
}
