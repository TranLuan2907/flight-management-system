/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import model.Flight;
import model.Passenger;
import model.Reservation;
import utils.yesno.ConfirmYesNo;
import utils.generatecode.GenerateCode;
import utils.print.PrintBoardingPasses;
import utils.print.PrintFlight;
import utils.print.PrintCrew;
import utils.print.PrintSeatMap;
import utils.validation.ValidateCode;
import utils.validation.ValidateDate;
import utils.validation.ValidateEmail;
import utils.validation.ValidateInt;
import utils.validation.ValidatePhone;
import utils.validation.ValidateString;

/**
 *
 * @author nklua
 */
public class FlightManagement {

    private static final String REGEX = "^F\\d{4}$";
    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";
    private static final String FOUND_DATE = "dd-MM-yyyy";
    private static final String EMAIL = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
    private static final String PHONE = "^[0-9]{9,10}$";
    private static final String SEAT = "^[0-9]{1,}[a-fA-F]{1}$";
    private static final String RESERVATION = "^VN\\d{5}$";
    private ArrayList<Flight> flightList = new ArrayList<>();
    private ArrayList<Flight> validFlight = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private ArrayList<Flight> assignCrew = new ArrayList<>();
    private Map<String, char[][]> updatedSeatMaps = new HashMap<>();
    private Map<String, char[][]> seatMaps = new HashMap<>();

    public void addAFlight() {
        boolean isContinue;
        String number, departureCity, destinationCity;
        Date departureTime, arrivalTime;
        int totalSeat;
        Flight x;
        do {
            System.out.println("ADD FLIGHT #" + (flightList.size() + 1));
            do {
                number = ValidateCode.getCode("INPUT A FLIGHT CODE IN FORMAT FXXXX (X STANDS FOR DIGIT): ", "A FLIGHT CODE MUST BE IN FORMAT FXXXX (X STANDS FOR DIGIT) AND CANNOT BE LEAVE EMPTY!", REGEX);
                x = searchFlightByCode(number);
                if (x != null) {
                    System.err.println("DUPLICATED CODE! PLEASE INPUT AGAIN!");
                }
            } while (x != null);
            departureCity = ValidateString.getString("INPUT A DEPARTURE CITY: ", "A DEPARTURE CITY CANNOT BE EMPTY!");
            destinationCity = ValidateString.getString("INPUT A DESTINATION CITY: ", "A DESTINATION CITY CANNOT BE EMPTY!");
            departureTime = ValidateDate.validateDepartureTime("INPUT DEPARTURE TIME IN FORMAT (dd-MM-yyyy HH:mm): ", "DEPARTURE TIME MUST BE IN FORMAT (dd-MM-yyyy HH:mm) AND CANNOT BE LEAVE EMPTY!", DATE_FORMAT);
            arrivalTime = ValidateDate.validateArrivalTime("INPUT ARRIVAL TIME IN FORMAT (dd-MM-yyyy HH:mm): ", "ARRIVAL TIME MUST BE IN FORMAT (dd-MM-yyyy HH:mm) AND CANNOT BE LEAVE EMPTY! ", DATE_FORMAT, departureTime);
            totalSeat = ValidateInt.getAnInteger("INPUT TOTAL SEAT: ", "TOTAL SEAT MUST BE A GREATER THAN 0 AND CANNOT BE LEAVE EMPTY!");
            flightList.add(new Flight(number, departureCity, destinationCity, departureTime, arrivalTime, totalSeat));
            System.out.println("FLIGHT #" + flightList.size() + " ADDED SUCCESSFULLY!");
            showFlightInfo();
            isContinue = ConfirmYesNo.confirmYesNo("DO YOU WANT TO ADD MORE FLIGHT (Y/N): ", "AN ANSWER MUST BE EITHER Y OR N!");
        } while (isContinue);
    }

    public Flight searchFlightByCode(String code) {
        if (flightList.isEmpty()) {
            return null;
        }

        for (Flight flight : flightList) {
            if (flight.getFlightNumber().equalsIgnoreCase(code)) {
                return flight;
            }
        }
        return null;
    }

