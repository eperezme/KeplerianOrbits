package com.company;

public class Main {

    public static void main(String[] args) {

        Orbits Test1 = new Orbits(20000, 3000, 0.5, 40000);
        Orbits Test2 = new Orbits(3000, 500, 0.7, 4000);
        System.out.println("Mu " + Test1.getMu());
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
        System.out.println("Pos " + Test2.getPosition());








        //Celestial Earth = new Celestial("Earth", "Planet", 2000000, 50000);
        //Orbit Earth = new Orbit(3000, 0.3, 30, 55.65534333, 65.65432, 23456.65432);


        //System.out.println(Earth.getMass());
        //System.out.println(Earth.getName());


    }

    /*** ATRIBUTES ***/
    public static final double G = 6.67428E-11;
    public static final double PI = Math.PI;}

