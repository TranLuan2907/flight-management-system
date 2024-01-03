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
public class ValidateString {

    private static final Scanner in = new Scanner(System.in);

    public static String getString(String inputMessage, String errorMessage) {
        String n;
        while (true) {
            System.out.print(inputMessage);
            n = in.nextLine().trim().toUpperCase();
            if (n.isEmpty()) {
                System.err.println(errorMessage);
            } else {
                return n;
            }
        }
    }

   
}
