public class Orbit {
    /** Attributes **/
    private double a, e, I, O, w, M;

    /** Gravitational Constant **/
    public static final double G = 6.67428E-11;

    /** Constructor **/
    public Orbit(double ac, double ec, double Ic, double Oc, double wc, double Mc){
        a = ac;
        e = ec;
        I = Ic;
        O = Oc;
        w = wc;
        M = Mc;
    }

   /** Methods **/

    public double getA(){
        return this.a;
    }

    public double gete(){
        return this.e;
    }

    public double getI(){
        return this.I;
    }

    public double getw(){
        return this.w;
    }

    public double getM(){
        return this.M;
    }


}
