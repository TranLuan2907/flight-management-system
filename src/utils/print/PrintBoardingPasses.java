/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.print;

import utils.validation.ValidateDate;
import java.util.Date;
import model.Flight;
import model.Passenger;
import model.Reservation;

/**
 *
 * @author nklua
 */
public class PrintBoardingPasses {

    public static void printBoardingPassesHeading(String heading) {
        printBar();
        System.out.print(heading);
        for (int i = 1; i <= 27; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printBar() {
        for (int i = 1; i <= 27; i++) {
            System.out.print("-");
        }
    }

    public static void printSpace(String item) {
        for (int i = 1; i <= 66 - item.length(); i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }

    public static void printFooterBar() {
        for (int i = 1; i <= 70; i++) {
            System.out.print("-");

        }
        System.out.println();
    }

    public static void printBoardingPasses(Flight flight, Passenger passenger, Reservation res, String seat) {
        String deptTime = ValidateDate.convertDayToString(flight.getDepartureTime());
        String arrivalTime = ValidateDate.convertDayToString(flight.getArrivalTime());
        System.out.println("HERE IS YOUR BOARDING PASSES!");
        PrintBoardingPasses.printBoardingPassesHeading("BOARDING PASSES!");
        System.out.println("|           ECONOMY CLASS           |       VIETNAM AIRLINES         |");
        PrintBoardingPasses.printFooterBar();
        System.out.println("|   RESERVATION ID:                 |                                |");
        System.out.printf("|   %-20s            |                                |\n", res.getReservationID());
        System.out.println("|                                   |                                |");
        System.out.printf("|   HÃNG CHUYÊN CHỞ/CARRIER         |  GIỜ KHỞI HÀNH/DEPARTURE TIME: |                               \n");
        System.out.printf("|   VIETNAM AIRLINES                |  %-20s          |\n", deptTime);
        System.out.println("|                                   |                                |");
        System.out.printf("|   HỌ VÀ TÊN/NAME                  |  GIỜ ĐẾN NƠI/ARRIVAL TIME:     |                              \n");
        System.out.printf("|   %-20s            |  %-20s          |\n", passenger.getName(), arrivalTime);
        System.out.println("|                                   |                                |");
        System.out.println("|   EMAIL                           |  SỐ ĐIỆN THOẠI/PHONE           |");
        System.out.printf("|   %-20s            |  %-20s          |\n", passenger.getEmail(), passenger.getPhone());
        System.out.println("|                                   |                                |");
        System.out.printf("|   NƠI ĐI/FROM                     |  CHUYẾN BAY/FLIGHT             |\n");
        System.out.printf("|   %-20s            |  %-20s          |\n", flight.getDepartureCity(), flight.getFlightNumber());
        System.out.println("|                                   |                                |");
        System.out.println("|   NƠI ĐẾN/TO                      |  GHẾ/SEAT                      |");
        System.out.printf("|   %-20s            |  %-20s          |\n", flight.getDestinationCity(), seat);
        PrintBoardingPasses.printFooterBar();
    }
}
