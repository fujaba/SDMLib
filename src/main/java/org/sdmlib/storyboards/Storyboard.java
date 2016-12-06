package org.sdmlib.storyboards;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.tables.Table;

/**
 * A StoryPage allows the use of the most important functionalities
 * of Storyboards.
 * 
 * @see StoryboardImpl
*/
public class Storyboard {

	private StoryboardImpl storyboard;
	
	public StoryboardImpl getStoryboard()
   {
      return storyboard;
   }
	
	/**
	 * Constructor for StoryPage, that creates a Storyboard by calling its
	 * standard constructor, in order to use the necessary functionalities 
	 * of the Storyboard class.
	*/
	public Storyboard() {
		this.storyboard = new StoryboardImpl();
	}
	
	/**
	 * Adds a new Step to the current Storyboard.
	 * <p>
	 * "Start:" + txt, for first step
	 * <br>
	 * stepcount + "." + txt, for every following step.
	 * 
	 * @param txt text, that is added to the new step
	 * @return return the Storyboard
	*/
	public StoryboardImpl addStep(String txt) {
		return storyboard.addStep(txt);
	}
	
	public Storyboard withDocDirName(String name )
	{
	   this.storyboard.withDocDirName(name);
	   
	   return this;
	}
	
	/**
	 * Adds a new step, with an preformatted text. 
	 * 
	 * @param expandedText this is the text, that the method is going to preformat
	*/
	public void addPreformatted(String expandedText) {
		storyboard.addPreformatted(expandedText);
	}
	
	/**
	 * Adds a string to the current step of the storyboard.
	 * 
	 * @param string a string, that will be added to the storyboard
	*/
	public void add(String string) {
		storyboard.add(string);
	}
	
	/**
	 * Adds an image to the current step of the storyboard.
	 * 
	 * @param imageFile name of the file, that contains the image
	*/
	public void addImage(String imageFile) {
		storyboard.addImage(imageFile);
	}
	
	/**
	 * Adds a diagram of an classmodel to the storyboard.
	 * 
	 * @param model object of the classmodel, that will be displayed as a diagram
	*/
	public void addClassDiagram(ClassModel model) {
		storyboard.addClassDiagram(model);
	}
	
	/**
	 * Adds an object diagram to the storyboard, that contains the given objects.
	 * 
	 * @param elems the elements, that the diagram will contain
	 * 
	*/
	public void addObjectDiagram(Object... elems) {
		storyboard.addObjectDiagram(elems);
	}
	
	/**
	 * Adds an object diagram to the storyboard, that only consists of the given objects.
	 * 
	 * @param elems the elements, that the diagram will only consist of
	*/
	public void addObjectDiagramOnlyWith(Object... elems) {
		storyboard.addObjectDiagramWith(elems);
	}
	
	/**
	 * Adds a pattern to the storyboard.
	 * 
	 * @param pattern the pattern, that will be added storyboard
	 * @param showMatch flag, that specifies, if the matches of the pattern will be shown
	 * 
	*/
	public void addPattern(PatternObject<?,?> pattern, boolean showMatch) {
		storyboard.addPattern(pattern, showMatch);
	}
	
   /**
    * Adds a pattern to the storyboard.
    * 
    * @param pattern the pattern, that will be added storyboard
    * @param showMatch flag, that specifies, if the matches of the pattern will be shown
    * 
   */
   public void addPattern(Pattern pattern, boolean showMatch) {
      storyboard.addPattern(pattern, showMatch);
   }
   
	/**
	 * Marks the current position within the code.
	*/
	public void markCodeStart() {
		storyboard.markCodeStart();
	}
	
	/**
	 * Adds a portion of code, to the storyboard, that has been marked beforehand.
	*/
	public void addCode() {
		storyboard.addCode();
	}
	
