import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {
	private static final String FILENAME = "students";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StdDoubleLL stdDB = new StdDoubleLL();
		BufferedReader br = null;
		FileReader fr = null;
		// System.out.println("0101".compareTo("0100"));

		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {

				String[] record = sCurrentLine.split(",");
				switch (record[0]) {
				case "insert":
					try {
						stdDB.insert(
								new StdNode(record[1], record[2], record[3], Integer.valueOf(record[4]), record[5]));
					} catch (DuplicateIDException e) {
						// TODO Auto-generated catch block
						System.out.println("\nTried to insert " + e.getID() + " but it is already in the database");
						// e.printStackTrace();
					}
					break;
				case "search":
					StdNode stdSearch = stdDB.search(record[1]);

					if (stdSearch == null)
						System.out.println("ID/" + record[1] + " is not Found");
					else {
						System.out.println("Found information: " + stdSearch);

					}
					break;
				case "printIDInterval":
					System.out.println("Students between " + record[1] + "---" + record[2]);
					stdDB.print(record[1], record[2]);
					break;
				case "printMajor":
					System.out.println("Students enrolled in " + record[1]);
					stdDB.print(record[1]);
					break;
				case "printAll":
					System.out.println("All students ");
					stdDB.print();
					break;
				case "delete":
					System.out.println("\nDelete: " + record[1]);
					if (stdDB.delete(record[1]))
						System.out.println(record[1] + " is deleted successfully");
					else
						System.out.println(record[1] + " is not in the list");
					break;
				default:
					break;

				}

			}
			br.close();
			fr.close();
		} catch (IOException | NumberFormatException e) {

			e.printStackTrace();

		}

	}

}
