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

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.Method;

public class ReverseClassModelTest
{
	
	
	@Test
	public void testReverseClassModel()
	{

		ClassModel model = new ClassModel();

    model.updateFromCode("test", "examples test src", "org.sdmlib.examples.studyrightextends");
		model.insertModelCreationCodeHere("test");

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

}

