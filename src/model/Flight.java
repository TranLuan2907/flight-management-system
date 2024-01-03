/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import utils.print.PrintFlight;
import utils.validation.ValidateDate;

/**
 *
 * @author nklua
 */
public class Flight implements Serializable {

    private String flightNumber;
    private String departureCity;
    private String destinationCity;
    private Date departureTime;
    private Date arrivalTime;
    private int totalSeat;
    private int availableSeat;
    private boolean assignedStatus;
    private char[][] seatMap;

    
    private ArrayList<String> pilots = new ArrayList<>();
    private ArrayList<String> flightAttendants = new ArrayList<>();
    private ArrayList<String> groundStaff = new ArrayList<>();
    private ArrayList<Flight> assignCrew = new ArrayList<>();

    public Flight(String flightNumber, String departureCity, String destinationCity, Date departureTime, Date arrivalTime, int totalSeat) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeat = totalSeat;
        this.availableSeat = totalSeat;
        this.pilots = new ArrayList<>();
        this.flightAttendants = new ArrayList<>();
        this.groundStaff = new ArrayList<>();
    }

    public void assignCrew(ArrayList<String> pilots, ArrayList<String> flightAttendants, ArrayList<String> groundStaff) {
        this.pilots = pilots;
        this.flightAttendants = flightAttendants;
        this.groundStaff = groundStaff;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public ArrayList<Flight> getAssignCrew() {
        return assignCrew;
    }

    public void setAssignCrew(ArrayList<Flight> assignCrew) {
        this.assignCrew = assignCrew;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    public int minusSeat() {
        return availableSeat = availableSeat - 1;
    }

    public boolean isAssignedStatus() {
        return assignedStatus;
    }

    public void setAssignedStatus(boolean assignedStatus) {
        this.assignedStatus = assignedStatus;
    }

    public ArrayList<String> getPilots() {
        return pilots;
    }

    public void setPilots(ArrayList<String> pilots) {
        this.pilots = pilots;
    }

    public ArrayList<String> getFlightAttendants() {
        return flightAttendants;
    }

    public void setFlightAttendants(ArrayList<String> flightAttendants) {
        this.flightAttendants = flightAttendants;
    }

    public ArrayList<String> getGroundStaff() {
        return groundStaff;
    }

    public void setGroundStaff(ArrayList<String> groundStaff) {
        this.groundStaff = groundStaff;
    }

    public char[][] getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(char[][] seatMap) {
        this.seatMap = seatMap;
    }

    @Override
    public String toString() {
        String departureTimeString = ValidateDate.convertDayToString(departureTime);
        String arrivalTimeString = ValidateDate.convertDayToString(arrivalTime);
        return String.format("| %-8s |  %-18s  |  %-20s  |   %-20s   |  %-14s   |  %-14s    |  %-18s     |", flightNumber, departureCity, destinationCity, departureTimeString, arrivalTimeString, totalSeat, availableSeat);
    }

    public void showProfile() {
        System.out.println(toString());
        PrintFlight.printFlightBar();
    }

}
