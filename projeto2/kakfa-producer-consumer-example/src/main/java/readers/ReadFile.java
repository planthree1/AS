package readers;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class ReadFile {

	public static ArrayList<String> readFile() {
		
		ArrayList<String> data = new ArrayList<String>();
		
		try {
			
			String path = System.getProperty("user.dir");
			String exampleFile_path = System.getProperty("os.name").equals("Linux") ?
					path +  "/src/main/java/data/CAR.txt" :
					path +  "\\src\\main\\java\\data\\CAR.txt";
			
			RandomAccessFile file = new RandomAccessFile(exampleFile_path, "r");
			String str;
			while ((str = file.readLine()) != null) {
				data.add(str);
			}
			
			file.close();
			return data;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
