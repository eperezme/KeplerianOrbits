package com.company;

import org.ejml.simple.SimpleMatrix;
import org.threadly.util.Clock;

import java.time.Period;
import java.util.Random;
import static com.company.Main.G;
import static com.company.Main.PI;

public class Orbits {

    /*** CONSTRUCTOR ***/
    // Make self-explain variables
    public Orbits(double _e, double _a, double _omega, double _w, double _i, int _id, double _period) {
        id = _id;
        eccentricity = _e;
        smAxis = _a;
        longitudeAscendingNode = _omega;
        argumentPeriapsis = _w;
        inclination = _i;
        period = _period;
        rotationMatrix = initRotationMatrix();
        rotationMatrix.print();
        orbitSetup();
    }

    /*** METHODS ***/
    private void orbitSetup() {
        meanAngularMotion = (2 * PI) / period;
        mu = Math.pow(meanAngularMotion, 2) * Math.pow(smAxis, 3);
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



    public double getTrueAnom(){
        double K, S, C, fak, phi;
        double eccAnom = getEccAnom();
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
    public double getMeanAnom(){
        return getMeanAngularMotion() * getTimeSincePeriapsis();
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
        updateMeanAngularMotion();
    }

    // Lombok? <- Autoconstructor
    public void setMeanAngularMotion(double _newAngularMotion){
    meanAngularMotion = _newAngularMotion;
    updatePeriod();
    }

    public void updatePeriod(){
        period = (2 * PI) / meanAngularMotion;
    }

    public void updateMeanAngularMotion(){
        meanAngularMotion = (2 * PI) / period;
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
        double phi = getTrueAnom();
        double cosTrue = Math.cos(phi);
        double sinTrue = Math.sin(phi);
        SimpleMatrix rVectorPQW = new SimpleMatrix(3,1, true, new double[]{  (semiParameter * cosTrue) / (1 + eccentricity * cosTrue), (semiParameter * sinTrue) / (1 + eccentricity * sinTrue), 0 });
        SimpleMatrix rVectorIJK = rotationMatrix.mult(rVectorPQW);
        //System.out.println("p = " + p + " && " + "true " + phi + " && " + "e " + eccentricity);
        //rotationMatrix.print();
        //rVectorPQW.print();
        //rVectorIJK.print();
        x = rVectorIJK.get(0,0);
        y = rVectorIJK.get(1,0);
        z = rVectorIJK.get(2,0);
    }

    public double getTimeSincePeriapsis()
   {
       return getActualTime() % getPeriod();
   }

    public double getActualTime(){
        return (double) Clock.accurateForwardProgressingMillis() / 1000;
    }

    public SimpleMatrix initRotationMatrix(){
        double omega = Math.toRadians(longitudeAscendingNode);
        double i = Math.toRadians(inclination);
        double w = Math.toRadians(argumentPeriapsis);
        return new SimpleMatrix(3,3, true, new double[]{((Math.cos(omega) * Math.cos(w)) - (Math.sin(omega) * Math.sin(w) * Math.cos(i))), ((((-1) * Math.cos(omega)) * Math.sin(w)) - (Math.sin(omega) * Math.cos(w) * Math.cos(i))), (Math.sin(omega) * Math.sin(i)),(Math.sin(omega) * Math.cos(w) + Math.cos(omega) * Math.sin(w) * Math.cos(i)), (((-1) * Math.sin(omega)) * Math.cos(w) * Math.cos(i)), (((-1) * Math.cos(omega) * Math.sin(i))), (Math.sin(w) * Math.sin(i)), (Math.cos(w) * Math.sin(i)), (Math.cos(i))});
    }


    /*** Atributes ***/

    private final double eccentricity;
    private final double smAxis;
    private final double id;
    private double eccAnom;
    private double mu = 0;
    private double meanAngularMotion;
    private double semiParameter;
    private double period;
    private final double dp =15;
    private int iter;
    private static final int MAX_ITERATION = 15;
    private static final double TOLERANCE = Math.pow(10, -8);
    private double xStored, yStored, x, y, z;
    private final double inclination;
    private final double longitudeAscendingNode;
    private final double argumentPeriapsis;
    public SimpleMatrix rotationMatrix;

    public double getInclination() {
        return inclination;
    }

    public double getLongitudeAscendingNode() {
        return longitudeAscendingNode;
    }

    public double getArgumentPeriapsis() {
        return argumentPeriapsis;
    }

    public double getZ() {
        return z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getId() {
        return id;
    }


}

