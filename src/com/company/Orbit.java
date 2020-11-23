package com.company;

import lombok.Getter;
import lombok.Setter;
import org.ejml.simple.SimpleMatrix;
import org.threadly.util.Clock;

import java.util.ArrayList;
import java.util.Random;

import static com.company.Main.PI;

@Getter
@Setter
public class Orbit
{

	/*** CONSTRUCTOR ***/
	// It needs the following parameters:
	// e is the eccentricity, how much eccentric is the anomaly, from 0 to 0.9999, (0 meaning totally circular)
	// a is the semi-major axis, the distance from the center of the orbit to one of the apsis (Apoapsis or Periapsis)
	// omega is the longitude of the Ascending Node
	public Orbit(double _e, double _a, double _omega, double _w, double _i, double _period)
	{
		eccentricity = _e;
		smAxis = _a;
		longitudeAscendingNode = _omega;
		argumentPeriapsis = _w;
		inclination = _i;
		period = _period;
		rotationMatrix = calculateRotationMatrix();
		rotationMatrix.print();
		orbitSetup();
	}

	/*** METHODS ***/
	private void orbitSetup()
	{
		meanAngularMotion = (2 * PI) / period;
		mu = Math.pow(meanAngularMotion, 2) * Math.pow(smAxis, 3);
		semiParameter = smAxis * (1 - Math.pow(eccentricity, 2));
		period = 2 * PI * Math.sqrt((Math.pow(smAxis, 3)) / (mu));


	}

	//Mean anomaly is the angle in degrees (0,0 is one of the focus) from the periapsis.
	// It gives the position assuming that the body's velocity doesn't change.
	// Necessary to get the eccentric anomaly.
	public double getMeanAnom(ArrayList<Integer> time, int k)
	{
		return getMeanAngularMotion() * time.get(k);
	}


	// Eccentric anomaly is the angle in degrees (0,0 is the center of the ellipse) from the Periapsis to the Teorical position
	// if the ellipse was a circle. Is an intermediate parameter
	//Here we apply the Newton's iteration method to find the Eccentric anomaly using the Mean anomaly until they converge into
	// a solution.
	public double getEccAnom(ArrayList<Integer> time, int k)
	{

		double error = 1;
		double MeanAnom = getMeanAnom(time, k);
		MeanAnom = MeanAnom / 360;
		MeanAnom = 2.0 * PI * (MeanAnom - Math.floor(MeanAnom));
		if (MeanAnom > -PI && MeanAnom < 0 || MeanAnom > PI)
			eccAnom = MeanAnom - eccentricity;
		else
			eccAnom = MeanAnom + eccentricity;
		int i_ke = 0;
		while (error > TOLERANCE && i_ke != MAX_ITERATION)
		{
			double eccAnomPlus = eccAnom + ((MeanAnom - eccAnom + eccentricity * Math.sin(eccAnom)) / (1 - eccentricity * Math.cos(eccAnom)));
			error = Math.abs(eccAnomPlus - eccAnom);
			eccAnom = eccAnomPlus;
			i_ke++;
		}
		iter = i_ke;
		return eccAnom;
	}

	// True Anomaly is the angle in degrees (0,0 is ) from the periapsis to the real position <-- That's what we are searching for.
	public double getTrueAnom(ArrayList<Integer> time, int k)
	{
		double K, S, C, fak, phi;
		double eccAnom = getEccAnom(time, k);
		K = PI / 180.0;
		S = Math.sin(eccAnom);
		C = Math.cos(eccAnom);

		fak = Math.sqrt(1.0 - eccentricity * eccentricity);

		phi = Math.atan2(fak * S, C - eccentricity) / K;

		return Math.round(phi * Math.pow(10, dp)) / Math.pow(10, dp);
	}

