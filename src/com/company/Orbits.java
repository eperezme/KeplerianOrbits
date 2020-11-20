package com.company;

import org.ejml.simple.SimpleMatrix;
import org.threadly.util.Clock;

import java.util.Random;
import static com.company.Main.G;
import static com.company.Main.PI;

public class Orbits {

    /*** CONSTRUCTOR ***/
    // Make self-explain variables
    public Orbits(double _M1, double _M2, double _e, double _a, double _omega, double _w, double _i) {
        mass1 = _M1;
        mass2 = _M2;
        eccentricity = _e;
        smAxis = _a;
        longitudeAscendingNode = _omega;
        argumentPeriapsis = _w;
        inclination = _i;
        orbitSetup();
    }

    /*** METHODS ***/
    private void orbitSetup() {
        mu = G * (mass1 + mass2);
        meanAngularMotion = Math.sqrt((mu / Math.pow(smAxis, 3)));
        semiParameter = smAxis * (1 - Math.pow(eccentricity, 2));
        period = 2 * PI * Math.sqrt((Math.pow(smAxis, 3)) / (mu));

    }

    public double getEccAnom() {

        double error = 1;
        double MeanAnom = getMeanAnom();
        MeanAnom = MeanAnom / 360;
        MeanAnom = 2.0 * PI * (MeanAnom - Math.floor(MeanAnom));
        if (MeanAnom > -PI && MeanAnom < 0 || MeanAnom > PI) eccAnom = MeanAnom - eccentricity;
        else eccAnom = MeanAnom + eccentricity;
        int i_ke = 0;
        while (error > TOLERANCE && i_ke != MAX_ITERATION) {
            double eccAnomPlus = eccAnom + ((MeanAnom - eccAnom + eccentricity * Math.sin(eccAnom)) / (1 - eccentricity * Math.cos(eccAnom)));
            error = Math.abs(eccAnomPlus - eccAnom);
            eccAnom = eccAnomPlus;
            i_ke++;
        }
        iter = i_ke;
        return eccAnom;
    }



    public double getTrueAnom(double eccAnom){
        double K, S, C, fak, phi;
        K = PI / 180.0;
        S = Math.sin(eccAnom);
        C = Math.cos(eccAnom);

        fak = Math.sqrt( 1.0 - eccentricity * eccentricity);

        phi = Math.atan2(fak * S, C - eccentricity) / K;

        return Math.round(phi * Math.pow(10, dp)) / Math.pow(10, dp);
    }

    public String getPosition() {
        double C, S, x, y;

        C = Math.cos(eccAnom);
        S = Math.sin(eccAnom);

        x = smAxis * (C - eccentricity);
        y = smAxis * Math.sqrt(1.0 - eccentricity * eccentricity) * S;

        xStored = x;
        yStored = y;

        return "(" + x + "," + y + ")";

    }


    //SOLVE THIS
    public double getMeanAnom(double meanAngularMotion, double timeSincePe){

        double meanAnomaly = meanAngularMotion * timeSincePe;

        return meanAnomaly;
    }

    public double getSmAxis(){
        return smAxis;
    }

    public double getecc(){
        return eccentricity;
    }

    public double getMu() {
        return mu;
    }

    public double getMeanAngularMotion() {
        return meanAngularMotion;
    }

    public double getPeriod() {
        return period;
    }

    public void setPeriod(double _period){
        period = _period;
    }

    public double getSemiParameter() {
        return semiParameter;
    }

    public double getIter(){
        return iter;
    }

    public double getxStored(){
        if(xStored == 0) {
            getPosition();
            return xStored;
        }
        else return xStored;
        }

    public double getyStored(){
        if(yStored == 0){
            getPosition();
            return yStored;
        }
        else return yStored;
    }


    public void getRVector()
    {
        Random random = new Random();
        SimpleMatrix rotationMatrix = new SimpleMatrix(3,3, true, new double[]{(Math.cos(longitudeAscendingNode) * Math.cos(argumentPeriapsis) - Math.sin(longitudeAscendingNode) * Math.sin(argumentPeriapsis) * Math.cos(inclination)), (-Math.cos(longitudeAscendingNode) * Math.sin(argumentPeriapsis) - Math.sin(longitudeAscendingNode) * Math.cos(argumentPeriapsis) * Math.cos(inclination)), (Math.sin(longitudeAscendingNode) * Math.sin(inclination)),(Math.sin(longitudeAscendingNode) * Math.cos(argumentPeriapsis) + Math.cos(longitudeAscendingNode) * Math.sin(argumentPeriapsis) * Math.cos(inclination)), (-Math.sin(longitudeAscendingNode) * Math.cos(argumentPeriapsis) * Math.cos(inclination)), (-Math.cos(longitudeAscendingNode) * Math.sin(inclination)), (Math.sin(argumentPeriapsis) * Math.sin(inclination)), (Math.cos(argumentPeriapsis) * Math.sin(inclination)), (Math.cos(inclination))});
        eccAnom = getEccAnom(getMeanAnom(getMeanAngularMotion(), getTimeSincePeriapsis(getActualTime(), getPeriod())));
        double phi = getTrueAnom(eccAnom);
        double cosTrue = Math.cos(phi);
        double sinTrue = Math.sin(phi);

        SimpleMatrix rVectorPQW = new SimpleMatrix(3,1, true, new double[]{  (semiParameter * cosTrue) / (1 + eccentricity * cosTrue), (semiParameter * sinTrue) / (1 + eccentricity * sinTrue), 0 });
        SimpleMatrix rVectorIJK = rotationMatrix.mult(rVectorPQW);
        System.out.println("//  OMEGA  " + longitudeAscendingNode + " // " + "w  " + argumentPeriapsis + " // " + "i " + inclination);
        //System.out.println("p = " + p + " && " + "true " + phi + " && " + "e " + eccentricity);
        //rotationMatrix.print();
        //rVectorPQW.print();
        rVectorIJK.print();
    }

    public double getTimeSincePeriapsis(double actualTime, double Period)
   {
       return actualTime % Period;
   }

    public double getActualTime(){
        return (double) Clock.accurateForwardProgressingMillis();
    }

    public void update(){
        double timeSincePeriapsis = getTimeSincePeriapsis(getActualTime(), getPeriod());
        double meanAnom = getMeanAnom(getMeanAngularMotion(),  timeSincePeriapsis);
        getEccAnom(meanAnom);


    }


    /*** Atributes ***/

    private final double mass1, mass2, eccentricity, smAxis;
    private double eccAnom;
    private double mu = 0;
    private double meanAngularMotion;
    private double semiParameter;
    private double period;
    private final double dp =15;
    private int iter;
    private static final int MAX_ITERATION = 15;
    private static final double TOLERANCE = Math.pow(10, -8);
    private double xStored, yStored;
    public double inclination, longitudeAscendingNode, argumentPeriapsis;

}

