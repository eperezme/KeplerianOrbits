package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main
{

	/*** ATRIBUTES ***/
	public static final double G = 6.67428E-11;
	public static final double PI = Math.PI;
	private static final int numberOfOrbits = 12;
	private static final int TIME = 1000;
	private static ArrayList<Orbit> orbits = new ArrayList<>();


    /*
    public static Clock initClock() {
        return new Clock();
    }
     */


	/*** FUNCTIONS**/

	public static ArrayList<Orbit> initOrbits(Random random)
	{
		for (int i = 0; i < numberOfOrbits; i++)
		{
			Orbit orbit = new Orbit(random.nextDouble(), random.nextDouble() * 10000, random.nextDouble() * 360, random.nextDouble() * 360, random.nextDouble() * 360,60);
			//orbitsList[i] = new Orbits(0.3, 4000, 0, 90, 0, i, 60);
			//Orbit orbit = new Orbit(random.nextDouble(), random.nextDouble() * 10000, 0, 90, 0,60);
			orbits.add(orbit);
		}

		return orbits;
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


	public static void main(String[] args) throws IOException {

		//Instance for clock
		//Clock clock = initClock();

		//Create time array
		ArrayList<Integer> time = createTime();

		//Instance for random number generator
		Random random = new Random();


		orbits = initOrbits(random);



		FileWriter myWriter = new FileWriter("orbitsData.txt");
		for (int i : time)
		{
			for (Orbit orbit : orbits)
			{
				orbit.getRVector(time, random, i);
				//System.out.printf("%-30s %-30s %-20s %-20s %n", orbits.getX(), orbits.getY(), orbits.getZ(), i, orbits.getId());
				System.out.printf("%-30s %-30s %-30s %n", orbit.getX(), orbit.getY(), orbit.getZ());
				myWriter.write(orbits.get(0).getX() + "\t" + orbits.get(0).getY() + "\t" + orbits.get(0).getZ() + "\n");

			}
			//TimeUnit.SECONDS.sleep(1);




		}
		myWriter.close();

	}


}



