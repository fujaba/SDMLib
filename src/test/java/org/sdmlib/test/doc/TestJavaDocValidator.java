package org.sdmlib.test.doc;

import java.io.File;

import org.junit.Test;
import org.sdmlib.doc.util.JavaDocValidator;

/**
 * Here will be the java doc validator tested 
 * 
 * @author Sebastian Copei
 *
 * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/doc/TestJavaDocStories.java'>TestJavaDocStories.java</a>
 * @see org.sdmlib.test.doc.TestJavaDocStories#testGenJavaDocStory
 */
public class TestJavaDocValidator {
	
	@Test
	public void testValidator() {
		
		//Create a new java doc validator
		JavaDocValidator val = new JavaDocValidator();
		
		//Look at the java doc from src/main/java, set the boolean to true to see all founded errors on the console
		val.validateFileTree(new File("src/main/java"), false, false);
		
	}


	/**
	 *
	 * @see org.sdmlib.test.doc.TestJavaDocStories#testGenJavaDocStory
	 * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/doc/TestJavaDocStories.java'>TestJavaDocStories.java</a>
 */
	public String hello()
	{
		return "world";
	}

}
