/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author Chad
 */
public class UserIOImpl implements UserIO {
    
    Scanner uInput = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }
    
    @Override
    public double readDouble(String prompt){
        System.out.println(prompt);
        return uInput.nextDouble();
    }
    
    @Override
    public double readDouble(String prompt, double min, double max){
        double input;
        System.out.print(prompt);
        
        do {
            while (!uInput.hasNextDouble()) {
                System.out.print("Enter a number between " + min + " and " + max + ": ");
                uInput.next();
            }
            
            input = uInput.nextDouble();
            
            if (input > max || input < min) {
                System.out.print("Enter a number between " + min + " and " + max + ": ");
            }

        } while (input > max || input < min);
        
        clearBuffer();
        return input;
    }
    
    @Override
    public float readFloat(String prompt){
        System.out.println(prompt);
        return uInput.nextFloat();
    }
    
    @Override
    public float readFloat(String prompt, float min, float max){
        float input;
        System.out.print(prompt);
        
        do {
            while (!uInput.hasNextFloat()) {
                System.out.print("Enter a number between " + min + " and " + max + ": ");
                uInput.next();
            }
            
            input = uInput.nextFloat();
            
            if (input > max || input < min) {
                System.out.print("Enter a number between " + min + " and " + max + ": ");
            }

        } while (input > max || input < min);
        
        clearBuffer();
        return input;
    }
    
    @Override
    public int readInt(String prompt){
        System.out.println(prompt);
        return uInput.nextInt();
    }
    
    @Override
    public int readInt(String prompt, int min, int max){
        int input;
        System.out.print(prompt);
        
        do {
            while (!uInput.hasNextInt()) {
                System.out.print("Enter a number between " + min + " and " + max + ": ");
                uInput.next();
            }
            
            input = uInput.nextInt();
            
            if (input > max || input < min) {
                System.out.print("Enter a number between " + min + " and " + max + ": ");
            }

        } while (input > max || input < min);
        
        clearBuffer();
        return input;
    }
    
    @Override
    public long readLong(String prompt){
        long end = 0;
        System.out.println(prompt);
        end = uInput.nextLong();
        return end;
    }
    
    @Override
    public long readLong(String prompt, long min, long max){
        long input;
        System.out.print(prompt);
        
        do {
            while (!uInput.hasNextLong()) {
                System.out.print("Enter a number between " + min + " and " + max + ": ");
                uInput.next();
            }
            
            input = uInput.nextLong();
            
            if (input > max || input < min) {
                System.out.print("Enter a number between " + min + " and " + max + ": ");
            }

        } while (input > max || input < min);
        
        clearBuffer();
        return input;
    }
    
    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String userInput;
        userInput = uInput.nextLine();
        return userInput;
    }

    @Override
    public int pickItem(String prompt, int min, int max) {
        System.out.println(prompt);
        int itemNumber;
        do {
            String userInput;

            userInput = uInput.nextLine();
            itemNumber = Integer.parseInt(userInput);
            return itemNumber;
        } while (itemNumber < min || itemNumber > max);
    }

    @Override
    public BigDecimal bigDecimal(String prompt) {
        System.out.println(prompt);
        BigDecimal bigDec;
        bigDec = uInput.nextBigDecimal();
        uInput.nextLine();
        return bigDec;
    }
    
    @Override
    public int orderNum(String prompt) {
        System.out.println(prompt);
        String user;
        user = uInput.nextLine();
        int userInput = parseInt(user);
        return userInput;
    }

    private void clearBuffer() {
        uInput.nextLine();
    }
}
