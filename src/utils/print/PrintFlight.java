/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.print;

/**
 *
 * @author nklua
 */
public class PrintFlight {

    public static void printFlightBar() {
        for (int i = 1; i <= 156; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printFlightHeading() {
        System.out.printf("| %-8s |  %-18s  |  %-20s  |   %-20s   |  %-14s     |  %-14s    |  %-18s     |", "NUMBER", "DEPARTURE CITY", "DESTINATION CITY", "DEPARTURE TIME", "ARRIVAL TIME", "TOTAL SEAT", "AVAILABLE SEAT");
        System.out.println();
        printFlightBar();
    }

    public static void printOutputFlightHeading(String heading) {
        printOutputFlightBar();
        System.out.print(heading);
        for (int i = 1; i <= 81; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printOutputFlightBar() {
        for (int i = 1; i <= 60; i++) {
            System.out.print("-");
        }
    }

}
