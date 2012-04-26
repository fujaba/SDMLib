/*
   Copyright (c) 2012 ajahl

   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software.

   The Software shall be used for Good, not Evil.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.sdmlib.model.classes;

import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import junit.framework.Assert;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.utils.PropertyChangeInterface;

public class ReverseClassModelTest implements PropertyChangeInterface
{
  public static final String BACKLOG = "backlog";
	
	@Test
	public void testStudyRightExtendsReverseClassModel()
	{
		 Scenario scenario = new Scenario("StudyRightExtendsReverseClassModel");

     scenario.add("Start situation: There are some java files. We parse them and generate a class model: ", BACKLOG, "ajahl", "02.04.2012 14:58:18", 0, 0);

		ClassModel model = new ClassModel();


      Clazz femaleClass = new Clazz("org.sdmlib.examples.studyrightextends.Female");

      Clazz lectureClass = new Clazz("org.sdmlib.examples.studyrightextends.Lecture");

      Clazz maleClass = new Clazz("org.sdmlib.examples.studyrightextends.Male");

      Clazz personClass = new Clazz("org.sdmlib.examples.studyrightextends.Person");

      Clazz professorClass = new Clazz("org.sdmlib.examples.studyrightextends.Professor");

      Clazz roomClass = new Clazz("org.sdmlib.examples.studyrightextends.Room");

      Clazz studentClass = new Clazz("org.sdmlib.examples.studyrightextends.Student");

      Clazz universityClass = new Clazz("org.sdmlib.examples.studyrightextends.University");



    Clazz reverseClassModelTestClass = new Clazz("org.sdmlib.model.classes.ReverseClassModelTest");
      /* add method */
      new Method()
			.withClazz(reverseClassModelTestClass)
			.withSignature("testInterfacesCodeGen()");
      /* add method */
      new Method()
			.withClazz(reverseClassModelTestClass)
			.withSignature("testSuperClassesCodeGen()");
      /* add method */
      new Method()
			.withClazz(reverseClassModelTestClass)
			.withSignature("testMethodsClassesCodeGen()");
      /* add method */
      new Method()
			.withClazz(reverseClassModelTestClass)
			.withSignature("testStudyRightClassesCodeGen()");
      /* add method */
      new Method()
			.withClazz(reverseClassModelTestClass)
			.withSignature("testStudyRightExtendsReverseClassModel()");


    model.updateFromCode("test", "examples test src", "org.sdmlib.examples.studyrightextends");
		model.insertModelCreationCodeHere("test");
		
    scenario.addImage(model.dumpClassDiag("StudyRightExtendsReverseClassModel"));

    ScenarioManager.get()
    .add(scenario)
    .dumpHTML();

	}

	@Test
	public void testStudyRightClassesCodeGen()
	{
		ClassModel model = new ClassModel();

		Clazz uniClass = new Clazz("org.sdmlib.examples.studyrightextends.University")
		.withAttribute("name", "String");
		
		Clazz personClass = new Clazz("org.sdmlib.examples.studyrightextends.Person")
		 .withInterfaze(true)
		 .withAttribute("name", "String");
		
		new Method()
			.withClazz(personClass)
			.withSignature("findMyPosition()");
			
		new Method()
			.withClazz(personClass)
			.withSignature("findMyPosition(String)");
			
		new Method()
			.withClazz(personClass)
			.withSignature("findMyPosition(String, int)");		 
		
		Clazz maleClass = new Clazz("org.sdmlib.examples.studyrightextends.Male")
		.withInterfaces(personClass)
		.withInterfaze(true);
		
		Clazz femaleClass = new Clazz("org.sdmlib.examples.studyrightextends.Female")
		.withInterfaces(personClass);		


		Clazz studClass = new Clazz("org.sdmlib.examples.studyrightextends.Student")
		.withInterfaces(maleClass)
		.withAttribute("matrNo", "int");
		
		Clazz profClass = new Clazz("org.sdmlib.examples.studyrightextends.Professor")
		.withSuperClass(femaleClass)
		.withAttribute("PersNr", "int");
		
		Clazz lectureClass = new Clazz("org.sdmlib.examples.studyrightextends.Lecture")
		.withAttribute("Title", "String");

		Association uniToStud = new Association()
		.withSource("uni", uniClass, Role.ONE)
		.withTarget("persons", personClass, Role.MANY);

		Clazz roomClass = new Clazz("org.sdmlib.examples.studyrightextends.Room")
		.withAttribute("roomNo", "String")
		.withAttribute("credits", "int");
		
		new Method()
		.withClazz(roomClass)
		.withSignature("studentCount()");	

		Association uniToRoom = new Association()
		.withSource("uni", uniClass, Role.ONE, Role.AGGREGATION)
		.withTarget("rooms", roomClass, Role.MANY);

		Association doors = new Association()
		.withSource("neighbors", roomClass, Role.MANY)
		.withTarget("neighbors", roomClass, Role.MANY);

		Association studsInRoom = new Association()
		.withSource("persons", personClass, Role.MANY)
		.withTarget("in", roomClass, Role.ONE);
		
		Association lecInRoom = new Association()
		.withSource("lecture", lectureClass, Role.MANY)
		.withTarget("in", roomClass, Role.ONE);
		
		Association profLec = new Association()
		.withSource("lecture", lectureClass, Role.MANY)
		.withTarget("has", profClass, Role.ONE);
		
		Association studLec = new Association()
		.withSource("lecture", lectureClass, Role.MANY)
		.withTarget("listen", studClass, Role.ONE);

		model.generate("examples", "examplehelpers");
	}
	
	@Test
	public void testMethodsClassesCodeGen()
	{
		ClassModel model = new ClassModel();

    Clazz reverseClassModelTestClass = new Clazz("org.sdmlib.model.classes.ReverseClassModelTest");
		
		Clazz placeClass = new Clazz("org.sdmlib.model.test.methods.Place");

		new Method()
		.withClazz(placeClass)
		.withSignature("findMyPosition()");
		
		new Method()
		.withClazz(placeClass)
		.withSignature("findMyPosition(String)");
		
		new Method()
		.withClazz(placeClass)
		.withSignature("findMyPosition(String,int)");	
		
		model.generate("test", "testhelpers");
		
		 Scenario scenario = new Scenario("MethodsClassesCodeGen");
     scenario.add("Start situation: There are some java files. We parse them and generate a class model: ", BACKLOG, "ajahl", "26.04.2012 14:58:18", 0, 0);
     
     //----------------------------------------------

     model = new ClassModel();
     
     model.updateFromCode("test", "examples test src", "org.sdmlib.model.test.methods");
     model.insertModelCreationCodeHere("test");
     
     //-----------------------------------------------
     LinkedHashSet<Clazz> classes = model.getClasses();
     Assert.assertEquals(2, classes.size());
     
     for (Clazz clazz : classes)
     {
 	    String name = clazz.getName();
 	    Assert.assertTrue("class " + name + " not found ", placeClass.getName().equals(name)  
 	    								|| reverseClassModelTestClass.getName().equals(name));
 	    
 	   if (placeClass.getName().equals(name)) {
	    	Assert.assertEquals( 3,  clazz.getMethods().size());
	    	for (Method method : clazz.getMethods())
        {
	    		Assert.assertTrue("method " + method.getSignature() + " has not " + name + " as class", 
	    				clazz.getName().equals(method.getClazz().getName()));
	    		
        }
	    }
     }
      
//     scenario.addImage(model.dumpClassDiag("MethodsClassesCodeGen"));
//     
//     ScenarioManager.get()
//     .add(scenario)
//     .dumpHTML();
		
		
	}

	@Test
	public void testSuperClassesCodeGen()
	{
		ClassModel model = new ClassModel();

      Clazz continentClass = new Clazz("org.sdmlib.model.test.superclasses.Continent");

      Clazz stateClass = new Clazz("org.sdmlib.model.test.superclasses.State")
			/*set superclass*/
      .withSuperClass(continentClass); 

      Clazz townClass = new Clazz("org.sdmlib.model.test.superclasses.Town")
			/*set superclass*/
      .withSuperClass(stateClass);

      Clazz reverseClassModelTestClass = new Clazz("org.sdmlib.model.classes.ReverseClassModelTest");
		
		model.generate("test", "testhelpers");

		Scenario scenario = new Scenario("SuperClassesCodeGen");
		scenario.add("Start situation: There are some java files. We parse them and generate a class model: ", BACKLOG, "ajahl", "26.04.2012 14:58:18", 0, 0);
	
	//----------------------------------------------
		model = new ClassModel();
    
    model.updateFromCode("test", "examples test src", "org.sdmlib.model.test.superclasses");    
    model.insertModelCreationCodeHere("test");
    
  //-----------------------------------------------
    LinkedHashSet<Clazz> classes = model.getClasses();
    Assert.assertEquals(4, classes.size());
    for (Clazz clazz : classes)
    {
	    String name = clazz.getName();
	    Assert.assertTrue("class " + name + " not found ", continentClass.getName().equals(name) 
	    								|| stateClass.getName().equals(name) 
	    								|| townClass.getName().equals(name)
	    								|| reverseClassModelTestClass.getName().equals(name));
	    
	    if (stateClass.getName().equals(name)) {
	    	Assert.assertTrue( name + " has no superclass named " + clazz.getSuperClass().getName(), 
	    			continentClass.getName().equals(clazz.getSuperClass().getName() ) );
	    }
	    else if (townClass.getName().equals(name)) {
	    	Assert.assertTrue( name + " has no superclass named " + clazz.getSuperClass().getName(), 
	    			stateClass.getName().equals(clazz.getSuperClass().getName() ) );
	    }
    }

    
//    scenario.addImage(model.dumpClassDiag("SuperClassesCodeGen"));
//    
//    ScenarioManager.get()
//    .add(scenario)
//    .dumpHTML();
	
	
	}
	
	@Test
	public void testInterfacesCodeGen()
	{
		ClassModel model = new ClassModel();

    Clazz reverseClassModelTestClass = new Clazz("org.sdmlib.model.classes.ReverseClassModelTest");

		Clazz personClass = new Clazz("org.sdmlib.model.test.interfaces.Person")
			/*set interface*/
      .withInterfaze(true);

		Clazz maleClass = new Clazz("org.sdmlib.model.test.interfaces.Male")
			/*set interface*/
      .withInterfaze(true)
			/*add interface*/
      .withInterfaces(personClass);
		
		Clazz femaleClass = new Clazz("org.sdmlib.model.test.interfaces.Female")
			/*set interface*/
      .withInterfaze(true)
			/*add interface*/
      .withInterfaces(personClass);

		Clazz studentClass = new Clazz("org.sdmlib.model.test.interfaces.Student")
			/*add interface*/
      .withInterfaces(femaleClass)
			/*add interface*/
      .withInterfaces(maleClass);
		
		model.generate("test", "testhelpers");

		Scenario scenario = new Scenario("InterfacesCodeGen");
		scenario.add("Start situation: There are some java files. We parse them and generate a class model: ", BACKLOG, "ajahl", "26.04.2012 14:58:18", 0, 0);
			
		model = new ClassModel();
    
    model.updateFromCode("test", "examples test src", "org.sdmlib.model.test.interfaces");
    model.insertModelCreationCodeHere("test");
    
    //-----------------------------------------------
    LinkedHashSet<Clazz> classes = model.getClasses();
    Assert.assertEquals(5, classes.size());
    for (Clazz clazz : classes)
    {
	    String name = clazz.getName(); 
	    Assert.assertTrue("class " + name + " not found ", personClass.getName().equals(name) 
	    								|| maleClass.getName().equals(name) 
	    								|| femaleClass.getName().equals(name)
	    								|| studentClass.getName().equals(name)
	    								|| reverseClassModelTestClass.getName().equals(name));
	    
	    if (personClass.getName().equals(name)) {
	    	Assert.assertTrue("no interface person", clazz.isInterfaze());
	    }
	    else if (maleClass.getName().equals(name)) {
	    	Assert.assertTrue("no interface male",  clazz.isInterfaze());
	    }
	    else if (femaleClass.getName().equals(name)) {
	    	Assert.assertTrue("no interface female",  clazz.isInterfaze() );
	    }
	    else if (studentClass.getName().equals(name)) {
	    	Assert.assertEquals(2,  clazz.getInterfaces().size() );
	    }
    }
    
//    scenario.addImage(model.dumpClassDiag("InterfacesCodeGen"));
//    
//    ScenarioManager.get()
//    .add(scenario)
//    .dumpHTML();
	
	}

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      return false;
   }

   
   //==========================================================================
   
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
}

