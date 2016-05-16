package org.sdmlib.doc.util;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is the visitor which analyse the java doc in a single file
 * 
 * 
 * @author Sebastian Copei
 *
 */
public class JavaDocFileVisitor implements FileVisitor<Path> {

	/**
	 * The suffix of a java file as constant for easer use
	 */
	private static final String JAVA_FILE_SUFFIX = ".java";

	/**
	 * The current package of the current file
	 */
	private String currentPackage;

	/**
	 * The current file name
	 */
	private String currentFileName;
	
	/**
	 * The informations about the current searching
	 */
	private ArrayList<String> traceInf;
	
	/**
	 * Should everything printed on console
	 */
	private boolean printOnConsole;
	
	/**
	 * Should be check everything or just always written java doc
	 */
	private boolean fullCheck;
	
	/**
	 * The recognize a change in the list, just for beauty print
	 */
	private int lastTraceInfSize;
	
	/**
	 * Construct a new java doc file visitor which checks the current file for the java doc
	 * 
	 * @param traceInf The informations about the current searching
	 * @param printOnConsole Should everything printed on console
	 * @param fullCheck If true everything will checked in every class, if false only created java doc will validate.
	 */
	public JavaDocFileVisitor(ArrayList<String> traceInf, boolean printOnConsole, boolean fullCheck) {

		//Initialize all attributes
		this.traceInf = traceInf;
		this.printOnConsole = printOnConsole;
		this.fullCheck = fullCheck;
		
	}
	
	//Validates a single java file for the java doc
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		
		//Check if it is a java file
		if(file.getFileName().toString().endsWith(JAVA_FILE_SUFFIX)) {

			//Create a string
			String text = "";
			
			//Set the last trace information size
			lastTraceInfSize = traceInf.size();
			
			//Get all lines and add them to the string builder
			List<String> lines = Files.readAllLines(file);
			for(String s : lines) { text += s + "\n"; }

			//Extract the package name of the current java file
			for(String s : lines) { if(s.contains("package")) { currentPackage = s.replace("package", ""); break; }};
			currentPackage = currentPackage.substring(0, currentPackage.length() - 1);
			
			//Set the current file name
			currentFileName = file.getFileName().toString();
						
			//Check the class java doc
			checkClassJavaDoc(text.replaceAll("[\t]", ""), traceInf, lines, printOnConsole);
			
			//Check all method java doc
			checkMethodsJavaDoc(text.replaceAll("[\t]", ""), traceInf, lines, printOnConsole);
			
		}
		
		//Finish with the current file just push a new line to the console if wanted to make it more structure
		if(printOnConsole && traceInf.size() > lastTraceInfSize) {
			
			System.out.println();
			
		}
		
