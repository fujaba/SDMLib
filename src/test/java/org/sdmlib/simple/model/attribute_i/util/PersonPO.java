package org.sdmlib.simple.model.attribute_i.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.attribute_i.Person;
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
      newInstance(null);
   }

   public PersonPO(Person... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public PersonPO filterNamesMap(SimpleKeyValueList<String,SimpleKeyValueList> value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAMESMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createNamesMap(SimpleKeyValueList<String,SimpleKeyValueList> value)
   {
      this.startCreate().filterNamesMap(value).endCreate();
      return this;
   }
   
   public SimpleKeyValueList<String,SimpleKeyValueList> getNamesMap()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getNamesMap();
      }
      return null;
   }
   
   public PersonPO withNamesMap(SimpleKeyValueList<String,SimpleKeyValueList> value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setNamesMap(value);
      }
      return this;
   }
   
}
