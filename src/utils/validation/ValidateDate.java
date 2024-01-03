/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author nklua
 */
public class ValidateDate {

    public static final Scanner in = new Scanner(System.in);

    public static Date parseDate(String inputMessage, String errorMessage, String dateFormat) {
        String input;
        while (true) {
            System.out.print(inputMessage);
            input = in.nextLine().trim();
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
                return formatter.parse(input);
            } catch (ParseException e) {
                System.err.println(errorMessage);
            }
        }
    }

    public static Date validateArrivalTime(String inputMessage, String errorMessage, String dateFormat, Date departureTime) {
        while (true) {
            Date arrivalTime = parseDate(inputMessage, errorMessage, dateFormat);
            boolean checkDate = (arrivalTime.getTime() - departureTime.getTime() > 0);
            if (!checkDate) {
                System.err.println("ARRIVAL TIME MUST BE GREATER THAN DEPARTURE TIME!");
            } else {
                return arrivalTime;
            }
        }
    }

    public static Date validateDepartureTime(String inputMessage, String errorMessage, String dateFormat) {
        while (true) {
            Date departureTime = parseDate(inputMessage, errorMessage, dateFormat);
            boolean checkDate = checkValidDate(new Date(), departureTime);
            if (!checkDate) {
                System.err.println("DEPARTURE TIME MUST BE GREATER THAN TODAY!");
            } else {
                return departureTime;
            }
        }
    }

    public static String convertDayToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return simpleDateFormat.format(date);
    }

    public static boolean checkValidDate(Date dateBefore, Date dateAfter) {
        return (dateAfter.getTime() - dateBefore.getTime() > 0);
    }
    
    public static boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date1).equals(sdf.format(date2));
    }
}
