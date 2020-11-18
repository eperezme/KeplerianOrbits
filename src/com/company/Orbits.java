package com.company;


import static com.company.Main.G;
import static com.company.Main.PI;

public class Orbits {

    /*** CONSTRUCTOR ***/
    // Hacer que variables sean mas explicatorias
    public Orbits(double _M1, double _M2, double _e, double _a) {
        M1 = _M1;
        M2 = _M2;
        e = _e;
        a = _a;

        if (mu == 0) orbitSetup();
    }

    /*** METHODS ***/
    private void orbitSetup() {
        mu = G * (M1 + M2);
        n = Math.sqrt((mu / Math.pow(a, 3)));
        p = a * (1 - Math.pow(e, 2));
        period = 2 * PI * Math.sqrt((Math.pow(a, 3)) / (mu));
        M = 0;

    }

    public double getEccAnom(double M) {
        error = 1;
        if (M > -PI && M < 0 || M > PI) E = M - e;
        else E = M + e;
        i_ke = 0;
        while (error > TOLERANCE && i_ke != MAX_ITERATION) {
            Eplus = E + ((M - E + e * Math.sin(E)) / (1 - e * Math.cos(E)));
            error = Math.abs(Eplus - E);
            E = Eplus;
            System.out.println("Eplus " + Eplus);
            i_ke++;
        }
        System.out.println("Iterations " + i_ke);

        return E;
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

    /*** Atributes ***/

    private double M1, M2, e, a, M, E, Eplus, mu = 0, n, p, period, error;
    private int i_ke;
    private static final int MAX_ITERATION = 15;
    private static final double TOLERANCE = Math.pow(10, -8);
}
