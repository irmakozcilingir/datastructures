
public class DuplicateIDException extends Exception {
	private String ID;
	public DuplicateIDException(String ID){
		setID(ID);
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}

}
