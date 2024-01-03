package utils.print;

import java.util.HashMap;
import java.util.Map;
import model.Flight;
import model.Reservation;
import utils.validation.ValidateCode;

public class PrintSeatMap {

    private static final Map<String, char[][]> seatMaps = new HashMap<>();

    public static void markSeatAsBooked(char[][] seatMap, int row, int column) {
        if (row >= 0 && row < seatMap.length && column >= 0 && column < seatMap[0].length) {
            seatMap[row][column] = 'X';
        }
    }

    public static char[][] validateAndMarkSeatAsBooked(char[][] seatMap, int totalSeats, int columns, String seat) {
        int row = Integer.parseInt(seat.replaceAll("[^0-9]", "")) - 1;
        char column = seat.replaceAll("[^A-Fa-f]", "").toUpperCase().charAt(0);
        seatMap[row][column - 'A'] = 'X';
        return seatMap;
    }

    public static char[][] createSeatMap(int totalSeats, int columns) {
        int fullRows = totalSeats / columns;
        int lastRowSeats = totalSeats % columns;
        int rows = fullRows + (lastRowSeats > 0 ? 1 : 0);

        char[][] seatMap = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i < fullRows || (i == fullRows && j < lastRowSeats)) {
                    seatMap[i][j] = 'O';
                } else {
                    seatMap[i][j] = ' ';
                }
            }
        }

        return seatMap;
    }

    public static void printSeatMap(char[][] seatMap) {
        int rows = seatMap.length;
        int columns = seatMap[0].length;

        System.out.print("  ");
        for (char column = 'A'; column < 'D'; column++) {
            System.out.print(column + "  ");
        }
        System.out.print("  ");
        for (char column = 'D'; column < 'G'; column++) {
            System.out.print(column + "  ");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < columns; j++) {
                if (j >= 3) {
                    System.out.print("  " + seatMap[i][j]);
                } else {
                    System.out.print(seatMap[i][j] + "  ");
                }
            }
            System.out.println();
        }
        System.out.println("SEAT MAP EXPLANATION:");
        System.out.println("O : AVAILABLE SEAT");
        System.out.println("X : BOOKED SEAT");
    }

    public static void storeUpdatedSeatMap(String flightCode, char[][] seatMap) {
        seatMaps.put(flightCode, seatMap);
    }

    public static void main(String[] args) {
        printSeatMap(createSeatMap(150, 6));
    }
}
