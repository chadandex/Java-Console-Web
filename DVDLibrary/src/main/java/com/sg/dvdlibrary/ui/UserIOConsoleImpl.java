/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author Chad
 */
public class UserIOConsoleImpl implements UserIO {
    private Scanner uInput = new Scanner(System.in);
    private String uInputString;
    

    public void print(String message) {
        System.out.println(message);
    }
    public double readDouble(String prompt){
        System.out.println(prompt);
        return uInput.nextDouble();
    }
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
    public float readFloat(String prompt){
        System.out.println(prompt);
        return uInput.nextFloat();
    }
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
    public int readInt(String prompt){
        System.out.println(prompt);
        return uInput.nextInt();
    }
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
    public long readLong(String prompt){
        long end = 0;
        System.out.println(prompt);
        end = uInput.nextLong();
        return end;
    }
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
    public String readString(String prompt){
        System.out.println(prompt);
        return uInput.nextLine();
    }
    private void clearBuffer() {
        uInput.nextLine();
    }
}
