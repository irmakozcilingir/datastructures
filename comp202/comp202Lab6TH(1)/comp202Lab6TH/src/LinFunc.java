/**
 * The standard linear function and its derivative. Mostly used for the input and output neurons.
 * 
 * @author kurmanbek
 *
 */
public class LinFunc implements Activation{
	public double func(double x) {
		return x;
	}
	public double der(double x) {
		return 1;
	}
}
