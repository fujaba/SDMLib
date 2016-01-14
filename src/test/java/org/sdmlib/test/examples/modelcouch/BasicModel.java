package org.sdmlib.test.examples.modelcouch;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.StoryPage;
import static de.uniks.networkparser.graph.DataType.*;
import static de.uniks.networkparser.graph.Cardinality.*;

import de.uniks.networkparser.graph.Clazz;

public class BasicModel
{
   public static void main(String[] args)
   {
      new BasicModel().basicModel();
   }

   private void basicModel()
   {
      StoryPage story = new StoryPage();

      ClassModel model = new ClassModel("org.sdmlib.test.examples.modelcouch");

      Clazz docData = model.createClazz("DocumentData")
            .withAttribute("tag", STRING)
            .withAttribute("value", STRING)
            .withAttribute("type", STRING)
            .withAttribute("lastEditor", STRING)
            .withAttribute("lastModified", STRING);

      docData.withBidirectional(docData, "subData", MANY, "parentData", MANY);
      
      
      Clazz task = model.createClazz("Task");
      
      task.withBidirectional(task, "subTasks", MANY, "parentTasks", MANY);

      
      Clazz person = model.createClazz("Person");

      person.withBidirectional(person, "members", MANY, "groups", MANY);

      
      task.withBidirectional(person, "persons", MANY, "tasks", MANY);
      task.withBidirectional(docData, "taskData", MANY, "tasks", MANY);
      person.withBidirectional(docData, "personData", MANY, "persons", MANY);
      

      story.addClassDiagram(model);

      model.generate("src/test/java");

      story.add(""
            + "The basic element of data in software stories are tag value pairs like 'name' = 'Albert'. "
            + "However, there may be values of different types, e.g. "
            );

      story.addPreformatted(""
            + "'login' : 'login' = 'zuendorf' \n"
            + "'dateOfBirth' : 'date' = '20.07.1963' \n"
            + "'phone' : 'phone number' = '+49 561 804 6240' \n"
            + "'familiy' : 'list of personRef' = 'sabine.zuendorf, tom.zuendorf' \n"
            + "");

      story.add(""
            + "As each tag/value pair has a 'type', we may provide a generic javafx controller and view, "
            + "that provides specific editing support for any tag value pair based on this type info. "
            + "For example a list of person references might offer a choice menu pre-filled with the login or names of persons in the system."
            + "Even more specific selection lists may be provided with the help of more specific type information. ");

      story.add("Finally, any tag/value pair shall store the login of the last person that has created or changed it. And we store the time of "
            + "the last modification. This helps multiple users to see how e.g. a task has evolved and whom to ask for certain data content. ");

      story.add(""
            + "Multiple tag/value pairs may be grouped to a complex data record, e.g. all tag/value pairs belonging to the person record of Albert. "
            + "Just because it may be cool, one tag/value pair (or complex data record) may belong to multiple parentData records. "
            + "Thus, multiple people may e.g. share the same address. ");

      story.add(""
            + "New tag/value pair types may be added to software stories via plugins to the javafx controller."
            + "Similarly, some logic part of the system may propose a standard set of tag/value pairs that a person record should have. ");

      story.add("Although, DocumentData may be used to model every aspects of a software story, "
         + "we might also add Person and Task as specific classes as these elements have central roles in software stories.");
      
      addExample(story);

      story.dumpHTML();
   }

   private void addExample(StoryPage story)
   {
      story.add(""
         + "A basic data set for a software story may look like: ");
      
      DocumentData seGroup = new DocumentData()
            .withType("list of person")
            .withLastEditor("zuendorf")
            .withLastModified("29.12.2015 19:18:42:123 CEST");
      
      DocumentData zuendorf = seGroup.createSubData()
            .withType("person")
            .withLastEditor("zuendorf")
            .withLastModified("29.12.2015 19:18:42:124 CEST");
      
      DocumentData zuendorfLogin = zuendorf.createSubData()
            .withTag("login")
            .withValue("zuendorf")
            .withType("login")
            .withLastEditor("zuendorf")
            .withLastModified("29.12.2015 19:18:42:125 CEST");
      
      DocumentData zuendorfPhone = zuendorf.createSubData()
            .withTag("phone")
            .withValue("+49 561 804 6240")
            .withType("phone number")
            .withLastEditor("zuendorf")
            .withLastModified("29.12.2015 19:18:42:126 CEST");
      
      DocumentData tobi = seGroup.createSubData()
            .withType("person")
            .withLastEditor("tobi")
            .withLastModified("29.12.2015 20:05:23:123 CEST");
      
      DocumentData tobiLogin = tobi.createSubData()
            .withTag("login")
            .withValue("lowrider")
            .withType("login")
            .withLastEditor("tobi")
            .withLastModified("29.12.2015 20:05:23:124 CEST");
      
      DocumentData tobiPhone = tobi.createSubData()
            .withTag("phone")
            .withValue("+49 561 804 6194")
            .withType("phone number")
            .withLastEditor("tobi")
            .withLastModified("29.12.2015 20:05:23:125 CEST");
      
      story.addObjectDiagram(seGroup.getSubDataTransitive());
      
   }
}
