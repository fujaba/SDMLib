package org.sdmlib.simple.model.attribute_l.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.attribute_l.Person;
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
   public PersonPO filterPersonalName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_PERSONALNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO filterPersonalName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_PERSONALNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createPersonalName(String value)
   {
      this.startCreate().filterPersonalName(value).endCreate();
      return this;
   }
   
   public String getPersonalName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getPersonalName();
      }
      return null;
   }
   
   public PersonPO withPersonalName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setPersonalName(value);
      }
      return this;
   }
   
}
