SDMLib
======

master : [![Build Status](https://travis-ci.org/fujaba/SDMLib.svg?branch=master)](https://travis-ci.org/fujaba/SDMLib)

develop: [![Build Status](https://travis-ci.org/fujaba/SDMLib.svg?branch=develop)](https://travis-ci.org/fujaba/SDMLib)

[Download SDMLib-SNAPSHOT-pm.jar for standard user](https://seblog.cs.uni-kassel.de/fileadmin/se/courses/ProgMeth/repository/org/sdmlib/SDMLib/SNAPSHOT/SDMLib-SNAPSHOT-pm.jar)

Welcome to SDMLib

SDMLib is a light weight modeling library. SDMLib intentionally comes without any tool or editor. 

The idea is that you _code_ your model: 

    ClassModel model = new ClassModel("org.sdmlib.sample");

    Clazz uni = model.createClazz("University");

    Clazz student = model.createClazz("Student")
        .withAttribute("studentID", DataType.STRING);

    uni.withAssoc(student, "students", Card.MANY, "almaMater", Card.ONE);

    model.generate();

By running model.generate() SDMLib will generate source code from your class model. 
If you extend your model later on and regenerate, the code generation will identify 
already existing classes and fields and attributes and leave them untouched. Only new 
elements will be inserted, carefully. 

In the example above, the generated code will be placed in package org.sdmlib.sample, which does not need to be pre-existing.

In order to get started, just add the following Maven dependency:

    <dependency>
        <groupId>org.sdmlib</groupId>
        <artifactId>SDMLib</artifactId>
        <version>1.1.600</version>
    </dependency>

In addition to class models, SDMLib will also support object diagrams, storyboards and model transformations. 

[Find documentation from the code repository here](https://rawgit.com/fujaba/SDMLib/master/doc/index.html)

<p>In addition to class models, SDMLib will also support object diagrams, story pages and model transformations. </p>

 - To learn about class diagrams and code generation, start with 
   (https://rawgit.com/fujaba/SDMLib/master/doc/StudyRightWithAssignmentsClassGeneration.html).
	
 - To learn how to use the generated classes see and to generate test documentation with story pages see 
   (https://rawgit.com/fujaba/SDMLib/master/doc/StudyRightWithAssignmentsStoryPage.html).
	
 - For advanced model navigation and model transformations have a look at 
   (https://rawgit.com/fujaba/SDMLib/master/doc/StudyRightObjectModelNavigationAndQueries.html).

 - To store your object model as Json and to load it again or to send (parts of it) to another process, see
   (https://rawgit.com/fujaba/SDMLib/master/doc/JsonPersistency.html).
   
   
SDMLib comes with [MIT licence]

Have fun 

The [SDMLib team]
