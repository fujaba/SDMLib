package org.sdmlib.simple.model.attribute_g.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.attribute_g.Person;
import de.uniks.networkparser.list.SimpleSet;
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
   public PersonPO createNamesSetCondition(SimpleSet<SimpleSet> value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAMESSET)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createNamesSetAssignment(SimpleSet<SimpleSet> value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAMESSET)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SimpleSet<SimpleSet> getNamesSet()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getNamesSet();
      }
      return null;
   }
   
   public PersonPO withNamesSet(SimpleSet<SimpleSet> value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setNamesSet(value);
      }
      return this;
   }
   
}
