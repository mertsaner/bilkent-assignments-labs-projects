//Author Yasin Balcancý
public class Transformer {
	private double R0;
	private double Vs;
	private double Rs;
	private double Ps;
	double[] values = new double[200];//there are 200 numbers 0.01 to 2 ( adding 0.01)
	private double optimalTurnRatio = 0;
	private double maxPs = 0;

    public Transformer(double R0, double Vs, double Rs) {
    	this.R0 = R0;
    	this.Vs = Vs;
    	this.Rs = Rs;
    }
    public double optimalTurnRatio() {//computes the optimal turn ratio
    	
    for (double n = 0.01; n < 2.0; n+=0.01){
		Ps =Rs*(Math.pow(n*Vs/(n*n*R0+Rs), 2));//mathematical formula
		if (Ps>maxPs){
			optimalTurnRatio = n;//assigns n to optimal n.
			maxPs = Ps;
		}
	}
	return optimalTurnRatio;
    }

}
