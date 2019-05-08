package com.sg.floormastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lukem
 */
public class FMUserIOImpl implements FMUserIO {

   Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);

    }
    
     @Override
    public void printBigDecimal(BigDecimal area) {
        System.out.print(area);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        double num = Double.parseDouble(sc.nextLine());
        return num;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double num = readDouble(prompt);
        while (min > num || max < num) {
            print("Error, number not in range");
            num = readDouble(prompt);
        }
        return num;
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        float num = Float.parseFloat(sc.nextLine());
        return num;

    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float num = readFloat(prompt);
        while (min > num || max < num) {
            print("Error, number not in range");
            num = readFloat(prompt);
        }
        return num;
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        int num = Integer.parseInt(sc.nextLine());
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int num = readInt(prompt);
        while (min > num || max < num) {
            print("Error, number not in range");
            num = readInt(prompt);
        }
        return num;
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        long num = Long.parseLong(sc.next());
        return num;

    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long num = readLong(prompt);
        while (min < num || max < num) {
            print("Error, number not in range");
            num = readLong(prompt);
        }
        return num;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        String num = sc.nextLine();
        return num;

    }
    
         @Override
    public BigDecimal readBigDecimal(String prompt) {
        print(prompt);
        BigDecimal num = new BigDecimal  (sc.nextLine());
        return num;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal num = readBigDecimal(prompt);
        while (min.compareTo(num) > 0 || max.compareTo(max) > 0) {
            print("Error, number not in range");
            num = readBigDecimal(prompt);
           
        }
        return num;
    }

   


  

    
    


}
