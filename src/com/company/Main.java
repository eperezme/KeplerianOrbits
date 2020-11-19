package com.company;
import java.util.Random;

public class Main {

    /*** ATRIBUTES ***/
    public static final double G = 6.67428E-11;
    public static final double PI = Math.PI;

    public static void main(String[] args) {

        //Instance for random number generator
        Random random = new Random();

        //Array of Orbit instances /// I could change this with Arraylist instead of normal Array
        Orbits[] orbitsList = new Orbits[10];

        for (int i = 0; i < orbitsList.length; i++) {
            orbitsList[i] = new Orbits(3000, 500, random.nextDouble(), 4000, random.nextDouble() * 360, random.nextDouble() * 360, random.nextDouble() * 360);
        }

        for (int j = 0; j < orbitsList.length; j++) {
            orbitsList[j].getRVector();
        }
/*
        *//*** Print ***//*
        //Strings for text print
        String num = "Orbit(n)";
        String ecc = "e"; String smAxis = "a";
        String EccAnom = "E"; String meanAnom = "MeanAnom"; String phi = "TrueAnom";
        String iter = "Iterations";
        String posX = "X"; String posY = "Y";
        String divider = "------------------------------------------------";


        System.out.printf("%-10s %-20s %-10s %-20s %-20s %-20s %-20s %-20s %-20s %n",
                num, ecc, smAxis, EccAnom, iter, meanAnom, phi, posX, posY);

        System.out.println(divider);

        for (int j = 0; j < orbitsList.length; j++) {
            System.out.printf("%-10s %-20s %-10s %-20s %-20s %-20s %-20s %-20s %-20s %n",
                    j, orbitsList[j].getecc(),
                    orbitsList[j].getSmAxis(),
                    orbitsList[j].getEccAnom(random.nextInt(360)),
                    orbitsList[j].getIter(),
                    orbitsList[j].getMeanAnom(),
                    orbitsList[j].getTrueAnom(),
                    orbitsList[j].getxStored(),
                    orbitsList[j].getyStored());
        }

        System.out.println(divider);*/
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


}



