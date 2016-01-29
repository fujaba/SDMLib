package org.sdmlib.test.examples.SimpleModelWithSet.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.SimpleModelWithSet.model.Child;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.test.examples.SimpleModelWithSet.model.Person;

public class ChildPO extends PatternObject<ChildPO, Child>
{

    public ChildSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ChildSet matches = new ChildSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Child) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ChildPO(){
      newInstance(org.sdmlib.test.examples.SimpleModelWithSet.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ChildPO(Child... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.SimpleModelWithSet.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public void setParent(Person parent)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Child) getCurrentMatch()).setParent(parent);
      }
   }

   public ChildPO hasParent(Person value)
   {
      new AttributeConstraint()
      .withAttrName(Child.PROPERTY_PARENT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChildPO createParent(Person value)
   {
      this.startCreate().hasParent(value).endCreate();
      return this;
   }
   
   public Person getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Child) getCurrentMatch()).getParent();
      }
      return null;
   }
   
   public ChildPO withParent(Person value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Child) getCurrentMatch()).setParent(value);
      }
      return this;
   }
   
}
