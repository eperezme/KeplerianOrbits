package com.company;

import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {



        Random random = new Random();

        Orbits[] Test = new Orbits[10];

        for (int i = 0; i < Test.length; i++) {
            Test[i] = new Orbits(3000, 500, random.nextDouble(), 4000);
        }

        String num = "Test(n)";
        String ecc = "e";
        String smAxis = "a";
        String EccAnom = "E";
        String phi = "TrueAnom";
        String iter = "Iterations";
        String posX = "X";
        String posY = "Y";
        String divider = "------------------------------------------------";


        System.out.printf("%-10s %-20s %-10s %-20s %-20s %-20s %-20s %-20s %n", num, ecc, smAxis, EccAnom, iter, phi, posX, posY);
        System.out.println(divider);

        int j;
        for (j = 0; j < 10; j++){
        System.out.printf("%-10s %-20s %-10s %-20s %-20s %-20s %-20s %-20s %n",
                j, Test[j].getecc(),
                Test[j].getSmAxis(),
                Test[j].getEccAnom(random.nextInt(360)),
                Test[j].getIter(),
                Test[j].getTrueAnom(),
                Test[j].getX_stored(),
                Test[j].getY_stored());
        }
        System.out.println(divider);
        /*
            Orbits Test1 = new Orbits(3000, 500, 0.5, 4000);
            Orbits Test2 = new Orbits(3000, 500, 0.7, 4000);
            Orbits Test3 = new Orbits(3000, 500, 0.9, 4000);
            Orbits Test4 = new Orbits(3000, 500, 1, 4000);
            Orbits Test5 = new Orbits(3000, 500, 0.1, 4000);
            Orbits Test6 = new Orbits(3000, 500, 0.3, 4000);
            Orbits Test7 = new Orbits(3000, 500, 0.2, 4000);
            Orbits Test8 = new Orbits(3000, 500, 0.4, 4000);
            Orbits Test9 = new Orbits(3000, 500, 0.98, 4000);
            Orbits Test10 = new Orbits(3000, 500, 0.67, 4000);

         */









      /*  System.out.println("Mu " + Test1.getMu());
        System.out.println("Mu " + Test2.getMu());
        System.out.println("n " + Test1.getN());
        System.out.println("n " + Test2.getN());
        System.out.println("P " + Test2.getP());
        System.out.println("P " + Test1.getP());
        System.out.println("Period " + Test1.getPeriod());
        System.out.println("Period " + Test2.getPeriod());
        System.out.println("E " + Test1.getEccAnom(70));
        System.out.println("E " + Test2.getEccAnom(70));
        System.out.println("True " + Test1.getTrueAnom());
        System.out.println("True " + Test2.getTrueAnom());
        System.out.println("Pos " + Test1.getPosition());
        System.out.println("Pos " + Test2.getPosition()); */


            //Celestial Earth = new Celestial("Earth", "Planet", 2000000, 50000);
            //Orbit Earth = new Orbit(3000, 0.3, 30, 55.65534333, 65.65432, 23456.65432);


            //System.out.println(Earth.getMass());
            //System.out.println(Earth.getName());


        }
    /*** ATRIBUTES ***/
    public static final double G = 6.67428E-11;
    public static final double PI = Math.PI;


}



