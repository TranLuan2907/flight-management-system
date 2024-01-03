/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.yesno;

import java.util.Scanner;

/**
 *
 * @author nklua
 */
public class ConfirmYesNo {

    public static final Scanner in = new Scanner(System.in);

    public static boolean confirmYesNo(String inputMessage, String errorMessage) {
        String input;
        do {
            System.out.print(inputMessage);
            input = in.nextLine().trim().toUpperCase();
            if (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N")) {
                System.err.println(errorMessage);
            }
        } while (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N"));
        return input.equalsIgnoreCase("Y");
    }
}
