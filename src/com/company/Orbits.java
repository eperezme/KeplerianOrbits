package com.company;

public class Orbits {
    /** Atributes **/ // Se suelen poner abajo
    public double G = 6.67428E-11;
    private double M1, M2, e, a, M, E, Eplus, mu = 0, n, p, period, error;
    private int i_ke;
    private int max_iteration = 15;
    private double tolerance = Math.pow(10, -8);
    public double pi = Math.PI;


    /** Constructor **/
    public Orbits(double _M1, double _M2, double _e, double _a){
        M1 = _M1;
        M2 = _M2;
        e = _e;
        a = _a;

        if (mu == 0) orbitSetup();
    }

    private void orbitSetup() {
        mu = G * (M1 + M2);
        n = Math.sqrt((mu/Math.pow(a, 3)));
        p = a * (1 - Math.pow(e, 2));
        period = 2 * pi * Math.sqrt((Math.pow(a, 3))/(mu));
        M = 7;

    }

    public double getE(){
        error = 1;
        if (M > -pi && M < 0 || M > pi) E = M - e;
        else E = M + e;
        i_ke = 0;
        while(error > tolerance && i_ke != max_iteration){
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
    public double getN(){
        return n;
    }

    public double getPeriod() {
        return period;
    }

    public double getP() {
        return p;
    }
}
