package com.company;

public class Main {

    public static void main(String[] args) {

        Orbits Test1 = new Orbits(20000, 3000, 0.4, 40000);
        System.out.println("Mu " + Test1.getMu());
        System.out.println("n " + Test1.getN());
        System.out.println("P " + Test1.getP());
        System.out.println("Period " + Test1.getPeriod());
        System.out.println("Final E " + Test1.getE());





        //Celestial Earth = new Celestial("Earth", "Planet", 2000000, 50000);
        //Orbit Earth = new Orbit(3000, 0.3, 30, 55.65534333, 65.65432, 23456.65432);


        //System.out.println(Earth.getMass());
        //System.out.println(Earth.getName());


    }
}
