/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.FlightManagement;
import utils.yesno.ConfirmYesNo;

/**
 *
 * @author nklua
 */
public class FlightManagementSystem {

    public static void main(String[] args) {
        boolean change = false;
        boolean confirm;
        int userChoice;
        Menu mainMenu = new Menu();
        mainMenu(mainMenu);
        FlightManagement flightManagement = new FlightManagement();
        flightManagement.loadFlightData();

        do {
            userChoice = mainMenu.getUserChoice();
            switch (userChoice) {
                case 1:
                    flightManagement.addAFlight();
                    change = true;
                    break;
                case 2:
                    flightManagement.bookAFlight();
                    change = true;
                    break;
                case 3:
                    flightManagement.checkIn();
                    change = true;
                    break;
                case 4:
                    flightManagement.assignCrew();
                    change = true;
                    break;
                case 5:
                    flightManagement.showFlightInfo();
                    change = true;
                    break;
                case 6:
                    flightManagement.saveFlightData();
                    change = true;
                    break;
                case 7:
                    flightManagement.displayCrew();
                    change = true;
                    break;
                case 8:
                    if (change) {
                        System.out.println("CHANGES HAVE BEEN MADE TO THE DATA. DO YOU WANT TO SAVE AND EXIT?");
                        confirm = ConfirmYesNo.confirmYesNo("TYPE 'Y' TO SAVE AND EXIT, OR 'N' TO EXIT WITHOUT SAVING: ", "YOUR CHOICE MUST BE 'Y' OR 'N' AND CANNOT BE LEAVE EMPTY!");
                        if (confirm) {
                            flightManagement.saveFlightData();
                            System.out.println("YOUR CHANGES HAVE BEEN SAVED! SEE YOU NEXT TIME!");
                            break;
                        } else {
                            System.out.println("SEE YOU NEXT TIME!");
                        }
                    }
            }
        } while (userChoice != 8);
    }

    public static void mainMenu(Menu mainMenu) {
        mainMenu.addMenuItem("WELCOME TO FLIGHT MANAGEMENT SYSTEM!");
        mainMenu.addMenuItem("FLIGHT SCHEDULE MANAGEMENT");
        mainMenu.addMenuItem("PASSENGER RESERVATION AND BOOKING");
        mainMenu.addMenuItem("PASSENGER CHECK-IN AND ALLOCATION");
        mainMenu.addMenuItem("CREW MANAGEMENT AND ASSIGNMENTS");
        mainMenu.addMenuItem("SHOW ALL FLIGHTS");
        mainMenu.addMenuItem("DATA STORAGE FOR FLIGHT DETAILS, RESERVATIONS AND ASSIGNMENTS");
        mainMenu.addMenuItem("SHOW CREW ASSIGNMENT BY FLIGHT CODE");
        mainMenu.addMenuItem("QUIT");
    }
}
