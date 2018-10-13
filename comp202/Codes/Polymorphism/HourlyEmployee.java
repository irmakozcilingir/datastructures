
public class HourlyEmployee extends Employee {
	private double wageRate;
	private double hours;
	
	public HourlyEmployee(String name, Date date, 
			              double wageRate, double hours){
		super(name, date);
		
		setWageRate(wageRate);
		setHours(hours);
	}
	
	public HourlyEmployee(){
		wageRate = 0;
		hours = 0;
	}
	
	public HourlyEmployee(HourlyEmployee other){
		super(other);
		wageRate = other.wageRate;
		hours = other.hours;
	}

	public double getWageRate() {
		return wageRate;
	}

	public double getHours() {
		return hours;
	}
	
	public double getPay(){
		return wageRate * hours;
	}

	public void setWageRate(double wageRate) {
		if(wageRate>=0)
			this.wageRate = wageRate;
		else
			System.exit(0);
	}
	
	public void setHours(double hours) {
		if(hours>=0)
			this.hours = hours;
		else
			System.exit(0);
	}
	
	public String toString(){
		return super.toString()+" "+wageRate+" "+hours;
	}
	
	public boolean equals(HourlyEmployee other){
        return super.equals(other) && 
          wageRate==other.wageRate && hours==other.hours;
    }

	
}
