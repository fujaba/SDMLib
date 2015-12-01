SDMLib
======

master : [![Build Status](https://travis-ci.org/fujaba/SDMLib.svg?branch=master)](https://travis-ci.org/fujaba/SDMLib) [![Scrutinizer Code Quality](https://scrutinizer-ci.com/g/fujaba/SDMLib/badges/quality-score.png?b=master)](https://scrutinizer-ci.com/g/fujaba/SDMLib/?branch=master)

develop: [![Build Status](https://travis-ci.org/fujaba/SDMLib.svg?branch=develop)](https://travis-ci.org/fujaba/SDMLib) [![Scrutinizer Code Quality](https://scrutinizer-ci.com/g/fujaba/SDMLib/badges/quality-score.png?b=develop)](https://scrutinizer-ci.com/g/fujaba/SDMLib/?branch=develop)

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

Start with [StudyRight with assignments class generation](https://rawgit.com/fujaba/SDMLib/master/doc/StudyRight%20with%20assignments%20class%20generation.html)
and than look at [StudStudyRight with assignments storyboard](https://rawgit.com/fujaba/SDMLib/master/doc/StudyRight%20with%20assignments%20storyboard.html)

SDMLib comes with [MIT licence]

Have fun 

The [SDMLib team]
