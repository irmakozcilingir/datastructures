
public class StdNode {
	private String name;
	private String surname;
	private int age;
	private String ID;
	private String major;
	StdNode next;
	StdNode prev;

	public StdNode(String ID, String name, String surname, int age, String major) {
		setID(ID);
		setName(name);
		setSurname(surname);
		setAge(age);
		setMajor(major);
		next = null;
		prev = null;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String toString() {
		return "ID:"+getID()+","+getName()+" "+getSurname()+"-"+getAge()+","+getMajor();
	}

}
