package org.sdmlib.test.examples.SimpleModelWithSet.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.SimpleModelWithSet.model.Child;
import org.sdmlib.test.examples.SimpleModelWithSet.model.Person;
import org.sdmlib.models.pattern.AttributeConstraint;

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
      newInstance(null);
   }

   public ChildPO(Child... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   
   //==========================================================================
   
   public void setParent(Person parent)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Child) getCurrentMatch()).setParent(parent);
      }
   }

   public ChildPO filterParent(Person value)
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
      this.startCreate().filterParent(value).endCreate();
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
