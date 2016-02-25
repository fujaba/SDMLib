package org.sdmlib.simple.model.attribute_j.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.attribute_j.Person;
import de.uniks.networkparser.list.SimpleKeyValueList;
import de.uniks.networkparser.list.SimpleSet;
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
   public PersonPO filterNamesList(SimpleSet<SimpleKeyValueList> value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAMESLIST)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createNamesList(SimpleSet<SimpleKeyValueList> value)
   {
      this.startCreate().filterNamesList(value).endCreate();
      return this;
   }
   
   public SimpleSet<SimpleKeyValueList> getNamesList()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getNamesList();
      }
      return null;
   }
   
   public PersonPO withNamesList(SimpleSet<SimpleKeyValueList> value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setNamesList(value);
      }
      return this;
   }
   
}
