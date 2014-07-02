SDMLib
======

Welcome to SDMLib

SDMLib is a light weight modeling library. SDMLib intentionally comes without any tool or editor. 

The idea is that you _code_ your model: 

    ClassModel model = new ClassModel();

    Clazz uni = model.createClazz("University");

    Clazz stud = model.createClazz("Student");

    Association uniHasStuds = new Association()
        .withSource(uni, ONE, AGGREGATION)
        .withTarget(stud, MANY);

    model.generate("../StudyRightUni/src");

By running model.generate() SDMLib will generate source code from your class model. 
If you extend your model later on and regenerate, the code generation will identify 
already existing classes and fields and attributes and leave them untouched. Only new 
elements will be inserted, carefully. 

In addition to class models, SDMLib will also support object diagrams, storyboards and model transformations. 

[Find documentation from the code repository here](https://rawgit.com/fujaba/SDMLib/master/doc/index.html)

Start with [StudyRight with assignments class generation](https://rawgit.com/fujaba/SDMLib/master/doc/StudyRight%20with%20assignments%20class%20generation.html)
and than look at [StudStudyRight with assignments storyboard](https://rawgit.com/fujaba/SDMLib/master/doc/StudyRight%20with%20assignments%20storyboard.html)

SDMLib comes with [MIT licence]

Have fun 

The [SDMLib team]
