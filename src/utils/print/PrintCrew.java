/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import model.Flight;
import static utils.print.PrintFlight.printOutputFlightBar;

/**
 *
 * @author nklua
 */
public class PrintCrew {

    public static void printCrewHeading(String heading) {
        printCrewBar();
        System.out.print(heading);
        for (int i = 1; i <= 47; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printCrewBar() {
        for (int i = 1; i <= 48; i++) {
            System.out.print("-");
        }
    }

    public static void printCrewHeading() {
        System.out.printf("|     %-18s       |    %-28s     |      %-26s      |", "PILOTS", "FLIGHT ATTENDANTS", "GROUND STAFFS");
    }

    public static void printCrewInfo(Flight flight) {
        ArrayList<String> pilots = flight.getPilots();
        ArrayList<String> flightAttendants = flight.getFlightAttendants();
        ArrayList<String> groundStaff = flight.getGroundStaff();

        int maxCrewCount = Math.max(pilots.size(), Math.max(flightAttendants.size(), groundStaff.size()));
        
        System.out.println();
        System.out.println("                                              HERE IS THE CREW ASSIGNMENT FOR THIS FLIGHT!                                        ");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|         PILOTS                           |        FLIGHT ATTENDANTS                 |           GROUND STAFF                   |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < maxCrewCount; i++) {
            String pilot = i < pilots.size() ? pilots.get(i) : "";
            String flightAttendant = i < flightAttendants.size() ? flightAttendants.get(i) : "";
            String staff = i < groundStaff.size() ? groundStaff.get(i) : "";
            System.out.printf("| %-40s | %-40s | %-40s |\n", pilot, flightAttendant, staff);
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        }
        System.out.println("CREW ASSIGNED SUCCESSFULLY!");
        System.out.println();
    }
}
