package org.sdmlib.test.examples.modelcouch.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.modelcouch.Person;
import org.sdmlib.test.examples.modelcouch.util.PersonPO;
import org.sdmlib.test.examples.modelcouch.util.PersonSet;
import org.sdmlib.test.examples.modelcouch.util.TaskPO;
import org.sdmlib.test.examples.modelcouch.Task;
import org.sdmlib.test.examples.modelcouch.util.TaskSet;
import org.sdmlib.test.examples.modelcouch.util.DocumentDataPO;
import org.sdmlib.test.examples.modelcouch.DocumentData;
import org.sdmlib.test.examples.modelcouch.util.DocumentDataSet;

public class PersonPO extends PatternObject<PersonPO, Person>
{

    public PersonSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PersonSet matches = new PersonSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Person) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PersonPO(){
      newInstance(org.sdmlib.test.examples.modelcouch.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PersonPO(Person... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.modelcouch.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public PersonPO hasMembers()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_MEMBERS, result);
      
      return result;
   }

   public PersonPO createMembers()
   {
      return this.startCreate().hasMembers().endCreate();
   }

   public PersonPO hasMembers(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_MEMBERS);
   }

   public PersonPO createMembers(PersonPO tgt)
   {
      return this.startCreate().hasMembers(tgt).endCreate();
   }

   public PersonSet getMembers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getMembers();
      }
      return null;
   }

   public PersonPO hasGroups()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_GROUPS, result);
      
      return result;
   }

   public PersonPO createGroups()
   {
      return this.startCreate().hasGroups().endCreate();
   }

   public PersonPO hasGroups(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_GROUPS);
   }

   public PersonPO createGroups(PersonPO tgt)
   {
      return this.startCreate().hasGroups(tgt).endCreate();
   }

   public PersonSet getGroups()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getGroups();
      }
      return null;
   }

   public TaskPO hasTasks()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_TASKS, result);
      
      return result;
   }

   public TaskPO createTasks()
   {
      return this.startCreate().hasTasks().endCreate();
   }

   public PersonPO hasTasks(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_TASKS);
   }

   public PersonPO createTasks(TaskPO tgt)
   {
      return this.startCreate().hasTasks(tgt).endCreate();
   }

   public TaskSet getTasks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getTasks();
      }
      return null;
   }

   public DocumentDataPO hasPersonData()
   {
      DocumentDataPO result = new DocumentDataPO(new DocumentData[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_PERSONDATA, result);
      
      return result;
   }

   public DocumentDataPO createPersonData()
   {
      return this.startCreate().hasPersonData().endCreate();
   }

   public PersonPO hasPersonData(DocumentDataPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_PERSONDATA);
   }

   public PersonPO createPersonData(DocumentDataPO tgt)
   {
      return this.startCreate().hasPersonData(tgt).endCreate();
   }

   public DocumentDataSet getPersonData()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getPersonData();
      }
      return null;
   }

}
