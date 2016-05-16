package org.sdmlib.doc.util;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * This class can check a part of file tree which contains java files. The java doc is the object to validate. 
 * The validator checks if there is a java doc and it is correct.
 * 
 * @author Sebastian Copei
 *
 */
public class JavaDocValidator {
	
	/**
	 * This methods goes recursively through a file tree, starting by the given file. Only files like *.java and directories will visit, java files
	 * will validated. If there is no java doc for a method or the java doc is not valid, there will be created a trace information. This will be 
	 * saved in a list and returned by this method. If the boolean printOnConsole is set to true, a founded trace information will be printed in
	 * the console.
	 * 
	 * @param start The start of the file tree
	 * @param printOnConsole Should founded warnings or error printed on the console
	 * @param fullCheck If true everything will checked in every class, if false only created java doc will validate.
	 * @return A list with all trace informations
	 */
	public ArrayList<String> validateFileTree(File start, boolean printOnConsole, boolean fullCheck) {
		
		//Create the log list with trace informations
		ArrayList<String> traceInf = new ArrayList<String>();

		//Check if the start is a file, we need a directory
		if(start.isDirectory()) {
			
			try {

				//Goes through the hole file tree and visit all files and directories
				Files.walkFileTree(start.toPath(), new JavaDocFileVisitor(traceInf, printOnConsole, fullCheck));
				
			} catch(Exception e) {
				
				//Put the error to the trace informations
				traceInf.add(e.getMessage());
				
				//Print it if you want
				if(printOnConsole) {
					
					e.printStackTrace();
					
				}
			}
			
		//The file is not a directory 
		} else if(start.isFile()) {
			
			//Create a error and but it to the trace information array
			traceInf.add("ERROR: The start point have to be a directory!");
			
			//Check if print
			if(printOnConsole) {

				//Print the lass information on the console
				System.err.println(traceInf.get(traceInf.size() - 1));
				
			}
		}
		
		return traceInf;
		
	}
}
