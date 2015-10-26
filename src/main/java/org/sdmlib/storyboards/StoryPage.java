package org.sdmlib.storyboards;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.pattern.PatternObject;

public class StoryPage {

	private Storyboard storyboard;
	
	public StoryPage() {
		this.storyboard = new Storyboard();
	}
	
	public Storyboard addStep(String txt) {
		return storyboard.addStep(txt);
	}
	
	public void addPreformatted(String expandedText) {
		storyboard.addPreformatted(expandedText);
	}
	
	public void add(String string) {
		storyboard.add(string);
	}
	
	public void addImage(String imageFile) {
		storyboard.addImage(imageFile);
	}
	
	public void addClassDiagram(ClassModel model) {
		storyboard.addClassDiagram(model);
	}
	
	public void addObjectDiagram(Object... elems) {
		storyboard.addObjectDiagram(elems);
	}
	
	public void addObjectDiagramOnlyWith(Object... elems) {
		storyboard.addObjectDiagramWith(elems);
	}
	
	public void addPattern(PatternObject pattern, boolean b) {
		storyboard.addPattern(pattern, b);
	}
	
	public void markCodeStart() {
		storyboard.markCodeStart();
	}
	
	public void addCode() {
		storyboard.addCode();
		// gleiches wie bei constructor
	}
	
	public String getMethodText(String rootDir, String className, String methodSignature) {
		return storyboard.getMethodText(rootDir, className, methodSignature);
	}
	
	public void recordSystemOut() {
		storyboard.recordSystemOut();
	}
	
	public void addSystemOut() {
		storyboard.add(storyboard.getSystemOut().toString());
	}
	
	public void dumpHTML() {
		storyboard.dumpHTML();
	}
	
	public void assertEquals(String message, double expected, double actual, double delta) {
		storyboard.assertEquals(message, expected, actual, delta);
	}
	
	public void assertEquals(String message, Object expected, Object actual) {	
		storyboard.assertEquals(message, expected, actual);
	}
	
	public void assertTrue(String message, boolean condition) {	
		storyboard.assertTrue(message, condition);
	}
	
	public void assertFalse(String message, boolean condition) {
		storyboard.assertFalse(message, condition);
	}
	
	public void assertEquals(String message, int expected, int actual) {
		storyboard.assertEquals(message, expected, actual);
	}	
	
	public void assertNotNull(String message, Object obj) {
		storyboard.assertNotNull(message, obj);
	}
	
	public void assertNull(String message, Object obj) {
		storyboard.assertNull(message, obj);
	}	
}
