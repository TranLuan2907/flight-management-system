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
public class ValidateInt {

    private static final Scanner in = new Scanner(System.in);

    public static int getAnInteger(String inputMessage, String errorMessage, int lowerBound, int upperBound) {
        int n, temp;
        if (lowerBound > upperBound) {
            temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }

        while (true) {
            try {
                System.out.print(inputMessage);
                n = Integer.parseInt(in.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.err.println(errorMessage);
            }

        }
    }

    public static int getAnInteger(String inputMessage, String errorMessage) {
        int n;
        while (true) {
            try {
                System.out.print(inputMessage);
                n = Integer.parseInt(in.nextLine());
                if (n < 0) {
                   throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.err.println(errorMessage);
            }
        }
    }
}
