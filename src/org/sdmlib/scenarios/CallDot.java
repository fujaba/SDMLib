package org.sdmlib.scenarios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CallDot {
	private static void writeToFile(String imgName, String fileText) throws IOException
	{
		BufferedWriter out;
		out = new BufferedWriter(new FileWriter("doc/" + imgName + ".dot"));

		out.write(fileText);
		out.close();
	}

	public static void callDot(String imgName, String dotContent) {
//		BufferedWriter out; 
	      String[] command = null;
	      
	      File docDir = new File("doc");
	      
	      docDir.mkdir();

	      if ((System.getProperty("os.name").toLowerCase()).contains("windows")) {
	     	  command = new String [] {"../SDMLib/tools/makeimage.bat", imgName};
	      }
	      else if ((System.getProperty("os.name").toLowerCase()).contains("mac")) {
	    	  command = new String [] {"../SDMLib/tools/Graphviz/osx_lion/makeimage.command", imgName};
	      }
	      else { // let's assume it's linux'ish (works also for mac)
	    	  command = new String [] {"dot","doc/"+imgName+".dot","-Tsvg","-o","doc/"+imgName+".svg"};
	      }
	      try {
	    	  writeToFile(imgName, dotContent);
	    	  Process child = Runtime.getRuntime().exec(command);
	    	  child.waitFor();
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	      
			
//			   BufferedWriter out;
	//
//			   File dotFile = new File("doc/" + diagName + ".dot");
//			   ScenarioManager.get().printFile(dotFile, dotFileText.toString());
	}

}
