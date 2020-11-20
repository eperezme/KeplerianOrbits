package com.company;

import org.threadly.util.Clock;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    /*** ATRIBUTES ***/
    public static final double G = 6.67428E-11;
    public static final double PI = Math.PI;
    private static int numberOfOrbits = 3;

    public static Clock initClock() {
        return new Clock();
    }


    /*** FUNCTIONS**/

    public static <numberOfOrbits> Orbits[] initOrbits(Random random) {
        Orbits[] orbitsList = new Orbits[numberOfOrbits];

        for (int i = 0; i < orbitsList.length; i++) {
            orbitsList[i] = new Orbits(random.nextDouble() * 100000, random.nextDouble() * 80000, random.nextDouble(), 4000, random.nextDouble() * 360, random.nextDouble() * 360, random.nextDouble() * 360);
        }


        return orbitsList;
    }


    public static void main(String[] args) throws InterruptedException {

        //Instance for clock
        Clock clock = initClock();

        //Instance for random number generator

        Random random = new Random();

        //Array of Orbit instances /// I could change this with Arraylist instead of normal Array
        Orbits[] orbitsList = initOrbits(random);



        for (Orbits orbits : orbitsList){
            System.out.println("MAM B " + orbits.getMeanAngularMotion());
            orbits.setPeriod(10);
            System.out.println("MAM A " + orbits.getMeanAngularMotion());
        }

        while (true) {
            for (Orbits orbits : orbitsList) {
                orbits.update();
                orbits.getRVector();
            }
            TimeUnit.SECONDS.sleep(1);
        }


    }
}



