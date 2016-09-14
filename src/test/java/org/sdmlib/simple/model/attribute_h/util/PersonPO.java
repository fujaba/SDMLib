package org.sdmlib.simple.model.attribute_h.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.attribute_h.Person;
import de.uniks.networkparser.list.SimpleKeyValueList;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;

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

   public PersonPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PersonPO createNamesCondition(SimpleKeyValueList<String,String> value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAMES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createNamesAssignment(SimpleKeyValueList<String,String> value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAMES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SimpleKeyValueList<String,String> getNames()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getNames();
      }
      return null;
   }
   
   public PersonPO withNames(SimpleKeyValueList<String,String> value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setNames(value);
      }
      return this;
   }
   
}
