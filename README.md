SDMLib
======

master : [![Build Status](https://travis-ci.org/fujaba/SDMLib.svg?branch=master)](https://travis-ci.org/fujaba/SDMLib) [![Scrutinizer Code Quality](https://scrutinizer-ci.com/g/fujaba/SDMLib/badges/quality-score.png?b=master)](https://scrutinizer-ci.com/g/fujaba/SDMLib/?branch=master)

develop: [![Build Status](https://travis-ci.org/fujaba/SDMLib.svg?branch=develop)](https://travis-ci.org/fujaba/SDMLib) [![Scrutinizer Code Quality](https://scrutinizer-ci.com/g/fujaba/SDMLib/badges/quality-score.png?b=develop)](https://scrutinizer-ci.com/g/fujaba/SDMLib/?branch=develop)

[![SDMLIB-PM](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/org/sdmlib/SDMLib/maven-metadata.xml.svg)](https://github.com/fujaba/SDMLib/releases/download/latest/SDMLib-pm.jar)

Welcome to SDMLib

SDMLib is a lightweight modeling library. SDMLib intentionally comes without any tool or editor. 

The idea is that you _code_ your model: 

    ClassModel model = new ClassModel("org.sdmlib.sample");

    Clazz uni = model.createClazz("University");

    Clazz student = model.createClazz("Student")
        .withAttribute("studentID", DataType.STRING);

    uni.withBidirectional(student, "students", Cardinality.MANY, "almaMater", Cardinality.ONE);

    model.generate();

By running model.generate() SDMLib will generate source code from your class model. 
If you extend your model later on and regenerate, the code generation will identify 
already existing classes and fields and attributes and leave them untouched. Only new 
elements will be inserted, carefully. 

In the example above, the generated code will be placed in package org.sdmlib.sample, which does not need to be pre-existing.

## Gradle and Maven

For stable version add to your `build.gradle` :

~~~groovy
repositories {
    jcenter()
}

dependencies {
    compile 'org.sdmlib:SDMLib:2.+'
    // compile 'org.sdmlib:SDMLib:2.3.204'  // did work when 2.3.+ failed
     
}
~~~

or for SNAPSHOT-Builds:
~~~groovy
repositories {
	maven {
        url "https://oss.sonatype.org/content/repositories/snapshots/"
    }
}

dependencies {
    compile 'org.sdmlib:SDMLib:2.+'
}
~~~

In order to get started, just add the following Maven dependency:

    <dependency>
        <groupId>org.sdmlib</groupId>
        <artifactId>SDMLib</artifactId>
        <version>2.3.+</version>
    </dependency>

In addition to class models, SDMLib will also support object diagrams, storyboards and model transformations. 

[Find documentation from the code repository here](https://rawgit.com/fujaba/SDMLib/master/doc/index.html)

<p>In addition to class models, SDMLib will also support object diagrams, story pages and model transformations. </p>

 - To learn about class diagrams and code generation, start with 
   (https://rawgit.com/fujaba/SDMLib/master/doc/StudyRightWithAssignmentsClassGeneration.html).
	
 - To learn how to use the generated classes see and to generate test documentation with storyboards see 
   (https://rawgit.com/fujaba/SDMLib/master/doc/StudyRightWithAssignmentsStoryboard.html).
	
 - For advanced model navigation and model transformations have a look at 
   (https://rawgit.com/fujaba/SDMLib/master/doc/StudyRightObjectModelNavigationAndQueries.html).

 - To store your object model as Json and to load it again or to send (parts of it) to another process, see
   (https://rawgit.com/fujaba/SDMLib/master/doc/JsonPersistency.html).
   
   
SDMLib comes with [MIT licence]

[![JProfiler](https://www.ej-technologies.com/images/product_banners/jprofiler_small.png)](http://www.ej-technologies.com/products/jprofiler/overview.html) optimized

Have fun 

The [SDMLib team]
