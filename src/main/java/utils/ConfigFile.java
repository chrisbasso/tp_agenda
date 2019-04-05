package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigFile {
		private static ConfigFile instancia;
		private String URL;
		private String USR;
		private String PWD;
		private String DEPLOYED;
		private String fileName;
		
		private ConfigFile() {
			//The name of the file to open.
	        fileName = "config.agf";
	        read();
		}
		
		public static ConfigFile getInstance() {
			if(instancia == null)	{
				instancia = new ConfigFile();
			}
			return instancia;
		}
		
		private void read() {
			// This will reference one line at a time
	        String line = null;

	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = new FileReader(fileName);
	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            while((line = bufferedReader.readLine()) != null) {
	                if(line.startsWith("URL=") && line.length()>5) {
	                	this.URL = line.substring(4);
	                }else if (line.startsWith("USR=") && line.length()>5) {
	                	this.USR = line.substring(4);
	                }else if (line.startsWith("PWD=") && line.length()>5) {
	                	this.PWD = line.substring(4);
	                }else if (line.startsWith("DEPLOYED=") && line.length()>5) {
	                	this.DEPLOYED = line.substring(9);
	                }
	            }  
	            // Always close files.
	            bufferedReader.close();         
	        }catch(IOException ex) {
	        	ex.printStackTrace();
	        }
		}
		
		public void update(String URL, String USR, String PWD, String DEP) {

	        try {
	            // Assume default encoding.
	            FileWriter fileWriter = new FileWriter(fileName);
	            // Always wrap FileWriter in BufferedWriter.
	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	            // Note that write() does not automatically
	            // append a newline character.
	            bufferedWriter.write("URL=" + URL);
	            bufferedWriter.newLine();
	            bufferedWriter.write("USR=" + USR);
	            bufferedWriter.newLine();
	            bufferedWriter.write("PWD=" + PWD);
	            bufferedWriter.newLine();
	            bufferedWriter.write("DEPLOYED=" + DEP);

	            // Always close files.
	            bufferedWriter.close();
	        }
	        catch(IOException ex) {
	        	ex.printStackTrace();
	        }			
		}

		public String getURL() {
			return URL;
		}

		public String getUSR() {
			return USR;
		}

		public String getPWD() {
			return PWD;
		}
		
		public String getDEPLOYED() {
			return DEPLOYED;
		}

}
	       
