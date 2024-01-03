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
public class ValidateCode {

    private static final Scanner in = new Scanner(System.in);

    public static String getCode(String inputMessage, String errorMessage, String regex) {
        String input;
        while (true) {
            System.out.print(inputMessage);
            input = in.nextLine().trim().toUpperCase();
            if (input.matches(regex)) {
                return input;
            } else {
                System.err.println(errorMessage);
            }
        }
    }
}
