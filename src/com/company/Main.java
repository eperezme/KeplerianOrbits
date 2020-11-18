package com.company;

public class Main {

    public static void main(String[] args) {

        Orbits Test1 = new Orbits(20000, 3000, 0.5, 40000);
        System.out.println("Mu " + Test1.getMu());
        System.out.println("n " + Test1.getN());
        System.out.println("P " + Test1.getP());
        System.out.println("Period " + Test1.getPeriod());
        System.out.println("Final E " + Test1.getEccAnom(30));

        Temp Test2 = new Temp(0.5, 30, 15);
        System.out.println("E " + Test2.EccAnom());
        System.out.println("True " + Test2.TrueAnom());
        System.out.println("Position " + Test2.position());

        Testtt Test3 = new Testtt(2000, 3000, 0.5, 40000);
        System.out.println("E " + Test3.getEccAnom(30));




        //Celestial Earth = new Celestial("Earth", "Planet", 2000000, 50000);
        //Orbit Earth = new Orbit(3000, 0.3, 30, 55.65534333, 65.65432, 23456.65432);


        //System.out.println(Earth.getMass());
        //System.out.println(Earth.getName());


    }

    /*** ATRIBUTES ***/
    public static final double G = 6.67428E-11;
    public static final double PI = Math.PI;}