		//Look at the next file
		return FileVisitResult.CONTINUE;
		
	}	
	
	/**
	 * Checks if there is a java doc over the class declaration
	 * 
	 * @param text The text which should be checked
	 * @param traceInf The current trace information
	 * @param lines All lines of the current checked file
	 * @param printOnConsole Should founded warnings or error printed on the console
	 */
	private void checkClassJavaDoc(String text, ArrayList<String> traceInf, List<String> lines, boolean printOnConsole) {
		
		//First check if the class has java doc, first get the index of "public class"
		int start = text.indexOf("public class");
		
		//if the start -1 the class could be abstract
		if(start == -1) {
			
			//Get the index of "public abstract class"
			start = text.indexOf("public abstract class");
			
		}
		
		//If the start -1 the class is a enumeration
		if(start == -1) {
			
			//Get the index of "public enum"
			start = text.indexOf("public enum");
			
		}
		
		//If the start -1 the class is a interface
		if(start == -1) {
			
			//Get the index of "public interface"
			start = text.indexOf("public interface");
			
		}
		
		//Maybe there is just a class
		if(start == -1) {
			
			//Get the index of "class"
			start = text.indexOf("class ");
			
		}
		
		//Get the line of the class definition to jump there if there is no comment
		int lineClass = -1;
		for(String s : lines) { if(s.contains("public class")) { lineClass = lines.indexOf(s) + 1; break; }}
		
		//Check if line class is still -1, this means class could be abstract
		for(String s : lines) { if(s.contains("public abstract class")) { lineClass = lines.indexOf(s) + 1; break; }}
		
		//Check if line class is still -1, this means class is a interface
		if(lineClass == -1) {
			
			for(String s : lines) { if(s.contains("public interface")) { lineClass = lines.indexOf(s) + 1; break; }}
		
		}
		
		//Check if line class is still -1, this means class is a enumeration
		if(lineClass == -1) {
			
			for(String s : lines) { if(s.contains("public enum")) { lineClass = lines.indexOf(s) + 1; break; }}
			
		}
		
		//Maybe there is just a class
		if(lineClass == -1) {
			
			for(String s : lines) { if(s.contains("class ")) { lineClass = lines.indexOf(s) + 1; break; }}
			
		}
			
		//Check if the char before the method declaration is a end of a comment
		if(lines.get(lineClass - 2).contains("*/")) {
			
			//Get the java doc comment, 0 because the first comment is the class comment
			String classDoc = extractJavaDocComment(text, start);
			
			//There is no comment or a wrong one
			if(classDoc.isEmpty() && fullCheck) {

				//Put the missing java doc to the trace informations
				traceInf.add("ERROR:" + currentPackage + ".missing.ClassDoc(" + currentFileName + ":" + lineClass + ")");
				
				//Print if you want to
				if(printOnConsole) {
					
					System.err.println(traceInf.get(traceInf.size() - 1));
					
				}
				
			//There is a comment check if there is a @author tag and text in the comment
			} else {
				
				//Check if there is a line like * some text
				if(!Pattern.compile("\\u002A \\w+").matcher(classDoc).find()) {
					
					//There is no text, put a missing doc description error
					traceInf.add("WARNING:" + currentPackage + ".missing.ClassDocText(" + currentFileName + ":" + lineClass + ")");
					
					//Print if you want to
					if(printOnConsole) {
						
						System.out.println(traceInf.get(traceInf.size() - 1));
						
					}
				}

				//Check if there is the @author tag with some text
				if(classDoc.split("@author").length == 1) {
					
					//There is no tag and no text
					traceInf.add("ERROR:" + currentPackage + ".missing.AuthorTag(" + currentFileName + ":" + lineClass + ")");
					
					//Check if this should be printed
					if(printOnConsole) {
						
						System.err.println(traceInf.get(traceInf.size() - 1));
						
					}
					
				//There is the tag but no text
				} else if(classDoc.split("@author [a-zA-Z]+").length == 1) {
					
					//There is no text after the tag, create warning
					traceInf.add("WARNING:" + currentPackage + ".missing.AuthoTagText(" + currentFileName + ":" + lineClass + ")");
					
					//Check if this should be printed
					if(printOnConsole) {
						
						System.out.println(traceInf.get(traceInf.size() - 1));
						
					}
				}
			}
			
			//Return we found a doc or a incomplete doc
			return;
			
		}
		
		//Only if full check is enable
		if(fullCheck) {
		
			//There is no comment in any case over the class definition, therefore create error
			traceInf.add("ERROR:" + currentPackage + ".missing.ClassDoc(" + currentFileName + ":" + lineClass + ")");
			
			//Print if you want to
			if(printOnConsole) {
				
				System.err.println(traceInf.get(traceInf.size() - 1));
				
			}
		}
	}
	
	/**
	 * Checks if there is a java doc over all method declarations
	 * 
	 * @param text The text which should be checked
	 * @param traceInf The current trace information
	 * @param lines All lines of the current checked file
	 * @param printOnConsole Should founded warnings or error printed on the console
	 */
	private void checkMethodsJavaDoc(String text, ArrayList<String> traceInf, List<String> lines, boolean printOnConsole) {
		
		//Create a matcher to find all method declarations
		Matcher methodPattern = Pattern.compile("((public|private|protected|static|final|native|synchronized|abstract|transient)+\\s)+[\\$_\\w\\<\\>\\[\\]]*\\s+[\\$_\\w]+\\([^\\)]*\\)?\\s*\\{?").matcher(text);

		//Go through all matches
		while(methodPattern.find()) {

			//Get the current match
			String match = methodPattern.group();
			
			//Get the index of the current match in the text
			int start = methodPattern.start();
			
			//Get the line of the method definition to jump there if there is no comment
			int lineMethod = -1;
			for(String s : lines) { if(s.contains(match)) { lineMethod = lines.indexOf(s) + 1; break; }}
			
			//If lineMethod still -1 the { is in the next row therefore cut at \n and try again
			if(lineMethod == -1) {

				for(String s : lines) { if(s.contains(match.split("\n")[0])) { lineMethod = lines.indexOf(s) + 1; break; }}
				
			}
			
			//Check if there is a annotation over the method, if so go to the next method
			if(lines.get(lineMethod - 2).contains("@")) { continue; }
			
			//Check if the char before the method declaration is a end of a comment
 			if(lines.get(lineMethod - 2).contains("*/")) {
			
				//Get the java doc comment
				String methodDoc = extractJavaDocComment(text, start);
				
				//There is no comment or a wrong one
				if(methodDoc.isEmpty() && fullCheck) {

					//Put the missing java doc to the trace informations
					traceInf.add("ERROR:" + currentPackage + ".missing.MethodDoc(" + currentFileName + ":" + lineMethod + ")");
					
					//Print if you want to
					if(printOnConsole) {
						
						System.err.println(traceInf.get(traceInf.size() - 1));
						
					}
					
				//There is a comment check if there are all needed tags
				} else {
					
					//Check if there is a line like * some text
					if(!Pattern.compile("\\u002A \\w+").matcher(methodDoc).find()) {
						
						//There is no text, put a missing doc description error
						traceInf.add("WARNING:" + currentPackage + ".missing.MethodDocText(" + currentFileName + ":" + lineMethod + ")");
						
						//Print if you want to
						if(printOnConsole) {
							
							System.out.println(traceInf.get(traceInf.size() - 1));
							
						}
					}

					//Check if there are parameters in the method declaration
					if(!match.contains("()")) {
						
						//Get all parameters from method declaration
						String temp = match.substring(match.indexOf("(") + 1, match.indexOf(")")).replaceAll("<[^\\)]*>", "");
						String[] parameters = temp.split(",");
						
						//Go through all parameters and check the @param tag
						for(String s : parameters) {
							
							String parameterName = s.trim().split(" ")[1];
							
							//Check if there is a @param tag with the current parameter name
							if(methodDoc.split("@param " + parameterName).length == 1) {
								
								//There is no tag and no text
								traceInf.add("ERROR:" + currentPackage + ".missing.ParamTag(" + currentFileName + ":" + lineMethod + ")");
								
								//Check if this should be printed
								if(printOnConsole) {
									
									System.err.println(traceInf.get(traceInf.size() - 1));
									
								}
								
							//There is the tag but no text
							} else if(methodDoc.split("@param " + parameterName + " [a-zA-Z]+").length == 1) {
								
								//There is no text after the tag, create warning
								traceInf.add("WARNING:" + currentPackage + ".missing.ParamTagText(" + currentFileName + ":" + lineMethod + ")");
								
								//Check if this should be printed
								if(printOnConsole) {
									
									System.out.println(traceInf.get(traceInf.size() - 1));
									
								}
							}
						}
					}

					//Check if there is a return type in the method
					if(!match.contains("void")) {
						
						//There is no tag and text
						if(methodDoc.split("@return").length == 1) {
							
							//There is no tag and no text
							traceInf.add("ERROR:" + currentPackage + ".missing.ReturnTag(" + currentFileName + ":" + lineMethod + ")");
							
							//Check if this should be printed
							if(printOnConsole) {
								
								System.err.println(traceInf.get(traceInf.size() - 1));
								
							}
							
						//There is the tag but no text
						} else if(methodDoc.split("@return [a-zA-Z]+").length == 1) {
							
							//There is no text after the tag, create warning
							traceInf.add("WARNING:" + currentPackage + ".missing.ReturnTagText(" + currentFileName + ":" + lineMethod + ")");
							
							//Check if this should be printed
							if(printOnConsole) {
								
								System.out.println(traceInf.get(traceInf.size() - 1));
								
							}
						}
					}
				}
				
				//Look at the next match
				continue;
					
			}
			
 			//Only if full check is enable
 			if(fullCheck) {
 			
				//There is no comment in any case over the method definition, therefore create error
				traceInf.add("ERROR:" + currentPackage + ".missing.MethodDoc(" + currentFileName + ":" + lineMethod + ")");
				
				//Print if you want to
				if(printOnConsole) {
					
					System.err.println(traceInf.get(traceInf.size() - 1));
					
				}
 			}
		}
	}
	
	/**
	 * Extract a java doc comment from a given string 
	 * 
	 * @param extractFrom The text where the comment should be extracted from
	 * @param searchIndex The index from which the search will start
	 * @return The extracted comment as string 
	 */
	private String extractJavaDocComment(String extractFrom, int searchIndex) {
		
		//Initialize attribute which describe the range of the doc
		int begin = -1;
		int end = -1;
		
		//Go back from the searchIndex
		for(int i = searchIndex; i > 0; i--) {
			
			//Found the start of the java doc
			if(extractFrom.charAt(i) == '*' && extractFrom.charAt(i - 1) == '*' && extractFrom.charAt(i - 2) == '/') {

				//The start of the comment
				begin = i - 2;
			
			//Found the end of the java doc
			} else if(extractFrom.charAt(i) == '/' && extractFrom.charAt(i - 1) == '*') {

				//The end of the comment
				end = i;
				
			}
			
			//If both values found break
			if(begin != -1 && end != -1) {
				
				break;
				
			}
		}
		
		//Wrong comment just a /* or there is no comment
		if(begin == -1){
			
			return "";
			
		}
		
		return extractFrom.substring(begin, end + 2);
		
	}

	//--------------------------------------NOT USED METHODS FROM FILE VISITOR--------------------------------------
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException { return FileVisitResult.CONTINUE; }

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException { return FileVisitResult.CONTINUE; }

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException { return FileVisitResult.CONTINUE; }

}
