package fileHandlingPractice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class fileHandling {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		String filePath = "/Users/roshaninagmote/nov1.txt";
//		FileReader fr = new FileReader(filePath);
//		BufferedReader br = new BufferedReader(fr);
//	//	System.out.println(br.read());
//		String line;
//		while((line = br.readLine()) != null){
//            //process the line
//            System.out.println(line);
//        }
//		br.close();
//        fr.close();
        readUsingFileReader();
        writeFile();
	}
	
	public static void readUsingFileReader() throws Exception
	{
		String filePath = "/Users/roshaninagmote/Documents/ip_file.txt";
		String line;
		HashMap<String, String> ip = new HashMap<String, String>();
		HashMap<String, String> ip1 = new HashMap<String, String>();
		HashMap<String, HashMap<String, String>> outerMap = new HashMap<String, HashMap<String, String>>();
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		while((line = br.readLine()) != null)
		{
			String fields[];
			fields = line.split("\t");
			ip.put(fields[0], fields[1]);
			System.out.println(fields[0]+" "+fields[1]+" "+fields[2]+" "+fields[3]);
			outerMap.put(fields[2], ip);
		}
		String value = ((HashMap<String, String>)outerMap.get("60")).get("L6").toString();
	    System.out.println("Retreived value is : " + value);
		br.close();
		fr.close();
	}
	
	public static void writeFile() throws Exception
	{
		String content = "roshani loves Rini";
		File f = new File("/Users/roshaninagmote/Documents/try.txt");
		if(!f.exists())
		{
			f.createNewFile();
		}
		FileWriter fw = new FileWriter(f.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
	}
	
}
