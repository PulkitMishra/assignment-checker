import java.util.*; 
import java.io.*; 
class pb 
{ 
	public static void main(String [] args) throws IOException 
	{ 
		try { 
			// create a process 
			ProcessBuilder pb = new ProcessBuilder("/bin/bash"); 

			// take all commands as input in a text file 
			File commands = new File("commands.txt"); 

			// File where error logs should be written 
			File error = new File("error.txt"); 

			// File where output should be written 
			File output = new File("output.txt"); 

			// redirect all the files 
			pb.redirectInput(commands); 
			pb.redirectOutput(output); 
			pb.redirectError(error); 

			// start the process 
			pb.start(); 
		} 
		catch(Exception e) 
		{ 
			e.printStackTrace(); 
		} 
	} 
} 

