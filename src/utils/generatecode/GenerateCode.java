/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.generatecode;

import java.util.Random;

/**
 *
 * @author nklua
 */
public class GenerateCode {

    public static String generateRandomCode() {
        String prefix = "VN";
        Random random = new Random();
        int randomDigits = random.nextInt(90000) + 10000;
        String randomCode = prefix + randomDigits;
        return randomCode;
    }
}
