package org.sdmlib.test.examples.SimpleModelWithSet.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.SimpleModelWithSet.model.Person;
import de.uniks.networkparser.list.SimpleKeyValueList;
import org.sdmlib.models.pattern.AttributeConstraint;

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
      newInstance(org.sdmlib.test.examples.SimpleModelWithSet.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PersonPO(Person... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.SimpleModelWithSet.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public PersonPO hasName(SimpleKeyValueList<String,String> value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public PersonPO createName(SimpleKeyValueList<String,String> value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public SimpleKeyValueList<String,String> getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PersonPO withName(SimpleKeyValueList<String,String> value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
}
