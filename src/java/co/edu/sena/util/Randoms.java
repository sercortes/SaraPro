/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open 14 34 35 12 27 16 32 17 14 17 0 the 14  16 0 17 template 34 35 26 in the editor.
 */
package co.edu.sena.util;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author equipo
 */
public class Randoms {
    
    public static void main(String[] args) {
        Random rand = new Random();
        int maximo = 36;
        boolean verdader = true;
        int i = 0;
        do{
        i++;
        int random = rand.nextInt(maximo);
        System.out.println("YOUR NUMBER IS "+random);
        System.out.println("");
        }while(i<60);
        
    }
    
}
