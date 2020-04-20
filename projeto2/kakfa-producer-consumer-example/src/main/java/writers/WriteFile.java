package writers;


import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;

public class WriteFile {
	
	

	private static void CreateFile(String filename) {
		String path = System.getProperty("user.dir");
		String full_path = System.getProperty("os.name").equals("Linux") ?
				path +  "/src/main/java/data/" :
				path +  "\\src\\main\\java\\data\\";
		
		// System.getProperty("os.name")
		try {
		      File myObj = new File(full_path, filename);
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		      
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

		
	}
	
	public static void writeToFile(String data, String filename) throws IOException {
		String path = System.getProperty("user.dir");
		String full_path = System.getProperty("os.name").equals("Linux") ?
				path +  "/src/main/java/data/" :
				path +  "\\src\\main\\java\\data\\";
		
		CreateFile(filename);
		System.out.println("writing file");
		try {
	      FileWriter myWriter = new FileWriter(full_path + filename, true);
	      
	      PrintWriter printWriter = new PrintWriter(myWriter);
	      printWriter.println(data);  //New line
	      printWriter.close();
	      myWriter.close();
	      System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }

		
	}
	
	
}
