package readers;


import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

import javax.xml.crypto.Data;

public class read_file {
	
	

	public static ArrayList<String> readFile() {
		
		ArrayList<String> data = new ArrayList<String>();
		
		try {
			
			String path = System.getProperty("user.dir");
			String exampleFile_path = System.getProperty("os.name").equals("Linux") ?
					path +  "/src/main/java/readers/example.txt" :
					path +  "\\src\\main\\java\\readers\\example.txt";
			
			RandomAccessFile file = new RandomAccessFile(exampleFile_path, "r");
			String str;
			while ((str = file.readLine()) != null) {
				data.add(str);
				//System.out.println(data	);
			}
			
			file.close();
			return data;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
