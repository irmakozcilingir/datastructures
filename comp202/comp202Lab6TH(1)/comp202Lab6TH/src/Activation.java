/**
 * The interface for the activation (aka transfer) functions.
 * func: the function
 * der: the derivative of the function
 * 
 * @author kurmanbek
 *
 */
public interface Activation {
	public double func(double x);
	public double der(double x);
}
