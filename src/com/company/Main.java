package com.company;

import org.threadly.util.Clock;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    /*** ATRIBUTES ***/
    public static final double G = 6.67428E-11;
    public static final double PI = Math.PI;
    private static int numberOfOrbits = 1;

    public static Clock initClock() {
        return new Clock();
    }


    /*** FUNCTIONS**/

    public static <numberOfOrbits> Orbits[] initOrbits(Random random) {
        Orbits[] orbitsList = new Orbits[numberOfOrbits];

        for (int i = 0; i < orbitsList.length; i++) {
            //orbitsList[i] = new Orbits(random.nextDouble() * 100000, random.nextDouble() * 80000, random.nextDouble(), 4000, random.nextDouble() * 360, random.nextDouble() * 360, random.nextDouble() * 360, i);
            orbitsList[i] = new Orbits(200000, 20000, 0.83285, 4000, 227.89, 53.38, 87.87, i, 60);

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
            double B = orbits.getMeanAngularMotion();
            orbits.setPeriod(random.nextInt(60));
            System.out.println("//  OMEGA  " + orbits.getLongitudeAscendingNode() + " // " + "w  " + orbits.getArgumentPeriapsis() + " // " + "i " + orbits.getInclination() + " // " + orbits.getId());
            System.out.println("Before " + B + " // After " + orbits.getMeanAngularMotion());
        }

        System.out.printf("%-20s %-20s %-20s %n", "X", "Y", "Z", "Time");
        while (true) {
            for (Orbits orbits : orbitsList) {
                orbits.getRVector();
                System.out.printf("%-20s %-20s %-20s %-20s %n", orbits.getX(), orbits.getY(), orbits.getZ(), orbits.getActualTime());
            }
            TimeUnit.SECONDS.sleep(1);
        }


    }
}