    public void showFlightInfo() {
        if (flightList.isEmpty()) {
            System.out.println("NOTHING TO SHOW!");
        } else {
            Collections.sort(flightList, Comparator.comparing(Flight::getDepartureTime).reversed());
            PrintFlight.printOutputFlightHeading("THE FLIGHT LIST");
            PrintFlight.printFlightHeading();
            flightList.forEach(Flight::showProfile); // method reference
        }
    }

    public void bookAFlight() {
        String departureCity = ValidateString.getString("INPUT A DEPARTURE CITY: ", "A DEPARTURE CITY CANNOT BE LEAVE EMPTY!");
        String destinationCity = ValidateString.getString("INPUT A DESTINATION CITY: ", "A DESTINATION CITY CANNOT BE LEAVE EMPTY!");
        Date date = ValidateDate.parseDate("INPUT A DEPARTURE DATE IN FORMAT (dd-MM-yyyy): ", "DEPARTURE DATE MUST BE IN FORMAT dd-MM-yyyy AND CANNOT BE LEAVE EMPTY!", FOUND_DATE);
        validFlight.clear();
        flightList.forEach((flight) -> {
            boolean isValidDepartureCity = flight.getDepartureCity().equalsIgnoreCase(departureCity);
            boolean isValidDestinationCity = flight.getDestinationCity().equalsIgnoreCase(destinationCity);
            boolean isValidDate = ValidateDate.isSameDay(date, flight.getDepartureTime());
            if (isValidDepartureCity && isValidDestinationCity && isValidDate && flight.getAvailableSeat() > 0) {
                validFlight.add(flight);
            }
        });

        if (validFlight.isEmpty()) {
            System.err.println("NOT FOUND FLIGHTS THAT MATCH YOUR CRITERIA!");
            return;
        } else {
            PrintFlight.printOutputFlightHeading("AVAILABLE FLIGHTS");
            PrintFlight.printFlightHeading();
            validFlight.forEach(Flight::showProfile);
        }
        Reservation r;
        boolean validInput = false;
        while (!validInput) {
            String flightCode = ValidateCode.getCode("INPUT A FLIGHT CODE IN FORMAT FXXXX (X STANDS FOR DIGIT): ", "A FLIGHT CODE MUST BE IN FORMAT FXXXX (X STANDS FOR DIGITS) AND CANNOT BE LEFT EMPTY!", REGEX);
            boolean flightCodeFound = false;
            for (Flight flight : validFlight) {
                if (flight.getFlightNumber().equalsIgnoreCase(flightCode)) {
                    Passenger passenger = getUserInformation();
                    boolean confirm = ConfirmYesNo.confirmYesNo("DO YOU REALLY WANT TO CREATE A RESERVATION? (Y OR N): ", "AN ANSWER MUST BE EITHER Y OR N AND CANNOT BE LEFT EMPTY!");
                    if (confirm) {
                        String reservationID;
                        do {
                            reservationID = GenerateCode.generateRandomCode();
                            r = findById(reservationID);
                        } while (r != null);
                        r = new Reservation(reservationID, passenger, flight);
                        reservations.add(r);
                        r.showReservationInfo();
                        flight.minusSeat();
                    } else {
                        System.out.println("CANCELLED RESERVATION!");
                    }
                    flightCodeFound = true;
                    validInput = true;
                    break;
                }
            }
            if (!flightCodeFound) {
                System.err.println("THE FLIGHT CODE YOU ARE TRYING TO RESERVE IS NOT IN THE LIST! PLEASE TRY AGAIN!");
                return;
            }
        }

    }

