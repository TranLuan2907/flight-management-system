/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.validation;

import java.util.Scanner;

/**
 *
 * @author nklua
 */
public class ValidateDouble {

    private static final Scanner in = new Scanner(System.in);

    public static double getADouble(String inputMessage, String errorMessage, double upperBound, double lowerBound) {
        double n, temp;
        if (lowerBound > upperBound) {
            temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }

        while (true) {
            try {
                System.out.print(inputMessage);
                n = Double.parseDouble(in.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.err.println(errorMessage);
            }
        }
    }

    public static double getADouble(String inputMessage, String errorMessage) {
        double n;
        while (true) {
            try {
                System.out.print(inputMessage);
                n = Double.parseDouble(in.nextLine());
                return n;
            } catch (NumberFormatException e) {
                System.err.println(errorMessage);
            }
        }
    }
}
