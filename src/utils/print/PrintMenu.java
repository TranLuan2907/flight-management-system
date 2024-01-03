/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.print;

/**
 *
 * @author nklua
 */
public class PrintMenu {

    public static void printHeading(String heading) {
        printBar();
        System.out.print(heading);
        for (int i = 1; i <= 18; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printBar() {
        for (int i = 1; i <= 19; i++) {
            System.out.print("-");
        }
    }

    public static void printSpace(String item) {
        for (int i = 1; i <= 66 - item.length(); i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }

    public static void printFooterBar() {
        for (int i = 1; i <= 73; i++) {
            System.out.print("-");

        }
        System.out.println();
    }
}
