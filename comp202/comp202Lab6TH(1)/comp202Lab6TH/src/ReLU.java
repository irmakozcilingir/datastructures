/**
 * The rectifier function and its derivative. Its name comes from "Rectifier Linear Unit"
 * 
 * @author kurmanbek
 *
 */
public class ReLU implements Activation{
	public double func(double x) {
		return Math.max(0, x);
	}
	public double der(double x) {
	  // The derivative when x = 0 is undefined but for the sake of simplicity we treat it as 0
		if(x <= 0) {
			return 0;
		}
		return 1;
	}
}
