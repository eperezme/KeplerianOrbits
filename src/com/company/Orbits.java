package com.company;


import static com.company.Main.G;
import static com.company.Main.PI;

public class Orbits {

    /*** CONSTRUCTOR ***/
    // Hacer que variables sean mas explicatorias
    public Orbits(double _M1, double _M2, double _e, double _a) {
        M1 = _M1;
        M2 = _M2;
        eccentricity = _e;
        smAxis = _a;

        if (mu == 0) orbitSetup();
    }

    /*** METHODS ***/
    private void orbitSetup() {
        mu = G * (M1 + M2);
        n = Math.sqrt((mu / Math.pow(smAxis, 3)));
        p = smAxis * (1 - Math.pow(eccentricity, 2));
        period = 2 * PI * Math.sqrt((Math.pow(smAxis, 3)) / (mu));
        M = 0;

    }

    public double getEccAnom(double MeanAnom) {
        double K = PI / 180.0;
        int i = 0;
        error = 1;
        delta = Math.pow(10, -dp);
        MeanAnom = MeanAnom / 360;
        MeanAnom = 2.0 * PI * (MeanAnom - Math.floor(MeanAnom));
        if (MeanAnom > -PI && MeanAnom < 0 || MeanAnom > PI) EccAnom = MeanAnom - eccentricity;
        else EccAnom = MeanAnom + eccentricity;
        i_ke = 0;
        while (error > TOLERANCE && i_ke != MAX_ITERATION) {
            EccAnomPlus = EccAnom + ((MeanAnom - EccAnom + eccentricity * Math.sin(EccAnom)) / (1 - eccentricity * Math.cos(EccAnom)));
            error = Math.abs(EccAnomPlus - EccAnom);
            EccAnom = EccAnomPlus;
            i_ke++;
        }
        iter = i_ke;
        return EccAnom;
    }

    public double getTrueAnom(){
        double K, S, C, fak, phi;
        K = PI / 180.0;
        S = Math.sin(EccAnom);
        C = Math.cos(EccAnom);

        fak = Math.sqrt( 1.0 - eccentricity * eccentricity);

        phi = Math.atan2(fak * S, C - eccentricity) / K;

        return Math.round(phi * Math.pow(10, dp)) / Math.pow(10, dp);
    }

    public String getPosition() {
        double C, S, x, y;
// a=semimajor axis, ec=eccentricity, E=eccentric anomaly
// x,y = coordinates of the planet with respect to the Sun

        C = Math.cos(EccAnom);

        S = Math.sin(EccAnom);

        x = smAxis * (C - eccentricity);

        y = smAxis * Math.sqrt(1.0 - eccentricity * eccentricity) * S;

        x_stored = x;
        y_stored = y;

        return "(" + x + "," + y + ")";

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

    public double getN() {
        return n;
    }

    public double getPeriod() {
        return period;
    }

    public double getP() {
        return p;
    }

    public double getIter(){
        return iter;
    }

    public double getX_stored(){
        if(x_stored == 0) {
            getPosition();
            return x_stored;
        }
        else return x_stored;
        }

    public double getY_stored() {
        if(y_stored == 0){
            getPosition();
            return y_stored;
        }
        else return y_stored;
    }

    /*** Atributes ***/

    private double M1, M2, eccentricity, smAxis, M, EccAnom, EccAnomPlus, mu = 0, n, p, period, error, delta, dp;
    private int i_ke, iter;
    private static final int MAX_ITERATION = 15;
    private static final double TOLERANCE = Math.pow(10, -8);
    private double x_stored, y_stored;
}
