/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import utils.print.PrintBoardingPasses;
import utils.print.PrintReservation;

/**
 *
 * @author nklua
 */
public class Reservation implements Serializable {

    private String reservationID;
    private Passenger passenger;
    private Flight flight;
    private boolean checkIn;
    
    public Reservation(String reservationID, Passenger passenger, Flight flight) {
        this.reservationID = reservationID;
        this.passenger = passenger;
        this.flight = flight;
        this.checkIn = false;
    }


    public Reservation(Passenger passenger, Flight flight, String reservationID) {
        this.passenger = passenger;
        this.reservationID = reservationID;
    }

   

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    public void showReservationInfo() {
        System.out.println("HERE IS YOUR RESERVATION TICKET!");
        PrintReservation.printBar();
        System.out.println("|              RESERVATION             |");
        PrintReservation.printBar();
        System.out.printf("| RESERVATION ID: %-20s |\n", this.reservationID);
        PrintReservation.printPassenger(this.passenger);
        PrintReservation.printFlight(this.flight);
    }

    public void showBoardingPasses(String input) {
        PrintBoardingPasses.printBoardingPasses(flight, passenger, this, input);
    }

}
