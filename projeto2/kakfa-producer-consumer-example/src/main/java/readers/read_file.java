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
			
			RandomAccessFile file = new RandomAccessFile("H:\\Eclipse\\kakfa-producer-consumer-example\\src\\main\\java\\readers\\example.txt", "r");
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