	//Once we find the True Anomaly we need to get a 3D Vector from the Main focus to the position.
	// We use the rotationMatrix that, using the orbital parameters, gives us the 3D vector to the position.
	public void getRVector(ArrayList<Integer> time, Random random, int k)
	{
		double phi, cosTrue, sinTrue;
		phi = getTrueAnom(time, k);
		cosTrue = Math.cos(phi);
		sinTrue = Math.sin(phi);

		SimpleMatrix rVectorPQW = new SimpleMatrix(3, 1);
		rVectorPQW.set(0, 0, ((semiParameter * cosTrue) / (1 + eccentricity * cosTrue)));
		rVectorPQW.set(1, 0, ((semiParameter * sinTrue) / (1 + eccentricity * cosTrue)));
		rVectorPQW.set(2, 0, 0);

		//SimpleMatrix rVectorPQW = new SimpleMatrix(3,1, true, new double[]{  (semiParameter * cosTrue) / (1 + eccentricity * cosTrue), (semiParameter * sinTrue) / (1 + eccentricity * sinTrue), 0 });
		SimpleMatrix rVectorIJK = rotationMatrix.mult(rVectorPQW);
		//System.out.println("p = " + p + " && " + "true " + phi + " && " + "e " + eccentricity);
		//rotationMatrix.print();
		//rVectorPQW.print();
		//rVectorIJK.print();
		x = rVectorIJK.get(0, 0);
		y = rVectorIJK.get(1, 0);
		z = rVectorIJK.get(2, 0);
	}

	// it calculates the matrix used for finding the vector in a 3D space.
	// if any of the parameters (related to this method) change, the entire matrix has to be recalculated again using this method.
	public SimpleMatrix calculateRotationMatrix()
	{
		double omega = Math.toRadians(longitudeAscendingNode);
		double i = Math.toRadians(inclination);
		double w = Math.toRadians(argumentPeriapsis);
		double cosLon = Math.cos(omega);
		double sinLon = Math.sin(omega);
		double cosArg = Math.cos(w);
		double sinArg = Math.sin(w);
		double cosInc = Math.cos(i);
		double sinInc = Math.sin(i);
		SimpleMatrix rotation = new SimpleMatrix(3, 3);
		rotation.set(0, 0, cosLon * cosArg - sinLon * sinArg * cosInc);
		rotation.set(1, 0, sinLon * cosArg + cosLon * sinArg * cosInc);
		rotation.set(2, 0, sinArg * sinInc);
		rotation.set(0, 1, -cosLon * sinArg - sinLon * cosArg * cosInc);
		rotation.set(1, 1, -sinLon * sinArg + cosLon * cosArg * cosInc);
		rotation.set(2, 1, cosArg * sinInc);
		rotation.set(0, 2, sinLon * sinInc);
		rotation.set(1, 2, -cosLon * sinInc);
		rotation.set(2, 2, cosInc);

		return rotation;

	}

	// To change the time needed to complete one orbit.
	public void setPeriod(double _period)
	{
		period = _period;
		updateMeanAngularMotion();
	}

	// To change the mean velocity.
	public void setMeanAngularMotion(double _newAngularMotion)
	{
		meanAngularMotion = _newAngularMotion;
		updatePeriod();
	}

	public void updatePeriod()
	{
		period = (2 * PI) / meanAngularMotion;
	}

	public void updateMeanAngularMotion()
	{
		meanAngularMotion = (2 * PI) / period;
	}

	public double getTimeSincePeriapsis()
	{
		return getActualTime() % getPeriod();
	}

	public double getActualTime()
	{
		return (double) Clock.accurateForwardProgressingMillis() / 1000;
	}


	/*** Atributes ***/
	private final double eccentricity;
	private final double smAxis;
	private double eccAnom;
	private double mu = 0;
	private double meanAngularMotion;
	private double semiParameter;
	private double period;
	private final double dp = 15;
	private int iter;
	private static final int MAX_ITERATION = 15;
	private static final double TOLERANCE = Math.pow(10, -8);
	private double xStored, yStored, x, y, z;
	private final double inclination;
	private final double longitudeAscendingNode;
	private final double argumentPeriapsis;
	public SimpleMatrix rotationMatrix;

}

