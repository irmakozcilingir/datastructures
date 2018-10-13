/**
 * The sigmoid function and its derivative
 * 
 * @author kurmanbek
 *
 */
public class Sigmoid implements Activation{
	public double func(double x) {
		return  (1 / (1 + Math.exp(-x)));
	}
	public double der(double x) {
		double gx = func(x);
		return gx*(1-gx);
	}
}
