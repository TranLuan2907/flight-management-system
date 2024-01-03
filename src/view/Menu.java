/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import utils.print.PrintMenu;
import utils.validation.ValidateInt;

/**
 *
 * @author nklua
 */
public class Menu extends ArrayList<String> {

    public void addMenuItem(String title) {
        this.add(title);
    }

    public void showMenu() {
        PrintMenu.printHeading(get(0));
        for (int i = 1; i < this.size(); i++) {
            System.out.print("| " + i + " | " + get(i));
            PrintMenu.printSpace(get(i));
        }
        PrintMenu.printFooterBar();
    }
    
    public int getUserChoice() {
        showMenu();
        return ValidateInt.getAnInteger("CHOOSE YOUR OPTIONS [1..." + (this.size() - 1) + "]: ", "AN OPTION MUST BE FROM [1..." + (this.size() - 1) + "] AND CANNOT BE LEAVE EMPTY!", 1, (this.size() - 1));
    }
}