    public void checkIn() {
        String reservationId = ValidateCode.getCode("INPUT A RESERVATION ID THAT YOU WANT TO CHECK IN IN FORMAT VNXXXXX (X STANDS FOR DIGIT): ", "A RESERVATION ID MUST BE IN A VALID FORMAT AND CANNOT BE LEFT EMPTY!", RESERVATION);
        Reservation reservation = findById(reservationId);

        if (reservation == null) {
            System.err.println("NOT FOUND THE RESERVATION ID THAT YOU WANT TO CHECK IN!");
            return;
        }

        if (reservation.isCheckIn()) {
            System.err.println("THIS RESERVATION HAS ALREADY BEEN CHECKED IN!");
            return;
        }

        Flight flight = reservation.getFlight();
        String flightCode = flight.getFlightNumber();
        Passenger passenger = reservation.getPassenger();

        int totalSeats = flight.getTotalSeat();
        int columns = 6;

        char[][] currentSeatMap = seatMaps.get(flightCode);
        if (currentSeatMap == null) {
            currentSeatMap = PrintSeatMap.createSeatMap(totalSeats, columns);
        }
        PrintSeatMap.printSeatMap(currentSeatMap);

        String seat;
        boolean validSeatChoice = false;

        while (!validSeatChoice) {
            seat = ValidateCode.getCode("INPUT A SEAT CODE THAT YOU WANT TO BOOK (EX: 10A): ", "A SEAT MUST BE IN VALID FORMAT AND CANNOT BE LEFT EMPTY!", SEAT);

            int row = Integer.parseInt(seat.replaceAll("[^0-9]", "")) - 1;
            char column = seat.replaceAll("[^A-Fa-f]", "").toUpperCase().charAt(0);

            int lastRowSeats = totalSeats % columns;

            boolean invalidColumn = column < 'A' || column >= (char) ('A' + columns);
            boolean invalidRow = row < 0 || row > (totalSeats - lastRowSeats) / columns;
            boolean invalidLastRowColumn = row == (totalSeats - lastRowSeats) / columns && (column < 'A' || column >= (char) ('A' + lastRowSeats));

            if (invalidRow || invalidLastRowColumn || invalidColumn) {
                System.err.println("INVALID SEAT CHOICE! PLEASE TRY AGAIN.");
            } else {
                char seatStatus = currentSeatMap[row][column - 'A'];
                switch (seatStatus) {
                    case 'X':
                        System.err.println("THIS SEAT IS ALREADY BOOKED. PLEASE CHOOSE ANOTHER SEAT!");
                        break;
                    case ' ':
                        System.err.println("THIS SEAT IS NOT AVAILABLE. PLEASE CHOOSE ANOTHER SEAT!");
                        break;
                    default:
                        currentSeatMap = PrintSeatMap.validateAndMarkSeatAsBooked(currentSeatMap, totalSeats, columns, seat);
                        if (currentSeatMap != null) {
                            seatMaps.put(flightCode, currentSeatMap);
                            reservation.getFlight().setSeatMap(currentSeatMap);
                            PrintSeatMap.storeUpdatedSeatMap(flightCode, currentSeatMap);
                            PrintBoardingPasses.printBoardingPasses(flight, passenger, reservation, seat);
                            validSeatChoice = true;
                            reservation.setCheckIn(true);
                        }
                        break;
                }
            }
        }
    }

