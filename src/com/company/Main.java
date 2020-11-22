package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Main
{

	/*** ATRIBUTES ***/
	public static final double G = 6.67428E-11;
	public static final double PI = Math.PI;
	private static final int numberOfOrbits = 1;
	private static final int TIME = 1000;

    /*
    public static Clock initClock() {
        return new Clock();
    }
     */


	/*** FUNCTIONS**/

	public static Orbits[] initOrbits(Random random)
	{
		Orbits[] orbitsList = new Orbits[numberOfOrbits];

		for (Orbits orbit : orbitsList)
		{
			//orbitsList[i] = new Orbits(0.3, 100, random.nextDouble() * 360, random.nextDouble() * 360, random.nextDouble() * 360, i, 60);
			//orbitsList[i] = new Orbits(0.3, 4000, 0, 90, 0, i, 60);
            orbit = new Orbits(random.nextDouble(), random.nextDouble() * 10000, 0, 90, 0,60);

		}


		return orbitsList;
	}

	public static ArrayList<Integer> createTime()
	{
		ArrayList<Integer> time = new ArrayList<Integer>();

		for (int i = 0; i < TIME + 1; i++)
		{
			time.add(i);
		}

		return time;
	}


	public static void main(String[] args)
	{

		//Instance for clock
		//Clock clock = initClock();

		//Create time array
		ArrayList<Integer> time = createTime();

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
		System.out.println("Exccentricity");
		System.out.println(orbitsList[0].getEccentricity());
		System.out.printf("%-30s %-30s %-20s %-20s %-20s %n", "X", "Y", "Z", "Time", "OrbitNum");

		for (int i : time)
		{
			for (Orbits orbits : orbitsList)
			{
				orbits.getRVector(time, random, i);
				//System.out.printf("%-30s %-30s %-20s %-20s %n", orbits.getX(), orbits.getY(), orbits.getZ(), i, orbits.getId());
				System.out.printf("%-30s %-30s %n", orbits.getX(), orbits.getY());

			}
			//TimeUnit.SECONDS.sleep(1);

		}


	}


}



