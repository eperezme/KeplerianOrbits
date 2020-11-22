package com.company;

import org.threadly.util.Clock;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    /*** ATRIBUTES ***/
    public static final double G = 6.67428E-11;
    public static final double PI = Math.PI;
    private static final int numberOfOrbits = 1;

    public static Clock initClock() {
        return new Clock();
    }


    /*** FUNCTIONS**/

    public static <numberOfOrbits> Orbits[] initOrbits(Random random) {
        Orbits[] orbitsList = new Orbits[numberOfOrbits];

        for (int i = 0; i < orbitsList.length; i++) {
            //orbitsList[i] = new Orbits(0.3, 100, random.nextDouble() * 360, random.nextDouble() * 360, random.nextDouble() * 360, i, 60);
            orbitsList[i] = new Orbits(0.3, 4000, 0, 90, 0, i, 60);

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


        /*
        for (Orbits orbits : orbitsList){
            double B = orbits.getMeanAngularMotion();
            orbits.setPeriod(random.nextInt(60));
            System.out.println("//  OMEGA  " + orbits.getLongitudeAscendingNode() + " // " + "w  " + orbits.getArgumentPeriapsis() + " // " + "i " + orbits.getInclination() + " // " + orbits.getId());
            System.out.println("Before " + B + " // After " + orbits.getMeanAngularMotion());
        }
        */
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %n", "X", "Y", "Z", "Time", "OrbitNum");

        for (int k = 0; k <= 300; k++) {
            for (Orbits orbits : orbitsList) {
                orbits.getRVector();
                System.out.printf("%-20s %-20s %-20s %-20s %n", orbits.getX(), orbits.getY(), orbits.getZ(), orbits.getActualTime(), orbits.getId());
            }
            TimeUnit.SECONDS.sleep(1);

        }


    }
}