    public Reservation findById(String id) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationID().equalsIgnoreCase(id)) {
                return reservation;
            }
        }
        return null;
    }

    public void assignCrew() {
        showFlightInfo();
        String code = ValidateCode.getCode("WHICH FLIGHT CODE DO YOU WANT TO ASSIGN CREW?: ", "A FLIGHT CODE MUST BE IN VALID FORMAT AND CANNOT BE LEAVE EMPTY!", REGEX);
        Flight flight = searchFlightByCode(code);

        if (flight == null) {
            System.err.println("NOT FOUND FLIGHT TO ASSIGN CREW!");
        } else if (flight.getAssignCrew().isEmpty()) {
            ArrayList<String> pilots = new ArrayList<>();
            ArrayList<String> flightAttendants = new ArrayList<>();
            ArrayList<String> groundStaff = new ArrayList();

            while (true) {
                String primaryPilot = ValidateString.getString("INPUT A PILOT: ", "A PRIMARY PILOT CANNOT BE LEFT EMPTY");
                pilots.add(primaryPilot);
                boolean continueAddingPilots = ConfirmYesNo.confirmYesNo("DO YOU WANT TO CONTINUE ADDING PILOT (Y OR N):  ", "AN ANSWER MUST BE EITHER Y OR N!");
                if (!continueAddingPilots) {
                    break;
                }
            }

            while (true) {
                String flightAttendant = ValidateString.getString("INPUT A FLIGHT ATTENDANT: ", "A FLIGHT ATTENDANT CANNOT BE LEFT EMPTY");
                flightAttendants.add(flightAttendant);
                boolean continueAddingAttendants = ConfirmYesNo.confirmYesNo("DO YOU WANT TO CONTINUE ADDING FLIGHT ATTENDANT? (Y OR N): ", "AN ANSWER MUST BE EITHER Y OR N!");
                if (!continueAddingAttendants) {
                    break;
                }
            }

            while (true) {
                String groundStaffMember = ValidateString.getString("INPUT A GROUND STAFF: ", "A GROUND STAFF MEMBER CANNOT BE LEFT EMPTY");
                groundStaff.add(groundStaffMember);
                boolean continueAddingGroundStaff = ConfirmYesNo.confirmYesNo("DO YOU WANT TO CONTINUE TO ADD GROUND STAFF? (Y OR N) ", "AN ANSWER MUST BE EITHER Y OR N!");
                if (!continueAddingGroundStaff) {
                    break;
                }
            }

            flight.getAssignCrew().add(flight);
            System.out.println("CREW ASSIGN SUCCESSFULLY!");
            flight.setPilots(pilots);
            flight.setFlightAttendants(flightAttendants);
            flight.setGroundStaff(groundStaff);

            PrintFlight.printOutputFlightHeading("THE FLIGHT LIST");
            PrintFlight.printFlightHeading();
            flight.showProfile();
            PrintCrew.printCrewInfo(flight);

        } else {
            System.err.println("THIS FLIGHT HAS ALREADY ASSIGNED CREW!");
        }
    }

    public void displayCrew() {
        showFlightInfo();
        String code = ValidateCode.getCode("INPUT A FLIGHT CODE THAT YOU WANT TO SEE CREW ASSIGNMENT: ", "A FLIGHT CODE MUST BE IN VALID FORMAT AND CANNOT BE LEAVE EMPTY!", REGEX);
        Flight flight = searchFlightByCode(code);
        if (flight == null) {
            System.err.println("NOT FOUND A FLIGHT CODE THAT YOU WANT TO SEE CREW ASSIGNMENT!");
            return;
        }

        if (flight.getAssignCrew().isEmpty()) {
            System.err.println("THIS FLIGHT HAS NOT ASSIGNED CREW YET! NOTHING TO PRINT OUT!");
            return;
        }

        PrintFlight.printOutputFlightHeading("THE FLIGHT LIST");
        PrintFlight.printFlightHeading();
        flight.showProfile();
        PrintCrew.printCrewInfo(flight);
    }

    public void saveFlightData() {
        try {
            try (FileOutputStream fos = new FileOutputStream("products.dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fos)) {

                oos.writeObject(flightList);
                oos.writeObject(reservations);
                oos.writeObject(validFlight);
                oos.writeObject(assignCrew);
                oos.writeObject(seatMaps);

            }
            System.out.println("FLIGHT DATA SAVED SUCCESSFULLY!");
        } catch (IOException e) {
            System.out.println("ERROR WHILE SAVING FLIGHT DATA: " + e.getMessage());
        }
    }

    public void loadFlightData() {
        try {
            try (FileInputStream fis = new FileInputStream("products.dat");
                    ObjectInputStream ois = new ObjectInputStream(fis)) {
                flightList = (ArrayList<Flight>) ois.readObject();
                reservations = (ArrayList<Reservation>) ois.readObject();
                validFlight = (ArrayList<Flight>) ois.readObject();
                assignCrew = (ArrayList<Flight>) ois.readObject();
                seatMaps = (Map<String, char[][]>) ois.readObject();
            }
            System.out.println("FLIGHT DATA LOADED SUCCESSFULLY!");
        } catch (Exception e) {
            System.out.println("ERROR WHILE LOADING FLIGHT DATA: " + e.getMessage());
        }
    }

    public Passenger getUserInformation() {
        System.out.println("INPUT USER INFORMATION!");
        String name = ValidateString.getString("INPUT A USER NAME: ", "A USER NAME CANNOT BE LEAVE EMPTY!");
        String phone = ValidatePhone.getPhone("INPUT A USER PHONE IN 9 OR 10 DIGITS: ", "A USER PHONE MUST BE VALID AND CANNOT BE LEAVE EMPTY!", PHONE);
        String email = ValidateEmail.getEmail("INPUT A USER EMAIL IN FORMAT (...@gmail.com): ", "A USER EMAIL MUST BE IN VALID FORMAT (...@gmail.com) AND CANNOT BE LEAVE EMPTY!", EMAIL);
        Passenger p = new Passenger(name, phone, email);
        return p;
    }

}
