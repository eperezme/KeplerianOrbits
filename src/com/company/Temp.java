package com.company;

import static com.company.Main.PI;


public class Temp {
    //For small eccentricities the mean anomaly M can be used as an initial value E0 for the iteration.
    //In case of e>0.8 the initial value E0=π is taken.



public Temp(double _ec, double _m, int _dp){
        ec = _ec;
        MeanAnom = _m;
        dp = _dp;

}

    public double EccAnom() {
// arguments:
// ec=eccentricity, m=mean anomaly,
// dp=number of decimal places
        double K = PI / 180.0;
        int maxIter = 30, i = 0;
        delta = Math.pow(10, -dp);
        MeanAnom = MeanAnom / 360.0;
        MeanAnom =2.0 * PI * (MeanAnom - Math.floor(MeanAnom));

        if (ec < 0.8) EccAnom = MeanAnom; else EccAnom = PI;

        F = EccAnom - ec * Math.sin(MeanAnom) - MeanAnom;

        while ((Math.abs(F) > delta) && (i < maxIter)) {

            EccAnom = EccAnom - F / (1.0 - ec * Math.cos(EccAnom));
            F = EccAnom - ec * Math.sin(EccAnom) - MeanAnom;

            i = i + 1;

        }

        EccAnom = EccAnom / K;

        return Math.round(EccAnom * Math.pow(10, dp)) / Math.pow(10, dp);

    }

    public double TrueAnom() {
    double K, S, C, fak, phi;
        K = Math.PI/180.0;
        S = Math.sin(EccAnom);
        C = Math.cos(EccAnom);

        fak = Math.sqrt( 1.0 - ec * ec);

        phi = Math.atan2(fak * S, C - ec) / K;

        return Math.round(phi * Math.pow(10, dp)) / Math.pow(10, dp);

    }

    public String position() {
    double C, S, x, y;
// a=semimajor axis, ec=eccentricity, E=eccentric anomaly
// x,y = coordinates of the planet with respect to the Sun

        C = Math.cos(EccAnom);

        S = Math.sin(EccAnom);

        x = a * (C - ec);

        y = a * Math.sqrt(1.0 - ec * ec) * S;

        return "(" + x + "," + y + ")";

    }

    /*** ATRIBUTES ***/
    public double ec, MeanAnom, delta, EccAnom, F, a = 40000;
    public int dp;

}