	/**
	 * Returns the code of a specified method.
	 * 
	 * @param rootDir the root directory of the file, that contains the specified method
	 * @param className name of the class, that contains the specified method
	 * @param methodSignature signature of the specified method.
	 * @return the code of the specified method
	 * 
	*/
	public String getMethodText(String rootDir, String className, String methodSignature) {
		return storyboard.getMethodText(rootDir, className, methodSignature);
	}
	
	/**
	 * Records the output of System.out.
	*/
	public void recordSystemOut() {
		storyboard.recordSystemOut();
	}
	
	/**
	 * Adds the output of System.out, to the storyboard, after it has been recorded.
	*/
	public void addSystemOut() {
		storyboard.add(storyboard.getSystemOut().toString());
	}
	
	/**
	 * Creates an html file, that contains the storyboard.
	*/
	public void dumpHTML() {
		storyboard.dumpHTML();
	}
	
	/**
	 * Asserts, that an expected and actual value are equal
	 * and adds the result to the storyboard.<br>
	 * If this is not the case, a message will be printed.
	 * 
	 * @param message the message, that will be displayed, in case, that the assertion failed
	 * @param expected the expected value
	 * @param actual the actual value
	 * @param delta allowed deviation between the two values
	*/
	public void assertEquals(String message, double expected, double actual, double delta) {
		storyboard.assertEquals(message, expected, actual, delta);
	}
	
	/**
	 * Asserts, that an expected and actual value are equal
	 * and adds the result to the storyboard.<br>
	 * If the assertion fails, a corresponding message will be printed.
	 * 
	 * @param message the message, that will be displayed, in case, that the assertion faile
	 * @param expected the expected value
	 * @param actual the actual value
	*/
	public void assertEquals(String message, Object expected, Object actual) {	
		storyboard.assertEquals(message, expected, actual);
	}
	
	/**
	 * Asserts, that a condition is fulfilled and adds the result to the storyboard.<br>
	 * If the assertion fails, a corresponding message will be printed.
	 * 
	 * @param message the message, that will be displayed, in case, that the assertion faile
	 * @param condition the condition, that is used for the assertion
	*/
	public void assertTrue(String message, boolean condition) {	
		storyboard.assertTrue(message, condition);
	}
	
	/**
	 * Asserts, that a condition is not fulfilled and adds the result to the storyboard.<br>
	 * If the assertion fails, a corresponding message will be printed.
	 * 
	 * @param message the message, that will be displayed, in case, that the assertion faile
	 * @param condition the condition, that is used for the assertion
	*/
	public void assertFalse(String message, boolean condition) {
		storyboard.assertFalse(message, condition);
	}
	
	/**
	 * Asserts, that an expected and actual value are equal
	 * and adds the result to the storyboard.<br>
	 * If the assertion fails, a corresponding message will be printed.
	 * 
	 * @param message the message, that will be displayed, in case, that the assertion faile
	 * @param expected the expected value
	 * @param actual the actual value
	*/
	public void assertEquals(String message, int expected, int actual) {
		storyboard.assertEquals(message, expected, actual);
	}	
	
	/**
	 * Asserts, that an object does not equal null and adds the result to the storyboard.<br>
	 * If the assertion fails, a corresponding message will be printed.
	 * 
	 * @param message the message, that will be displayed, in case, that the assertion faile
	 * @param obj the object, that is used for the assertion
	*/
	public void assertNotNull(String message, Object obj) {
		storyboard.assertNotNull(message, obj);
	}
	
	/**
	 * Asserts, that an object does equals null and adds the result to the storyboard.<br>
	 * If the assertion fails, a corresponding message will be printed.
	 * 
	 * @param message the message, that will be displayed, in case, that the assertion faile
	 * @param obj the object, that is used for the assertion
	*/
	public void assertNull(String message, Object obj) {
		storyboard.assertNull(message, obj);
	}

   public void addTable(Table table)
   {
      storyboard.addTable(table);
   }

   public void addLineChart(Table table)
   {
      storyboard.addLineChart(table);
   }	

   public void addBarChart(Table table)
   {
      storyboard.addBarChart(table);
   }  
}
